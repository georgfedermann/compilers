package org.poormanscastle.studies.compilers.ch01;

/**
 * Created by georg on 11.12.15.
 */
public interface Exp extends GrammarItem {

    /**
     * implements type specific evaluation logic.
     *
     * @return
     */
    int evaluate(MemoryTable table);

}
