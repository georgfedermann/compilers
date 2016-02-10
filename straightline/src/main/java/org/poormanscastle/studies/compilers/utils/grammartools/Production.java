package org.poormanscastle.studies.compilers.utils.grammartools;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * one of the productions used to define a grammar.
 * <p>
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
}
