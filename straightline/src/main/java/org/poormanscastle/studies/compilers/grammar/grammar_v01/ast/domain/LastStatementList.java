package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public final class LastStatementList extends AbstractAstItem implements StatementList {

    private final Statement statement;

    public LastStatementList(CodePosition codePosition, Statement statement) {
        super(codePosition);
        checkArgument(statement != null);
        this.statement = statement;
    }

    public LastStatementList(Statement statement) {
        this(statement.getCodePosition(), statement);
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithLastStatementList(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitLastStatementList(this);

        if (statement.handleProceedWith(visitor)) {
            statement.accept(visitor);
        }
        visitor.leaveLastStatementList(this);
    }
}
