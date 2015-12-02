package org.poormanscastle.studies.compilers.grammer;

/**
 * An id expression gives the name of a variable to whose value it evalutes.
 * Created by georg on 02.12.15.
 */
public class IdExp extends Exp {
    private String id;

    public IdExp(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
