package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import org.apache.commons.lang3.StringUtils;

import static com.google.common.base.Preconditions.checkArgument;

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
        checkArgument(!StringUtils.isBlank(id));
        checkArgument(expression != null);
        this.id = id;
        this.expression = expression;
    }

    public AssignmentStatement(String id, Expression expression) {
        this(expression.getCodePosition(), id, expression);
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
