package org.poormanscastle.studies.compilers.ch01;

/**
 * basic definition of a memory table.
 * Created by georg on 12.12.15.
 */
public interface MemoryTable {

    /**
     * add a new variable to this memory table or update the value of an existing memory item.
     *
     * @param id
     * @param value
     * @return
     */
    void put(String id, int value);

    /**
     * search the memory table for a variable name and return the associated value.
     *
     * @param id
     * @return
     */
    int lookup(String id);

}
