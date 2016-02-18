package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import java.io.Serializable;

/**
 * an AstItem is part of an abstract syntax tree representing a sentence that can be
 * validated against a given context-free regular grammar.
 * <p/>
 * additionally, this interface supports traversability for implementations of the AstVisitor,
 * implementing different aspects of the AstTree, as semantic analysis, IR tree production,
 * method frame production, etc.
 * <p/>
 * Created by georg on 13.01.16.
 */
public interface AstItem extends Serializable {

    /**
     * delegates the decision whether the given visitor shall visit this object to the host object.
     * this method can decide what to do in case a visitor comes knocking, how to point the visitor
     * to child elements in the tree hierarchy, or choose to remain empty.
     *
     * @param visitor visitor like in visitor pattern
     */
    void accept(AstItemVisitor visitor);

    /**
     * AstItems can have several child elements of various implementations of the same interface, like
     * OpPlus, OpMinus, OpTimes, OpDiv all implementing the Op interface. Implementing one method
     * proceedWith(AstItem) or only proceedWith(Op) to let the visitor decide whether to proceed
     * with the given item or operator implementation will not work, since method overloading in Java
     * only support static polymorphism, i.e. static binding. So, for each implementation of AstItem or
     * Op, one and the same method for the static type will be called. So, the visitor will have to
     * do instanceof checks and type casts and switch logic, which results in ugly code.
     * To avoid all of these, each AstItem implements handleProceedWith(AstVisitor). Now, the implementation
     * of this method will be resolved via dynamic polymorphism, once again delegating the decision logic
     * to dynamic binding and freeing the implementation of instanceof checks, type casts and switch logic.
     *
     * @param visitor visitor like in visitor pattern
     * @return returns {@code true} if the visitor shall proceed with this item, {@code false} otherwise.
     */
    boolean handleProceedWith(AstItemVisitor visitor);

    CodePosition getCodePosition();
}
