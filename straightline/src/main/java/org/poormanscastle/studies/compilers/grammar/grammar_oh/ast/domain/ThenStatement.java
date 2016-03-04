package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * I couldn't think of any other solution to resolve the matter of branch selection for different visitors:
 * <p/>
 * symbolTableCreator/expressionValidator will want to visit all branches of a conditional statement.
 * <p/>
 * the interpreter will only want to visit one branch or no branch at all depending on the truth value of the
 * conditional statement's boolean expression, and whether the conditional statement has an else statement or not.
 * <p/>
 * thus, when visitor.proceedWith(Statement) is called, the visitor needs to know that this is not just any old
 * Statment, but a thenStatement or elseStatement. SymbolTableCreator and ExpressionValidator will proceed any way,
 * the interpreter can make an educated decision.
 * <p/>
 * Created by 02eex612 on 03.03.2016.
 */
public class ThenStatement extends AbstractAstItem implements Statement {

    private final Statement nestedStatement;

    private final ConditionalStatement parentStatement;

    public ThenStatement(CodePosition codePosition, ConditionalStatement parentStatement, Statement nestedStatement) {
        super(codePosition);
        this.parentStatement = parentStatement;
        this.nestedStatement = nestedStatement;
    }

    public ThenStatement(ConditionalStatement parentStatement, Statement nestedStatement) {
        this(parentStatement.getCodePosition(), parentStatement, nestedStatement);
    }

    public ConditionalStatement getParentStatement() {
        return parentStatement;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithThenStatement(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitThenStatement(this);
        if (nestedStatement.handleProceedWith(visitor)) {
            nestedStatement.accept(visitor);
        }
        visitor.leaveThenStatement(this);
    }
}
