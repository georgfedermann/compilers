package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkArgument;
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
        checkState(getState() == ExpressionState.VALID);
        checkState(lhs.getValueType() != null);
        checkState(rhs.getValueType() != null);
        // TODO the operator also plays a part in inferring the resulting data type. In fact, the inferring should
        // be implemented completely within the operator, because operators like ==, <, <=, >, >= will first have
        // to cast operands according to casting rules, but the resulting return type may be something else completely.
        // e.g. in 3.5 <= 7, the int will be cast to double, but the return value type will be boolean.
        return Type.getInferredType(lhs.getValueType(), rhs.getValueType());
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
