package org.poormanscastle.studies.compilers.grammar;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * represents an expression in the grammar of a simple straight-line language.
 * Created by georg on 02.12.15.
 */
public abstract class Exp implements GrammarItem {

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
