package org.poormanscastle.studies.compilers.grammar;

/**
 * Created by georg on 02.12.15.
 */
public class PairExpList extends ExpList {

    private Exp head;
    private ExpList tail;

    public PairExpList(Exp h, ExpList t) {
        head = h;
        tail = t;
    }

    public Exp getHead() {
        return head;
    }

    public void setHead(Exp head) {
        this.head = head;
    }

    public ExpList getTail() {
        return tail;
    }

    public void setTail(ExpList tail) {
        this.tail = tail;
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

}


