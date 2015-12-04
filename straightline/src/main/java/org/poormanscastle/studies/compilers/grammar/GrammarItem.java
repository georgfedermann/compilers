package org.poormanscastle.studies.compilers.grammar;

/**
 * a grammar item is any of the symbols of the simple straightline language defined in this package.
 * <p>
 * this interface makes those symbols traversible by a visitor, more specific visitors implementing
 * the GrammarItemVisitor.
 * <p>
 * Created by georg on 03.12.15.
 */
public interface GrammarItem {

    /**
     * delegates the decision whether the given visitor shall visit this object to the host object.
     * this method can decide what to do in case a visitor comes knocking, how to point the visitor
     * to other elements in the object hierarchy, or choose to remain empty.
     *
     * @param visitor
     */
    void accept(GrammarItemVisitor visitor);

    boolean handleProceedWith(GrammarItemVisitor visitor);

}
