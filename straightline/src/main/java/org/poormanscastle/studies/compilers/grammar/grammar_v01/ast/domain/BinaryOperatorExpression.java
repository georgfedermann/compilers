package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public final class BinaryOperatorExpression extends AbstractAstItem implements Expression {

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
