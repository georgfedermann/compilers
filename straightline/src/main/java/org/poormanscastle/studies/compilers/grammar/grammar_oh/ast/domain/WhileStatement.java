package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 02eex612 on 08.03.2016.
 */
public class WhileStatement extends AbstractAstItem implements Statement {

    private final Expression condition;

    private final WhileBody whileBody;

    public WhileStatement(CodePosition codePosition, Expression condition, Statement statement) {
        super(codePosition);
        checkNotNull(codePosition);
        checkNotNull(condition);
        checkNotNull(statement);
        this.condition = condition;
        this.whileBody = new WhileBody(this, statement);
    }

    public WhileStatement(Expression condition, Statement statement) {
        this(condition.getCodePosition(), condition, statement);
    }

    public Expression getCondition() {
        return condition;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithWhileStatement(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitWhileStatement(this);
        if (condition.handleProceedWith(visitor)) {
            condition.accept(visitor);
        }
        if (whileBody.handleProceedWith(visitor)) {
            whileBody.accept(visitor);
        }
        visitor.leaveWhileStatement(this);
    }
}
