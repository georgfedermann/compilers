package org.poormanscastle.studies.compilers.grammer;

/**
 * represents a Compound Statement in the grammer of the simple straight-line language.
 * a compound statement consists of two statements separated by a semicolon ; .
 *
 * Created by georg on 02.12.15.
 */
public class CompoundStm extends Stm {
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
}
