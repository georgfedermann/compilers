package org.poormanscastle.studies.compilers.grammar;

/**
 * Created by georg on 02.12.15.
 */
public class LastExpList extends AbstractExpList {
    public Exp head;

    public LastExpList(AbstractExp h) {
        head = h;
    }

    @Override
    public void accept(GrammarItemVisitor visitor) {
        visitor.visitLastExpList(this);
        if (head.handleProceedWith(visitor)) {
            head.accept(visitor);
        }
        visitor.leaveLastExpList(this);
    }

    @Override
    public boolean handleProceedWith(GrammarItemVisitor visitor) {
        return visitor.proceedWithLastExpList(this);
    }

    @Override
    public ValuesAndTable evaluate(ValuesAndTable vats) {
        ValueAndTable vat = head.evaluate(vats.getTable());
        vats.appendValue(vat.getValue());
        vats.setTable(vat.getTable());
        return vats;
    }
}
