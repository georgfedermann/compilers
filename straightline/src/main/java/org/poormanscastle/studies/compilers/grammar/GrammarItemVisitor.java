package org.poormanscastle.studies.compilers.grammar;

/**
 * imlements algorithms and logic that is to be applied to more or less all grammar items within
 * a given program. implementing classes should implement the visitor pattern.
 * <p>
 * Created by georg on 03.12.15.
 */
public interface GrammarItemVisitor {

    boolean proceedWith(Stm stm);

    boolean proceedWith(AssignStm stm);

    boolean proceedWith(CompoundStm stm);

    boolean proceedWith(PrintStm stm);

    boolean proceedWith(Exp exp);

    boolean proceedWith(EseqExp exp);

    boolean proceedWith(IdExp exp);

    boolean proceedWith(NumExp numExp);

    boolean proceedWith(OpExp opExp);

    boolean proceedWith(ExpList expList);

    boolean proceedWith(PairExpList expList);

    boolean proceedWith(LastExpList expList);

    void visit(Stm stm);

    void visit(AssignStm stm);

    void visit(CompoundStm stm);

    void visit(PrintStm stm);

    void visit(Exp exp);

    void visit(EseqExp exp);

    void visit(IdExp exp);

    void visit(NumExp exp);

    void visit(OpExp exp);

    void visit(ExpList expList);

    void visit(PairExpList expList);

    void visit(LastExpList expList);

    void leave(Stm stm);

    void leave(AssignStm stm);

    void leave(CompoundStm stm);

    void leave(PrintStm stm);

    void leave(Exp exp);

    void leave(EseqExp exp);

    void leave(IdExp exp);

    void leave(NumExp exp);

    void leave(OpExp exp);

    void leave(ExpList expList);

    void leave(PairExpList expList);

    void leave(LastExpList expList);

}
