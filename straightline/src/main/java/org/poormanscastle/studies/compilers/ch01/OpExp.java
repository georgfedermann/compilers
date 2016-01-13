package org.poormanscastle.studies.compilers.ch01;

/**
 * the operator expression is kind of a compound expression consisting of two expressions which
 * will be combined by the given binary operator.
 * <p>
 * Created by georg on 02.12.15.
 */
public class OpExp extends AbstractExp {
    private Exp left, right;
    private Operator operator;

    public OpExp(Exp left, Operator operator, Exp right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public void accept(GrammarItemVisitor visitor) {
        visitor.visitOpExp(this);
        if (left.handleProceedWith(visitor)) {
            left.accept(visitor);
        }
        if (right.handleProceedWith(visitor)) {
            right.accept(visitor);
        }
        visitor.leaveOpExp(this);
    }

    @Override
    public boolean handleProceedWith(GrammarItemVisitor visitor) {
        return visitor.proceedWithOpExp(this);
    }

    @Override
    public int evaluate(MemoryTable table) {
        int valueLeft = left.evaluate(table);
        int valueRight = right.evaluate(table);
        return operator.operate(valueLeft, valueRight);
    }
}
