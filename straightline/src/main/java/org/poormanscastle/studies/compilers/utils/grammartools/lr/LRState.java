package org.poormanscastle.studies.compilers.utils.grammartools.lr;

import java.util.HashSet;
import java.util.Set;

import org.poormanscastle.studies.compilers.utils.grammartools.Production;

import com.google.common.base.Objects;

/**
 * In LR parsing an LR state is a set of items belonging to this state.
 * <p/>
 * Created by 02eex612 on 12.02.2016.
 */
public class LRState {

    private static long sequence = 0;

    private final Set<LRItem> items = new HashSet<>();

    /**
     * an id identifying this state. This id will be used as an id in the parser table and can be used to create
     * visual representations of the state machine.
     */
    private final long id;

    public LRState() {
        this.id = ++LRState.sequence;
    }

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

    /**
     * returns a String representation of this state, consisting of the production strings of it's items' productions.
     *
     * @return a string that can be used for visual representations of this String.
     */
    public String getStateAsText() {
        StringBuilder result = new StringBuilder();
        for (LRItem item : items) {
            Production production = item.getProduction();
            result.append(production.getLhs().getId()).append(" -> ");
            for (int c = 0; c < production.getRhs().size(); c++) {
                if (c == item.getPosition()) {
                    result.append(". ");
                }
                result.append(production.getRhs().get(c).getId()).append(" ");
            }
            result.append(item.getPosition() == item.getProduction().getRhs().size() ? "." : "" + "\\n");
        }
        return result.toString();
    }

    public long getId() {
        return id;
    }
}
