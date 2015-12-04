package org.poormanscastle.studies.compilers.grammar;

/**
 * Created by georg on 02.12.15.
 */
public class EseqExp extends Exp {
    private Stm stm;
    private Exp exp;

    public EseqExp(Stm stm, Exp exp) {
        this.stm = stm;
        this.exp = exp;
    }

    public Stm getStm() {
        return stm;
    }

    public void setStm(Stm stm) {
        this.stm = stm;
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
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

}