package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public enum BinaryOperator {
    PLUS("+", Type.INT, Type.DOUBLE, Type.TEXT), MINUS("-", Type.INT, Type.DOUBLE), TIMES("*", Type.INT, Type.DOUBLE),
    DIV("/", Type.INT, Type.DOUBLE), XOR("xor", Type.BOOLEAN), AND("AND", Type.BOOLEAN), OR("OR", Type.BOOLEAN),
    LT("<", Type.INT, Type.DOUBLE), LTE("<=", Type.INT, Type.DOUBLE), GT(">", Type.INT, Type.DOUBLE),
    GTE(">=", Type.INT, Type.DOUBLE), EQ("==", Type.INT, Type.DOUBLE, Type.TEXT, Type.BOOLEAN),
    NEQ("!=", Type.INT, Type.DOUBLE, Type.BOOLEAN, Type.TEXT);

    private String label;

    private List<Type> supportedTypes;

    BinaryOperator(String label, Type... supportedTypes) {
        this.label = label;
        this.supportedTypes = Arrays.asList(supportedTypes);
    }

    public String getLabel() {
        return label;
    }

    public boolean supportsType(Type type) {
        return supportedTypes.contains(type);
    }
}
