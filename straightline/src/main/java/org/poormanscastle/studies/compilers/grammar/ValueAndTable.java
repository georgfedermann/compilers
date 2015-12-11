package org.poormanscastle.studies.compilers.grammar;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * since EseqExp can execute statements and evaluate expressions, there is the chance of side effects at
 * each location where exp can be used. thus, an exp must always be able to return a reference to the new
 * memory table state. this type enables exps to return a value and a new memory table at the same time.
 * <p>
 * Created by georg on 11.12.15.
 */
public class ValueAndTable {

    private int value;

    private Table table;

    public ValueAndTable(int value, Table table) {
        this.value = value;
        this.table = table;
    }

    public int getValue() {
        return value;
    }

    public Table getTable() {
        return table;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
