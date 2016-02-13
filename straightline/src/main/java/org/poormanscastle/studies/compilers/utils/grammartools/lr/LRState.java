package org.poormanscastle.studies.compilers.utils.grammartools.lr;

import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Objects;

/**
 * In LR parsing an LR state is a set of items belonging to this state.
 * <p/>
 * Created by 02eex612 on 12.02.2016.
 */
public class LRState {

    private final Set<LRItem> items = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LRState lrState = (LRState) o;
        return Objects.equal(items, lrState.items);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(items);
    }

    public Set<LRItem> getItems() {
        return items;
    }

}
