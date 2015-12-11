package org.poormanscastle.studies.compilers.grammar;

/**
 * Created by georg on 02.12.15.
 */
public class EseqExp extends AbstractExp {
    private Stm stm;
    private Exp exp;

    public EseqExp(Stm stm, Exp exp) {
        this.stm = stm;
        this.exp = exp;
    }

    @Override
    public void accept(GrammarItemVisitor visitor) {
        visitor.visitEseqExp(this);
        if (stm.handleProceedWith(visitor)) {
            stm.accept(visitor);
        }
        if (exp.handleProceedWith(visitor)) {
            exp.accept(visitor);
        }
        visitor.leaveEseqExp(this);
    }

    @Override
    public boolean handleProceedWith(GrammarItemVisitor visitor) {
        return visitor.proceedWithEseqExp(this);
    }

    @Override
    public ValueAndTable evaluate(Table table) {
        table = stm.execute(table);
        return exp.evaluate(table);
    }
}
