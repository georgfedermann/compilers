package org.poormanscastle.studies.compilers.ch01;

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

    /**
     * method overloading in the GrammarItemVisitor interface does not support dynamic polymorphism.
     * Thus one cannot call visitor.proceedWith(Exp) with subtypes of Exp. Static binding will always
     * call the method for the supertype Exp. So, for each implementation of Exp a different version
     * of the overloaded method has to be called explicitely, which is inefficient and results in
     * convoluted code.
     * to avoid application of if/else and instanceof each type implements handleProceedWith(Visitor),
     * which will be resolved using dynamic polymorphism, thus relieving the system from most of the
     * decision logic.
     *
     * @param visitor
     * @return
     */
    boolean handleProceedWith(GrammarItemVisitor visitor);

}
