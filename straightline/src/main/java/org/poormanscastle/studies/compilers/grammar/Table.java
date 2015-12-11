package org.poormanscastle.studies.compilers.grammar;

import org.apache.commons.lang3.StringUtils;

/**
 * memory table representation, mapping identifiers to integer values.
 * Created by georg on 11.12.15.
 */
public class Table {

    /**
     * variable name
     */
    private String id;
    /**
     * variable value
     */
    private int value;
    /**
     * next item in the memory abstraction
     */
    private Table next;

    public Table(String id, int value, Table nextEntry) {
        this.id = id;
        this.value = value;
        this.next = nextEntry;
    }

    /**
     * add a new variable to this memory table or update the value of an existing memory item.
     *
     * @param id
     * @param value
     * @return
     */
    public Table update(String id, int value) {
        return new Table(id, value, this);
    }

    /**
     * search the memory table for a variable name and return the associated value.
     *
     * @param id
     * @return
     */
    public int lookup(String id) {
        Table table = this;
        while (!table.id.equals(id) && table.next != null) {
            table = table.next;
        }
        if (table.id.equals(id)) {
            return table.value;
        }
        throw new IllegalArgumentException(StringUtils.join("No variable ", id, " found in memory."));
    }

}
