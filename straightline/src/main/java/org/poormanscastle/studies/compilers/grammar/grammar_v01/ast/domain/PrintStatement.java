package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * the PrintStatement prints the value of each expression in an ExpressionList.
 * <p/>
 * Created by 02eex612 on 17.02.2016.
 */
public final class PrintStatement extends AbstractAstItem implements Statement {

    private final ExpressionList expressionList;

    public PrintStatement(CodePosition codePosition, ExpressionList expressionList) {
        super(codePosition);
        this.expressionList = expressionList;
    }

    public PrintStatement(ExpressionList expressionList) {
        super(expressionList.getCodePosition());
        this.expressionList = expressionList;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithPrintStatement(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitPrintStatement(this);
        if (expressionList.handleProceedWith(visitor)) {
            expressionList.accept(visitor);
        }
        visitor.leavePrintStatement(this);
    }
}
