package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * makes control return to call point.
 * <p/>
 * takes an expression. the corresponding function call will have assigned the value of this expression.
 * <p/>
 * Created by 02eex612 on 11.03.2016.
 */
public class ReturnStatement extends AbstractAstItem implements Statement {

    private final Expression expression;

    public ReturnStatement(CodePosition codePosition, Expression expression) {
        super(codePosition);
        checkNotNull(expression);
        this.expression = expression;
    }

    public ReturnStatement(Expression expression) {
        this(expression.getCodePosition(), expression);
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithReturnStatement(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitReturnStatement(this);
        if (expression.handleProceedWith(visitor)) {
            expression.accept(visitor);
        }
        visitor.leaveReturnStatement(this);
    }
}
