package org.poormanscastle.studies.compilers.utils.grammartools;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import static com.google.common.base.Preconditions.checkArgument;
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
     * the left-hand-side symbol
     */
    private Symbol lhs;

    /**
     * the right-hand-side symbols
     */
    private List<Symbol> rhs = new LinkedList<>();

    /**
     * production definition as read from the input stream
     */
    private String definitionString;

    private boolean startProduction = false;

    /**
     * a production's follow set is equal to its lhs symbol's follow set (because this
     * production can be employed at any place where the original nonterminal symbol is
     * allowed), but its first set must be calculated specifically for this prouction,
     * since the lhs nonterminal symbol accumulates the contents of its first set over
     * all its productions.
     */
    private Set<Symbol> firstSet = new HashSet<>();

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

    public Production(String definitionString, boolean startProduction, Grammar grammar) {
        int state = STATE_LHS;
        this.definitionString = definitionString;
        this.startProduction = startProduction;
        definitionString = definitionString.replace(MAPPING_ARROW, " ");
        StringTokenizer tokenizer = new StringTokenizer(definitionString);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            Symbol symbol = grammar.getSymbols().get(token);
            if (symbol == null) {
                symbol = new Symbol(token, false);
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

    public void setFirstSet(Set<Symbol> firstSet) {
        this.firstSet = firstSet;
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

    public void setLhs(Symbol lhs) {
        checkArgument(!lhs.isTerminal());
        this.lhs = lhs;
    }

    public boolean isStartProduction() {
        return startProduction;
    }

    public void setStartProduction(boolean startProduction) {
        this.startProduction = startProduction;
    }

    public String getDefinitionString() {
        return definitionString;
    }
}
