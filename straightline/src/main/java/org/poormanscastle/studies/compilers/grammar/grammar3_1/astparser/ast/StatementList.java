package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * Created by georg on 15.01.16.
 */
public class StatementList extends AbstractAstItem implements Statement {

    private final Statement statement;

    private final StatementList nextStatements;

    public StatementList(CodePosition codePosition, Statement statement, StatementList nextStatements) {
        super(codePosition);
        this.statement = statement;
        this.nextStatements = nextStatements;
    }

    public Statement getStatement() {
        return statement;
    }

    public StatementList getNextStatements() {
        return nextStatements;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithStatementList(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitStatementList(this);
        if (statement.handleProceedWith(visitor)) {
            statement.accept(visitor);
        }
        if (nextStatements != null && nextStatements.handleProceedWith(visitor)) {
            nextStatements.accept(visitor);
        }
        visitor.leaveStatementList(this);
    }

}
