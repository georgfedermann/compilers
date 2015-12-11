package org.poormanscastle.studies.compilers.grammar;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by georg on 11.12.15.
 */
public class ValuesAndTable {

    private List<Integer> values = new LinkedList<>();

    private Table table;

    public ValuesAndTable(Table table) {
        this.table = table;
    }

    public List<Integer> getValues() {
        return values;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void appendValue(int value) {
        values.add(value);
    }

}
