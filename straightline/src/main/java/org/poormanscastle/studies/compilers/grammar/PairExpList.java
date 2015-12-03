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
        visitor.visit(this);
        if (visitor.proceedWith(head)) {
            head.accept(visitor);
        }
        if (visitor.proceedWith(tail)) {
            tail.accept(visitor);
        }
    }
}


