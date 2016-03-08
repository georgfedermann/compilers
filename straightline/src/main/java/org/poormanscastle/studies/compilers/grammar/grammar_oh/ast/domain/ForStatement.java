package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * Created by 02eex612 on 08.03.2016.
 */
public class ForStatement extends AbstractAstItem implements Statement {

    private final Statement initializationStatement;

    private final Expression condition;

    private final Statement incrementStatement;

    private final Statement forBody;

    public ForStatement(CodePosition codePosition, Statement initializationStatement, Expression condition,
                        Statement incrementStatement, Statement bodyStatement) {
        super(codePosition);
        this.initializationStatement = initializationStatement;
        this.condition = condition;
        this.incrementStatement = incrementStatement;
        this.forBody = bodyStatement;
    }

    public ForStatement(Statement initStatement, Expression condition, Statement incrStatement, Statement body) {
        this(initStatement.getCodePosition(), initStatement, condition, incrStatement, body);
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getInitializationStatement() {
        return initializationStatement;
    }

    public Statement getForBody() {
        return forBody;
    }

    public Statement getIncrementStatement() {
        return incrementStatement;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithForStatement(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitForStatement(this);
        if (initializationStatement.handleProceedWith(visitor)) {
            initializationStatement.accept(visitor);
        }
        if (condition.handleProceedWith(visitor)) {
            condition.accept(visitor);
        }
        if (incrementStatement.handleProceedWith(visitor)) {
            incrementStatement.accept(visitor);
        }
        if (forBody.handleProceedWith(visitor)) {
            forBody.accept(visitor);
        }
        visitor.leaveForStatement(this);
    }
}
