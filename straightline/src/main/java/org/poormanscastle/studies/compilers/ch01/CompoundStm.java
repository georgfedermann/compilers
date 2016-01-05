package org.poormanscastle.studies.compilers.ch01;

/**
 * represents a Compound Statement in the grammar of the simple straight-line language.
 * a compound statement consists of two statements separated by a semicolon ; .
 * <p>
 * Created by georg on 02.12.15.
 */
public class CompoundStm extends AbstractStm {
    private Stm stm1, stm2;

    public CompoundStm(Stm s1, Stm s2) {
        stm1 = s1;
        stm2 = s2;
    }

    public Stm getStm1() {
        return stm1;
    }

    public void setStm1(Stm stm1) {
        this.stm1 = stm1;
    }

    public Stm getStm2() {
        return stm2;
    }

    public void setStm2(Stm stm2) {
        this.stm2 = stm2;
    }

    @Override
    public void accept(GrammarItemVisitor visitor) {
        visitor.visitCompoundStm(this);
        if (stm1.handleProceedWith(visitor)) {
            stm1.accept(visitor);
        }
        if (stm2.handleProceedWith(visitor)) {
            stm2.accept(visitor);
        }
        visitor.leaveCompoundStm(this);
    }

    @Override
    public boolean handleProceedWith(GrammarItemVisitor visitor) {
        return visitor.proceedWithCompoundStm(this);
    }

    @Override
    public void execute(MemoryTable table) {
        stm1.execute(table);
        stm2.execute(table);
    }
}
