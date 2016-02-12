package org.poormanscastle.studies.compilers.utils.grammartools.lr;

import org.poormanscastle.studies.compilers.utils.grammartools.Production;
import org.poormanscastle.studies.compilers.utils.grammartools.Symbol;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

/**
 * Used in parsing of LR grammars, an item is a production (grammar rule) combined with a marker
 * that indicates the current position of the parser within the production.
 * <p/>
 * Created by 02eex612 on 12.02.2016.
 */
public class LRItem {

    private final Production production;

    private final int position;

    public LRItem(Production production) {
        this(production, 0);
    }

    public LRItem(Production production, int position) {
        checkArgument(production != null);
        checkArgument(position >= 0 && position <= production.getRhs().size());
        this.production = production;
        this.position = position;
    }

    /**
     * get the next symbol, directly to the right of the dot in the rhs of this item's production.
     * it's only legal to call this method on an item which is not already reducible. So, check
     * reducible before calling this method. (The other option would be to return a NullPointer, which
     * you also would have to check.)
     *
     * @return
     */
    public Symbol getNextSymbol() {
        checkState(position >= 0);
        checkState(position < production.getRhs().size());
        return production.getRhs().get(position);
    }

    /**
     * an item shall be reducible, if the dot marker is positioned to the right of the last rhs symbol
     * of this item's production.
     * <p/>
     * after all rhs symbols in this item have been consumed, the parse can choose to reduce this production.
     *
     * @return
     */
    public boolean isReducible() {
        checkState(position >= 0);
        checkState(position <= production.getRhs().size());
        return !(position < production.getRhs().size());
    }

    public int getPosition() {
        return position;
    }

    public Production getProduction() {
        return production;
    }

}
