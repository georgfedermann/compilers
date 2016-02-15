package org.poormanscastle.studies.compilers.utils.grammartools.lr;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.poormanscastle.studies.compilers.utils.grammartools.AbstractGrammar;
import org.poormanscastle.studies.compilers.utils.grammartools.Grammar;
import org.poormanscastle.studies.compilers.utils.grammartools.Production;
import org.poormanscastle.studies.compilers.utils.grammartools.Symbol;

import static com.google.common.base.Preconditions.checkState;

/**
 * Grammar implementation for LR(0) languages.
 * <p/>
 * Created by georg on 09.02.16.
 */
public class LR0Grammar extends AbstractGrammar {

    private final Set<LRState> states = new HashSet<>();

    private final Set<LREdge> edges = new HashSet<>();

    @Override
    public Grammar initialize() {
        calculateStatesAndTransitions();
        return this;
    }

    public Set<LRState> getStates() {
        return Collections.unmodifiableSet(states);
    }

    public Set<LREdge> getEdges() {
        return Collections.unmodifiableSet(edges);
    }

    /**
     * calculates the LR(0) states for this LR(0) grammar.
     */
    public void calculateStatesAndTransitions() {
        states.clear();
        edges.clear();
        LRState startState = new LRState();
        startState.getItems().add(new LRItem(productions.get(0)));
        startState = calculateClosure(startState);
        states.add(startState);

        int oldSizeEdges, oldSizeStates;
        do {
            List<LRState> deltaList = new LinkedList<>();
            oldSizeEdges = edges.size();
            oldSizeStates = states.size();
            for (LRState sourceState : states) {
                for (LRItem item : sourceState.getItems()) {
                    if (!item.isReducible()) {
                        // For items with next symbol == EOF, no edges get calculated.
                        // if the parser follows and edge in the state machine, this results in a shift or goto action.
                        // EOF on the other hand should result in an accept action.
                        if (item.getNextSymbol() != Symbol.EOF) {
                            LRState targetState = calculateEdge(sourceState, item.getNextSymbol());
                            deltaList.add(targetState);
                            edges.add(new LREdge(sourceState, targetState, item.getNextSymbol()));
                        }
                    }
                }
            }
            states.addAll(deltaList);
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
            List<LRItem> deltaList = new LinkedList<>();
            oldSize = state.getItems().size();
            for (LRItem item : state.getItems()) {
                if (!item.isReducible() && !item.getNextSymbol().isTerminal()) {
                    for (Production production : productions) {
                        if (item.getNextSymbol() == production.getLhs()) {
                            deltaList.add(new LRItem(production));
                        }
                    }
                }
            }
            state.getItems().addAll(deltaList);
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
