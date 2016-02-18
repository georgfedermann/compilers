package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public final class UnaryOperatorExpression extends AbstractAstItem implements Expression {

    private final UnaryOperator operator;

    private final Expression expression;

    public UnaryOperatorExpression(CodePosition codePosition, UnaryOperator operator, Expression expression) {
        super(codePosition);
        checkArgument(operator != null);
        checkArgument(expression != null);
        this.operator = operator;
        this.expression = expression;
    }

    public UnaryOperatorExpression(UnaryOperator operator, Expression expression) {
        this(expression.getCodePosition(), operator, expression);
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithUnaryOperatorExpression(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitUnaryOperatxorExpression(this);
        if (expression.handleProceedWith(visitor)) {
            expression.accept(visitor);
        }
        visitor.leaveUnaryOperatorExpression(this);
    }
}
