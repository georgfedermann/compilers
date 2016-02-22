package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public enum UnaryOperator {

    NOT("!", Type.BOOLEAN);

    private String label;

    private List<Type> supportedTypes;

    UnaryOperator(String label, Type... types) {
        this.label = label;
        supportedTypes = Arrays.asList(types);
    }

    public String getLabel() {
        return label;
    }

    public boolean supportsType(Type type) {
        return supportedTypes.contains(type);
    }
}
