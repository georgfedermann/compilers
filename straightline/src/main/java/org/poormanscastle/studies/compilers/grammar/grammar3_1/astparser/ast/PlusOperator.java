package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

/**
 * adds up its operands.
 * Created by georg on 14.01.16.
 */
public class PlusOperator extends AbstractAstItem implements TermOperator {

    public PlusOperator(CodePosition codePosition) {
        super(codePosition);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitPlusOperator(this);
        visitor.leavePlusOperator(this);
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithPlusOperator(this);
    }

}
