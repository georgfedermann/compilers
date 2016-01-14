package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

/**
 * performs multiplication on its operands.
 * Created by georg on 14.01.16.
 */
public class TimesOperator extends AbstractAstItem implements FactorOperator {

    public TimesOperator(CodePosition codePosition) {
        super(codePosition);
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithTimesOperator(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitTimesOperator(this);
        visitor.leaveTimesOperator(this);
    }

}
