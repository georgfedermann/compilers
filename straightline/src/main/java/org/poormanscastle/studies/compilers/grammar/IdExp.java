package org.poormanscastle.studies.compilers.grammar;

/**
 * An id expression gives the name of a variable to whose value it evalutes.
 * Created by georg on 02.12.15.
 */
public class IdExp extends AbstractExp {
    private String id;

    public IdExp(String id) {
        this.id = id;
    }

    @Override
    public void accept(GrammarItemVisitor visitor) {
        visitor.visitIdExp(this);
        visitor.leaveIdExp(this);
    }

    @Override
    public boolean handleProceedWith(GrammarItemVisitor visitor) {
        return visitor.proceedWithIdExp(this);
    }

    @Override
    public ValueAndTable evaluate(Table table) {
        return new ValueAndTable(table.lookup(id), table);
    }
}
