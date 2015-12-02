package org.poormanscastle.studies.compilers.grammer;

/**
 * Created by georg on 02.12.15.
 */
public class LastExpList extends ExpList {
    public Exp head;

    public LastExpList(Exp h) {
        head = h;
    }

    public Exp getHead() {
        return head;
    }

    public void setHead(Exp head) {
        this.head = head;
    }
}
