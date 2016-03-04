package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public final class BinaryOperatorExpression extends AbstractExpression<Object> {

    private final Expression lhs;
    private final BinaryOperator operator;
    private final Expression rhs;

    /**
     * to be read and written by the interpreter.
     */
    private Object value;

    public BinaryOperatorExpression(CodePosition codePosition, Expression lhs, BinaryOperator operator, Expression rhs) {
        super(codePosition);
        checkArgument(lhs != null);
        checkArgument(rhs != null);
        checkArgument(operator != null);
        this.lhs = lhs;
        this.operator = operator;
        this.rhs = rhs;
    }

    @Override
    public Object getValue() throws UnsupportedOperationException {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
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
