package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import java.util.Arrays;
import java.util.List;

import org.poormanscastle.studies.compilers.utils.grammartools.exceptions.CompilerException;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public enum UnaryOperator implements Operator {

    NOT("!", OperatorCategory.UNARY, Type.BOOLEAN);

    private String label;

    private List<Type> supportedTypes;

    private OperatorCategory operatorCategory;

    UnaryOperator(String label, OperatorCategory operatorCategory, Type... supportedTypes) {
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
            case UNARY:
                return operandType;
            default:
                throw new CompilerException("Unsupported Operator Category.");
        }
    }
}
