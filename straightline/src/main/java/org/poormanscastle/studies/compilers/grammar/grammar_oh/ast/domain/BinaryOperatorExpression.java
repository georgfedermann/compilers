package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public final class BinaryOperatorExpression extends AbstractExpression implements Expression {

    private final Expression lhs;
    private final BinaryOperator operator;
    private final Expression rhs;

    public BinaryOperatorExpression(CodePosition codePosition, Expression lhs, BinaryOperator operator, Expression rhs) {
        super(codePosition);
        checkArgument(lhs != null);
        checkArgument(rhs != null);
        checkArgument(operator != null);
        this.lhs = lhs;
        this.operator = operator;
        this.rhs = rhs;
    }

    public BinaryOperatorExpression(Expression lhs, BinaryOperator operator, Expression rhs) {
        this(lhs.getCodePosition(), lhs, operator, rhs);
    }

    @Override
    public Type getValueType() {
        // validity is tracked during expression validation, but e.g. not any more during interpretation phase.
        // therefore the next check has to be done by the ExpressionValidator, and is not an invariant to the Expression.
        // TODO is this correct?
        // checkState(getState() == ExpressionState.VALID);
        checkNotNull(lhs.getValueType());
        checkNotNull(rhs.getValueType());
        checkNotNull(operator);
        return operator.getInferredType(Type.getInferredType(lhs.getValueType(), rhs.getValueType()));
    }

    public Expression getLhs() {
        return lhs;
    }

    public BinaryOperator getOperator() {
        return operator;
    }

    public Expression getRhs() {
        return rhs;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithBinaryOperatorExpression(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitBinaryOperatorExpression(this);
        if (lhs.handleProceedWith(visitor)) {
            lhs.accept(visitor);
        }
        if (rhs.handleProceedWith(visitor)) {
            rhs.accept(visitor);
        }
        visitor.leaveBinaryOperatorExpression(this);
    }
}
