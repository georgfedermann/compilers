package org.poormanscastle.studies.compilers.grammar;

/**
 * a num expression gives an integer to which evaluates.
 * Created by georg on 02.12.15.
 */
public class NumExp extends Exp {

    private int num;

    public NumExp(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void accept(GrammarItemVisitor visitor) {
        visitor.visitNumExp(this);
        visitor.leaveNumExp(this);
    }

    @Override
    public boolean handleProceedWith(GrammarItemVisitor visitor) {
        return visitor.proceedWithNumExp(this);
    }

}
