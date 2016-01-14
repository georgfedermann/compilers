package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

/**
 * performs division on its operands.
 * Created by georg on 14.01.16.
 */
public class DivOperator extends AbstractAstItem implements FactorOperator {

    public DivOperator(CodePosition codePosition) {
        super(codePosition);
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithDivOperator(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitDivOperator(this);
        visitor.leaveDivOperator(this);
    }
    
}
