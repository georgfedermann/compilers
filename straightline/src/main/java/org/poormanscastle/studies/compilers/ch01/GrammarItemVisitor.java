package org.poormanscastle.studies.compilers.ch01;

/**
 * imlements algorithms and logic that is to be applied to more or less all grammar items within
 * a given program. implementing classes should implement the visitor pattern.
 * <p>
 * Created by georg on 03.12.15.
 */
public interface GrammarItemVisitor {

    boolean proceedWithAssignStm(AssignStm stm);

    boolean proceedWithCompoundStm(CompoundStm stm);

    boolean proceedWithPrintStm(PrintStm stm);

    boolean proceedWithEseqExp(EseqExp exp);

    boolean proceedWithIdExp(IdExp exp);

    boolean proceedWithNumExp(NumExp numExp);

    boolean proceedWithOpExp(OpExp opExp);

    boolean proceedWithPairExpList(PairExpList expList);

    boolean proceedWithLastExpList(LastExpList expList);

    void visitAssignStm(AssignStm stm);

    void visitCompoundStm(CompoundStm stm);

    void visitPrintStm(PrintStm stm);

    void visitEseqExp(EseqExp exp);

    void visitIdExp(IdExp exp);

    void visitNumExp(NumExp exp);

    void visitOpExp(OpExp exp);

    void visitPairExpList(PairExpList expList);

    void visitLastExpList(LastExpList expList);

    void leaveAssignStm(AssignStm stm);

    void leaveCompoundStm(CompoundStm stm);

    void leavePrintStm(PrintStm stm);

    void leaveEseqExp(EseqExp exp);

    void leaveIdExp(IdExp exp);

    void leaveNumExp(NumExp exp);

    void leaveOpExp(OpExp exp);

    void leavePairExpList(PairExpList expList);

    void leaveLastExpList(LastExpList expList);

}
