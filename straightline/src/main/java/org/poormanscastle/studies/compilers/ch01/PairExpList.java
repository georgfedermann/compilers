package org.poormanscastle.studies.compilers.ch01;

/**
 * Created by georg on 02.12.15.
 */
public class PairExpList extends AbstractExpList {

    private Exp head;
    private ExpList tail;

    public PairExpList(Exp h, ExpList t) {
        head = h;
        tail = t;
    }

    @Override
    public void accept(GrammarItemVisitor visitor) {
        visitor.visitPairExpList(this);
        if (head.handleProceedWith(visitor)) {
            head.accept(visitor);
        }
        if (tail.handleProceedWith(visitor)) {
            tail.accept(visitor);
        }
        visitor.leavePairExpList(this);
    }

    @Override
    public boolean handleProceedWith(GrammarItemVisitor visitor) {
        return visitor.proceedWithPairExpList(this);
    }

    @Override
    public Values evaluate(Values vats, MemoryTable memoryTable) {
        int value = head.evaluate(memoryTable);
        vats.appendValue(value);

        return tail.evaluate(vats, memoryTable);
    }
}


