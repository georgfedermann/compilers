package org.poormanscastle.studies.compilers.grammar;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by georg on 11.12.15.
 */
public class Values {

    private List<Integer> values = new LinkedList<>();

    public List<Integer> getValues() {
        return values;
    }

    public void appendValue(int value) {
        values.add(value);
    }

}
