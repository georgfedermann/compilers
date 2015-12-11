package org.poormanscastle.studies.compilers.grammar;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * represents a Statement in the grammar of the straight-line language.
 * <p>
 * Created by georg on 02.12.15.
 */
public abstract class AbstractStm implements Stm {

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
