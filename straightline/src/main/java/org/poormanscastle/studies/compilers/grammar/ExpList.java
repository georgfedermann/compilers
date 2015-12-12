package org.poormanscastle.studies.compilers.grammar;

/**
 * Created by georg on 11.12.15.
 */
public interface ExpList extends GrammarItem {

    /**
     * implements type specific evaluation logic.
     *
     * @return
     */
    Values evaluate(Values vats, MemoryTable memoryTable);

}
