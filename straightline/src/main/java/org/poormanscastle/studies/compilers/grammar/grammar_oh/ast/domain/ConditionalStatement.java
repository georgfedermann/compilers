package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * Created by 02eex612 on 26.02.2016.
 */
public class ConditionalStatement extends AbstractAstItem implements Statement {

    private final Expression condition;

    private final ThenStatement thenStatement;

    /**
     * elseStatement may remain {@code null} if the conditional statement has no else part.
     */
    private final ElseStatement elseStatement;

    public ConditionalStatement(CodePosition codePosition, Expression condition, Statement thenStatement, Statement elseStatement) {
        super(codePosition);
        checkNotNull(condition);
        checkNotNull(thenStatement);
        this.condition = condition;
        this.thenStatement = new ThenStatement(this, thenStatement);
        if (elseStatement != null) {
            this.elseStatement = new ElseStatement(this, elseStatement);
        } else {
            this.elseStatement = null;
        }
    }

    public ConditionalStatement(Expression condition, Statement thenStatement, Statement elseStatement) {
        this(condition.getCodePosition(), condition, thenStatement, elseStatement);
    }

    public ConditionalStatement(Expression condition, Statement thenStatement) {
        this(condition.getCodePosition(), condition, thenStatement, null);
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
        // the decision, whether the Statements thenStatement and elseStatement shall be visited is a difficult one
        // and cannot be decided in Statement.proceedWith(visitor).
        // SymbolTableCreator/ExpressionValidator will have to visit them in any case. Interpreter will only want
        // to visit one or none based on the value of condition and if there is an elseStatement.
        if (thenStatement.handleProceedWith(visitor)) {
            thenStatement.accept(visitor);
        }
        if (elseStatement != null && elseStatement.handleProceedWith(visitor)) {
            elseStatement.accept(visitor);
        }
        visitor.leaveConditionalStatement(this);
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getElseStatement() {
        return elseStatement;
    }

    public Statement getThenStatement() {
        return thenStatement;
    }

}
