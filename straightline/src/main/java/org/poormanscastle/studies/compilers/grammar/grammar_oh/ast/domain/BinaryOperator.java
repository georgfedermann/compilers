package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import java.util.Arrays;
import java.util.List;

import org.poormanscastle.studies.compilers.utils.grammartools.exceptions.CompilerException;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public enum BinaryOperator implements Operator {

    PLUS("+", OperatorCategory.ADDITIVE_ARITHMETIC, Type.INT, Type.DOUBLE, Type.TEXT),
    MINUS("-", OperatorCategory.ADDITIVE_ARITHMETIC, Type.INT, Type.DOUBLE),
    TIMES("*", OperatorCategory.MULTIPLICATIVE_ARITHMETIC, Type.INT, Type.DOUBLE),
    DIV("/", OperatorCategory.MULTIPLICATIVE_ARITHMETIC, Type.INT, Type.DOUBLE),
    XOR("xor", OperatorCategory.BITWISE_OR, Type.BOOLEAN),
    AND("AND", OperatorCategory.LOGICAL_AND, Type.BOOLEAN),
    OR("OR", OperatorCategory.LOGICAL_OR, Type.BOOLEAN),
    LT("<", OperatorCategory.RELATIONAL, Type.INT, Type.DOUBLE),
    LTE("<=", OperatorCategory.RELATIONAL, Type.INT, Type.DOUBLE),
    GT(">", OperatorCategory.RELATIONAL, Type.INT, Type.DOUBLE),
    GTE(">=", OperatorCategory.RELATIONAL, Type.INT, Type.DOUBLE),
    EQ("==", OperatorCategory.EQUALITY, Type.INT, Type.DOUBLE, Type.TEXT, Type.BOOLEAN),
    NEQ("!=", OperatorCategory.EQUALITY, Type.INT, Type.DOUBLE, Type.BOOLEAN, Type.TEXT);

    private String label;

    private List<Type> supportedTypes;

    private OperatorCategory operatorCategory;

    BinaryOperator(String label, OperatorCategory operatorCategory, Type... supportedTypes) {
        this.label = label;
        this.supportedTypes = Arrays.asList(supportedTypes);
        this.operatorCategory = operatorCategory;
    }

    public String getLabel() {
        return label;
    }

    public boolean supportsType(Type type) {
        return supportedTypes.contains(type);
    }

    @Override
    public Type getInferredType(Type operandType) {
        switch (operatorCategory) {
            case ADDITIVE_ARITHMETIC:
            case MULTIPLICATIVE_ARITHMETIC:
                return operandType;
            case LOGICAL_AND:
            case LOGICAL_OR:
            case LOGICAL_XOR:
            case RELATIONAL:
            case EQUALITY:
                return Type.BOOLEAN;
            default:
                throw new CompilerException("Unsupported Operator Category.");
        }
    }
}
