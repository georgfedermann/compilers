package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

/**
 * an expression can be evaluated to a value of the various types defined by the given grammar.
 * <p/>
 * Created by 02eex612 on 17.02.2016.
 */
public interface Expression extends AstItem {

    /**
     * can be used to query the state of the given expression.
     *
     * @return
     */
    ExpressionState getState();

    /**
     * @return the type of this expression's value. For the types BooleanExpression, NumExpression, TextExpression
     * and DecimalExpression, the return value is statically implemented in the expression. For IdExpressions, the
     * value must be looked up in the symbol table. For OperatorExpressions, the value is inferred from their sub
     * expressions.
     */
    Type getValueType();

}
