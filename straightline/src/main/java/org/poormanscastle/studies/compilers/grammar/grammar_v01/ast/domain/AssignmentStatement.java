package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * assigns the value of a given expression to a memory location identified by an id string.
 * <p/>
 * Created by 02eex612 on 17.02.2016.
 */
public final class AssignmentStatement extends AbstractAstItem implements Statement {

    private final String id;

    private final Expression expression;

    public AssignmentStatement(CodePosition codePosition, String id, Expression expression) {
        super(codePosition);
        this.id = id;
        this.expression = expression;
    }

    public AssignmentStatement(String id, Expression expression) {
        super(expression.getCodePosition());
        this.id = id;
        this.expression = expression;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithAssignmentStatement(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitAssignmentStatement(this);
        if (expression.handleProceedWith(visitor)) {
            expression.accept(visitor);
        }
        visitor.leaveAssignmentStatement(this);
    }
}
