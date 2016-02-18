package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public final class PairExpressionList extends AbstractAstItem implements ExpressionList {

    private final Expression expression;

    private final ExpressionList expressionList;

    public PairExpressionList(CodePosition codePosition, Expression expression, ExpressionList expressionList) {
        super(codePosition);
        this.expression = expression;
        this.expressionList = expressionList;
    }

    public PairExpressionList(Expression expression, ExpressionList expressionList) {
        super(expression.getCodePosition());
        this.expression = expression;
        this.expressionList = expressionList;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithPairExpressionList(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitPairExpressionList(this);
        if (expression.handleProceedWith(visitor)) {
            expression.accept(visitor);
        }
        if (expressionList.handleProceedWith(visitor)) {
            expressionList.accept(visitor);
        }
        visitor.leavePairExpressionList(this);
    }
}
