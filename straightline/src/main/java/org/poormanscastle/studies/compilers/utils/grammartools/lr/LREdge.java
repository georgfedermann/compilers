package org.poormanscastle.studies.compilers.utils.grammartools.lr;

import org.poormanscastle.studies.compilers.utils.grammartools.Symbol;

/**
 * an edge is a transition from sourceState to targetState induced by symbol X.
 * <p/>
 * Created by 02eex612 on 12.02.2016.
 */
public class LREdge {

    private final LRState sourceState;

    private final LRState targetState;

    private final Symbol symbol;

    public LREdge(LRState sourceState, LRState targetState, Symbol symbol) {
        this.sourceState = sourceState;
        this.targetState = targetState;
        this.symbol = symbol;
    }
}
