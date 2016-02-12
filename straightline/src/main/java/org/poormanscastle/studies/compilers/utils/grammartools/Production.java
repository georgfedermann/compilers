package org.poormanscastle.studies.compilers.utils.grammartools;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import static com.google.common.base.Preconditions.checkState;

/**
 * one of the productions used to define a grammar.
 * <p/>
 * Created by georg on 09.02.16.
 */
public class Production {

    private final String MAPPING_ARROW = "->";

    private final int STATE_LHS = 0;

    private final int STATE_RHS = 1;

    /**
     * the left-hand-side symbol (short lhs)
     */
    private Symbol lhs;

    /**
     * the right-hand-side symbols (short rhs)
     */
    private final List<Symbol> rhs = new LinkedList<>();

    /**
     * production definition as read from the input stream
     */
    private final String definitionString;

    private final boolean startProduction;

    /**
     * a production's follow set is equal to its lhs symbol's follow set (because this
     * production can be employed at any place where the original nonterminal symbol is
     * allowed), but its first set must be calculated specifically for this prouction,
     * since the lhs nonterminal symbol accumulates the contents of its first set over
     * all its productions.
     */
    private final Set<Symbol> firstSet = new HashSet<>();

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toStringExclude(this, "lhs", "rhs", "STATE_LHS", "STATE_RHS", "MAPPING_ARROW");
    }

    public boolean isNullable() {
        boolean result = true;
        for (Symbol symbol : rhs) {
            result &= symbol.isNullable();
        }
        return result;
    }

    /**
     * initializes a new Production.
     *
     * @param definitionString gets stored to graphically represent this production when printing parser tables. Note:
     *                         parser tables are different from parser trees:
     *                         parser tables are input when parsing, parser trees are output when parsing.
     *                         <p/>
     *                         the definition string will be parsed to infere the lhs symbol and the rhs symbols. when
     *                         the respective symbols can be found in the grammar they are taken from there. otherwise
     *                         they get newly created and registered with the grammar.
     * @param startProduction  states whether this production is the one startProduction in the given grammar.
     * @param grammar          this objects holds all the grammar's symbols and productions.
     */
    public Production(String definitionString, boolean startProduction, Grammar grammar) {
        this.definitionString = definitionString;
        this.startProduction = startProduction;
        // parsing the definition string. start with the LHS, than preprocess with RHS
        int state = STATE_LHS;
        // for further proceedings, the mapping arrow -> is not needed any more
        StringTokenizer tokenizer = new StringTokenizer(definitionString.replace(MAPPING_ARROW, " "));
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            Symbol symbol = grammar.getSymbols().get(token);
            // symbol was not registered before, i.e. first mentioned in this production
            // -> create new symbol and register it with the grammar
            if (symbol == null) {
                symbol = new Symbol(token);
                grammar.addSymbol(symbol);
            }
            switch (state) {
                case STATE_LHS:
                    checkState(!symbol.isTerminal());
                    lhs = symbol;
                    state = STATE_RHS;
                    break;
                case STATE_RHS:
                    rhs.add(symbol);
                    break;
                default:
                    throw new RuntimeException("What fracking state is this machine in?");
            }
        }
    }

    public Set<Symbol> getFirstSet() {
        return firstSet;
    }

    public List<Symbol> getRhs() {
        return rhs;
    }

    public boolean addRhsSymbol(Symbol symbol) {
        return rhs.add(symbol);
    }

    public Symbol getLhs() {
        checkState(!lhs.isTerminal());
        return lhs;
    }

    public boolean isStartProduction() {
        return startProduction;
    }

    public String getDefinitionString() {
        return definitionString;
    }
}
