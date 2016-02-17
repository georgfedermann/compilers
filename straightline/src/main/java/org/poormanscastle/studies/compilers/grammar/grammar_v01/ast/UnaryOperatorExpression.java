package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public final class UnaryOperatorExpression extends AbstractAstItem implements Expression {

    private UnaryOperator operator;

    private Expression expression;

    public UnaryOperatorExpression(CodePosition codePosition, UnaryOperator operator, Expression expression) {
        super(codePosition);
        this.operator = operator;
        this.expression = expression;
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
