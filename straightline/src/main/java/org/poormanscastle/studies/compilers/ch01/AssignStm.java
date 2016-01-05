package org.poormanscastle.studies.compilers.ch01;

/**
 * represents an assignment statement in the grammar of a simple straight-line language.
 * an assignment statement consists of the id of a variable and an expression which gets
 * evaluated and whose value gets assigned to the given variable.
 * Created by georg on 02.12.15.
 */
public class AssignStm extends AbstractStm {
    private String id;
    private Exp exp;

    public AssignStm(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
        this.exp = exp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public void accept(GrammarItemVisitor visitor) {
        visitor.visitAssignStm(this);
        if (exp.handleProceedWith(visitor)) {
            exp.accept(visitor);
        }
        visitor.leaveAssignStm(this);
    }

    @Override
    public boolean handleProceedWith(GrammarItemVisitor visitor) {
        return visitor.proceedWithAssignStm(this);
    }

    @Override
    public void execute(MemoryTable table) {
        table.put(id, exp.evaluate(table));
    }
}
