package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

/**
 * Created by 02eex612 on 22.02.2016.
 */
public enum ExpressionState {

    /**
     * operands are compatible, i.e. they have equal types, or there types can be cast to fit.
     * e.g. boolean and text, or double and int.
     * <p/>
     * operator and operands are compatible.
     */
    VALID,
    /**
     * the operands are of different types and they cannot be cast to fit. e.g. boolean and int
     */
    OPERANDS_INCOMPATIBLE,
    /**
     * the operator cannot handle the given operand(s). e.g. * and text
     */
    OPERATOR_INCOMPATIBLE,
    /**
     * one or more of the expression's operands are invalid.
     */
    OPERANDS_INVALID,
    /**
     * the state of this expression has not been determined yet.
     * The Semantic phase has not been executed (fully), yet.
     */
    NOT_DETERMINED_YET;

}
