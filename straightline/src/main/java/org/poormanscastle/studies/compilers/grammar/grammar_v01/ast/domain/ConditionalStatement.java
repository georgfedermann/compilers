package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 02eex612 on 26.02.2016.
 */
public class ConditionalStatement extends AbstractAstItem implements Statement {

    private final Expression condition;

    private final Statement thenStatement;

    /**
     * elseStatement may remain {@code null} if the conditional statement has no else part.
     */
    private final Statement elseStatement;

    public ConditionalStatement(CodePosition codePosition, Expression condition, Statement thenStatement, Statement elseStatement) {
        super(codePosition);
        checkNotNull(condition);
        checkNotNull(thenStatement);
        this.condition = condition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public ConditionalStatement(Expression condition, Statement thenStatement, Statement elseStatement) {
        this(condition.getCodePosition(), condition, thenStatement, elseStatement);
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithConditionalStatement(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitConditionalStatement(this);
        if (condition.handleProceedWith(visitor)) {
            condition.accept(visitor);
        }
        if (thenStatement.handleProceedWith(visitor)) {
            thenStatement.accept(visitor);
        }
        if (elseStatement != null && elseStatement.handleProceedWith(visitor)) {
            elseStatement.accept(visitor);
        }
        visitor.leaveConditionalStatement(this);
    }
}
