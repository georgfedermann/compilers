package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * Created by 02eex612 on 03.03.2016.
 */
public class ElseStatement extends AbstractAstItem implements Statement {

    private final Statement nestedStatement;

    private final ConditionalStatement parentStatement;

    public ElseStatement(CodePosition codePosition, ConditionalStatement parentStatement, Statement nestedStatement) {
        super(codePosition);
        this.nestedStatement = nestedStatement;
        this.parentStatement = parentStatement;
    }

    public ElseStatement(ConditionalStatement parentStatement, Statement nestedStatement) {
        this(parentStatement.getCodePosition(), parentStatement, nestedStatement);
    }


    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithElseStatement(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitElseStatement(this);
        if(nestedStatement.handleProceedWith(visitor)){
            nestedStatement.accept(visitor);
        }
        visitor.leaveElseStatement(this);
    }
}
