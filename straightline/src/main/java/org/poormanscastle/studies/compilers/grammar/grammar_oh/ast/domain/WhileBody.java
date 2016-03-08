package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 02eex612 on 08.03.2016.
 */
public class WhileBody extends AbstractAstItem implements Statement {

    private final Statement nestedStatement;

    private final WhileStatement parentStatement;

    public WhileBody(CodePosition codePosition, WhileStatement parentStatement, Statement nestedStatement) {
        super(codePosition);
        checkNotNull(parentStatement);
        checkNotNull(nestedStatement);
        this.nestedStatement = nestedStatement;
        this.parentStatement = parentStatement;
    }

    public WhileBody(WhileStatement parentStatement, Statement nestedStatement) {
        this(nestedStatement.getCodePosition(), parentStatement, nestedStatement);
    }

    public Statement getNestedStatement() {
        return nestedStatement;
    }

    public WhileStatement getParentStatement() {
        return parentStatement;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithWhileBody(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitWhileBody(this);
        if (nestedStatement.handleProceedWith(visitor)) {
            nestedStatement.accept(visitor);
        }
        visitor.leaveWhileBody(this);
    }
}
