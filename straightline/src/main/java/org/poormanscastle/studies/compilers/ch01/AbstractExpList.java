package org.poormanscastle.studies.compilers.ch01;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * represents a list of expressions.
 * the way this straightline language is defined, no other statements make use of ExpLists than print statements.
 * <p>
 * Created by georg on 02.12.15.
 */
public abstract class AbstractExpList implements ExpList {

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
