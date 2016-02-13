package org.poormanscastle.studies.compilers.utils.grammartools.lr;

import org.poormanscastle.studies.compilers.utils.grammartools.Symbol;

import com.google.common.base.Objects;

/**
 * an edge is a transition from sourceState to targetState induced by symbol X.
 * <p/>
 * Created by 02eex612 on 12.02.2016.
 */
public class LREdge {

    private final LRState sourceState;

    private final LRState targetState;

    private final Symbol symbol;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LREdge lrEdge = (LREdge) o;
        return Objects.equal(sourceState, lrEdge.sourceState) &&
                Objects.equal(targetState, lrEdge.targetState) &&
                Objects.equal(symbol, lrEdge.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sourceState, targetState, symbol);
    }

    public LREdge(LRState sourceState, LRState targetState, Symbol symbol) {
        this.sourceState = sourceState;
        this.targetState = targetState;
        this.symbol = symbol;
    }
}
