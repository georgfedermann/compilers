package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Created by georg on 13.01.16.
 */
public abstract class AbstractX implements X {

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
