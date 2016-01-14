package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

/**
 * Created by georg on 14.01.16.
 */
public class MinusOperator extends AbstractAstItem implements TermOperator {

    public MinusOperator(CodePosition codePosition) {
        super(codePosition);
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithMinusOperator(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitMinusOperator(this);
        visitor.leaveMinusOperator(this);
    }

}
