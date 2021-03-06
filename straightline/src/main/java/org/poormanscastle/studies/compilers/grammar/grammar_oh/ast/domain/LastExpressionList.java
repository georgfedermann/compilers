package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public final class LastExpressionList extends AbstractAstItem implements ExpressionList {

    private final Expression expression;

    public LastExpressionList(CodePosition codePosition, Expression expression) {
        super(codePosition);
        checkNotNull(expression != null);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public LastExpressionList(Expression expression) {
        this(expression.getCodePosition(), expression);
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithLastExpressionList(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitLastExpressionList(this);
        if (expression.handleProceedWith(visitor)) {
            expression.accept(visitor);
        }
        visitor.leaveLastExpressionList(this);
    }
}
