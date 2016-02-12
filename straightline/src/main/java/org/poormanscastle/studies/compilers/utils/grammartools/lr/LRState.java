package org.poormanscastle.studies.compilers.utils.grammartools.lr;

import java.util.HashSet;
import java.util.Set;

/**
 * In LR parsing an LR state is a set of items belonging to this state.
 * <p/>
 * Created by 02eex612 on 12.02.2016.
 */
public class LRState {

    private final Set<LRItem> items = new HashSet<>();

    public Set<LRItem> getItems() {
        return items;
    }

}
