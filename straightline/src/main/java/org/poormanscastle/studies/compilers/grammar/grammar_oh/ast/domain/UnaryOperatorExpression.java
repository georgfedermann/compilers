package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public final class UnaryOperatorExpression extends AbstractExpression implements Expression {

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

    public UnaryOperator getOperator() {
        return operator;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public Type getValueType() {
        checkState(expression.getValueType() != null);
        return operator.getInferredType(expression.getValueType());
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
