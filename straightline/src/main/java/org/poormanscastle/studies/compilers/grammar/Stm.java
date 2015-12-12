package org.poormanscastle.studies.compilers.grammar;

/**
 * Created by georg on 11.12.15.
 */
public interface Stm extends GrammarItem{

    /**
     * implements the Stm specific execution logic.
     */
    void execute(MemoryTable table);

}
