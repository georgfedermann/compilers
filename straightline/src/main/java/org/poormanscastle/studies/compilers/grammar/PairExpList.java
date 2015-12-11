package org.poormanscastle.studies.compilers.grammar;

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
    public ValuesAndTable evaluate(ValuesAndTable vats) {
        ValueAndTable vat = head.evaluate(vats.getTable());
        vats.appendValue(vat.getValue());
        vats.setTable(vat.getTable());

        return tail.evaluate(vats);
    }
}


