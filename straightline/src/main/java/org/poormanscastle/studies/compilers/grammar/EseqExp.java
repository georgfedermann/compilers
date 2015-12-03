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
        visitor.visit(this);
        if (visitor.proceedWith(stm)) {
            stm.accept(visitor);
        }
        if (visitor.proceedWith(exp)) {
            exp.accept(visitor);
        }
        visitor.leave(this);
    }

}
