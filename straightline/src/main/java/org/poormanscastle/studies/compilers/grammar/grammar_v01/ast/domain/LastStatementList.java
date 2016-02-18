package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public final class LastStatementList extends AbstractAstItem implements StatementList {

    private final Statement statement;

    public LastStatementList(CodePosition codePosition, Statement statement) {
        super(codePosition);
        this.statement = statement;
    }

    public LastStatementList(Statement statement) {
        super(statement.getCodePosition());
        this.statement = statement;
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
