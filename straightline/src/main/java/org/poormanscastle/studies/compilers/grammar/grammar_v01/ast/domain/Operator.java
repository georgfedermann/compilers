package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

/**
 * Operators abstract operations on operands, like arithmetic operators, logical operators, text operators, etc.
 * <p/>
 * Operators can be overloaded to operate on different types, like + operates on num, dec and text values.
 * <p/>
 * There are binary and unary operators.
 * <p/>
 * Operators can return the same value type as their operands, or different ones. like == operates on
 * all data types but always returns boolean values. < operates on int and double and returns boolean.
 * <p/>
 * Created by 02eex612 on 23.02.2016.
 */
public interface Operator {

    /**
     * this method delivers the value type of the expression using this operator.
     * the compiler will see to it that the operands will be cast to be of the same type (if
     * compatible) before the operator is executed, thus there is only on input parameter
     * in this method.
     * <p/>
     * e.g. called on the Operator == with input type Type.TEXT will return Type.BOOLEAN.
     *
     * @param operandType
     * @return
     */
    Type getInferredType(Type operandType);

}
