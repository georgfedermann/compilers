package org.poormanscastle.studies.compilers.grammar;

/**
 * represents an assignment statement in the grammar of a simple straight-line language.
 * an assignment statement consists of the id of a variable and an expresseion which gets
 * evaluated and whose value gets assigned to the given variable.
 * Created by georg on 02.12.15.
 */
public class AssignStm extends Stm {
    private String id;
    private Exp exp;

    public AssignStm(String id, Exp exp){
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
}
