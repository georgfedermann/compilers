package org.poormanscastle.studies.compilers.grammar;

/**
 * a print statement prints all of the arguemnts that are given in a comma separated list.
 * Created by georg on 02.12.15.
 */
public class PrintStm extends Stm {
    public ExpList exps;

    public PrintStm(ExpList e) {
        exps = e;
    }

    public ExpList getExps() {
        return exps;
    }

    public void setExps(ExpList exps) {
        this.exps = exps;
    }

    @Override
    public void accept(GrammarItemVisitor visitor) {
        visitor.visitPrintStm(this);
        if (exps.handleProceedWith(visitor)) {
            exps.accept(visitor);
        }
        visitor.leavePrintStm(this);
    }

    @Override
    public boolean handleProceedWith(GrammarItemVisitor visitor) {
        return visitor.proceedWithPrintStm(this);
    }

}