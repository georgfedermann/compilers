package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

/**
 * Created by georg on 15.01.16.
 */
public class PrintStatement extends AbstractAstItem implements Statement {

    private final ExpressionList expressionList;

    public PrintStatement(CodePosition codePosition, ExpressionList expressionList) {
        super(codePosition);
        this.expressionList = expressionList;
    }

    public ExpressionList getExpressionList() {
        return expressionList;
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
