package org.poormanscastle.studies.compilers.utils.grammartools.lr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.utils.grammartools.Production;
import org.poormanscastle.studies.compilers.utils.grammartools.Symbol;

import static com.google.common.base.Preconditions.checkState;

/**
 * Created by georg on 09.02.16.
 */
public class LR0Grammar {

    private final Set<LRState> states = new HashSet<>();

    private final Set<LREdge> edges = new HashSet<>();

    private final List<Production> productions = new LinkedList<>();

    private final Map<String, Symbol> symbols = new HashMap<>();

    private final Set<Symbol> terminalSymbols = new HashSet<>();

    public boolean addProduction(Production production) {
        return productions.add(production);
    }

    public Symbol addSymbol(Symbol symbol) {
        if (symbols.containsKey(symbol.getId())) {
            throw new RuntimeException(StringUtils.join("Symbol already defined: ", symbol));
        }
        return symbols.put(symbol.getId(), symbol);
    }

    public void addTerminalSymbol(Symbol terminalSymbol) {
        if (terminalSymbols.contains(terminalSymbol)) {
            throw new RuntimeException(StringUtils.join("TerminalSymbol already defined: ", terminalSymbol));
        }
        if (symbols.containsKey(terminalSymbol.getId())) {
            throw new RuntimeException(StringUtils.join("Symbol already defined: ", terminalSymbol));
        }
        terminalSymbols.add(terminalSymbol);
        addSymbol(terminalSymbol);
    }

    public List<Production> getProductions() {
        return productions;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    public Set<Symbol> getTerminalSymbols() {
        return terminalSymbols;
    }

    public Set<LRState> getStates() {
        return states;
    }

    /**
     * calculates the LR(0) states for this LR(0) grammar.
     */
    public void calculateStatesAndTransitions() {
        states.clear();
        edges.clear();
        LRState startState = new LRState();
        startState.getItems().add(new LRItem(productions.get(0)));
        states.add(calculateClosure(startState));

        int oldSizeEdges, oldSizeStates;
        do {
            oldSizeEdges = edges.size();
            oldSizeStates = states.size();
            for (LRState sourceState : states) {
                for (LRItem item : sourceState.getItems()) {
                    if (!item.isReducible()) {
                        LRState targetState = calculateEdge(sourceState, item.getNextSymbol());
                        states.add(targetState);
                        edges.add(new LREdge(sourceState, targetState, item.getNextSymbol()));
                    }
                }
            }
            checkState(oldSizeEdges >= 0 && oldSizeEdges <= edges.size());
            checkState(oldSizeStates >= 0 && oldSizeStates <= states.size());
        } while (oldSizeEdges < edges.size() || oldSizeStates < states.size());
    }

    /**
     * calculates the closure for a state, i.e. add more items to the state when there is a dot to the left
     * of a nonterminal.
     *
     * @param state
     * @return state for method call chaining there reference to the given state is returned.
     */
    public LRState calculateClosure(LRState state) {
        int oldSize;
        do {
            oldSize = state.getItems().size();
            for (LRItem item : state.getItems()) {
                if (!item.isReducible() && !item.getNextSymbol().isTerminal()) {
                    for (Production production : getProductions()) {
                        if (item.getNextSymbol() == production.getLhs()) {
                            state.getItems().add(new LRItem(production));
                        }
                    }
                }
            }
            checkState(oldSize <= state.getItems().size());
        } while (oldSize < state.getItems().size());
        return state;
    }

    public LRState calculateEdge(LRState state, Symbol x) {
        LRState newState = new LRState();
        for (LRItem item : state.getItems()) {
            if (!item.isReducible() && item.getNextSymbol() == x) {
                newState.getItems().add(new LRItem(item.getProduction(), item.getPosition() + 1));
            }
        }
        return calculateClosure(newState);
    }

}
