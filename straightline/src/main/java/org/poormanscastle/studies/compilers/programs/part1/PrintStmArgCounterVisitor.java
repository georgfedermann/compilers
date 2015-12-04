package org.poormanscastle.studies.compilers.programs.part1;

import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.grammar.AssignStm;
import org.poormanscastle.studies.compilers.grammar.CompoundStm;
import org.poormanscastle.studies.compilers.grammar.EseqExp;
import org.poormanscastle.studies.compilers.grammar.GrammarItemVisitor;
import org.poormanscastle.studies.compilers.grammar.IdExp;
import org.poormanscastle.studies.compilers.grammar.LastExpList;
import org.poormanscastle.studies.compilers.grammar.NumExp;
import org.poormanscastle.studies.compilers.grammar.OpExp;
import org.poormanscastle.studies.compilers.grammar.PairExpList;
import org.poormanscastle.studies.compilers.grammar.PrintStm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * this type counts the args to print statements. it makes use of the fact that no other statements (can) make use of
 * the ExpList type other than print statements. this results from the ways this straightline language's grammar is
 * defined.
 * <p>
 * Created by georg on 03.12.15.
 */
public class PrintStmArgCounterVisitor implements GrammarItemVisitor {

    private final static Logger logger = LoggerFactory.getLogger(PrintStmArgCounterVisitor.class);

    private Stack<PrintContext> printContexts = new Stack<>();

    /**
     * if the visitor finds a printer statement, it creates a printer and assigns it to this field, to increment its
     * args number while finding ExpList entries below the printer.
     */
    private PrintContext currentPrintContext = null;

    @Override
    public void visitPrintStm(PrintStm stm) {
        currentPrintContext = new PrintContext();
    }

    @Override
    public void leavePrintStm(PrintStm stm) {
        printContexts.push(currentPrintContext);
        currentPrintContext = null;
    }

    @Override
    public boolean proceedWithAssignStm(AssignStm stm) {
        return true;
    }

    @Override
    public boolean proceedWithCompoundStm(CompoundStm stm) {
        return true;
    }

    @Override
    public boolean proceedWithPrintStm(PrintStm stm) {
        return true;
    }

    @Override
    public boolean proceedWithEseqExp(EseqExp exp) {
        return true;
    }

    @Override
    public boolean proceedWithIdExp(IdExp exp) {
        logger.debug(StringUtils.join("Ignoring IdExp since it cannot hold print statements: ", exp.toString()));
        return false;
    }

    @Override
    public boolean proceedWithNumExp(NumExp exp) {
        logger.debug(StringUtils.join("Ignoring NumExp since it cannot hold print statements: ", exp.toString()));
        return true;
    }

    @Override
    public boolean proceedWithOpExp(OpExp exp) {
        return true;
    }

    @Override
    public boolean proceedWithPairExpList(PairExpList expList) {
        return true;
    }

    @Override
    public boolean proceedWithLastExpList(LastExpList expList) {
        return true;
    }

    @Override
    public void visitAssignStm(AssignStm stm) {

    }

    @Override
    public void visitCompoundStm(CompoundStm stm) {

    }

    @Override
    public void visitEseqExp(EseqExp exp) {

    }

    @Override
    public void visitIdExp(IdExp exp) {

    }

    @Override
    public void visitNumExp(NumExp exp) {

    }

    @Override
    public void visitOpExp(OpExp exp) {

    }

    @Override
    public void visitPairExpList(PairExpList expList) {

    }

    @Override
    public void visitLastExpList(LastExpList expList) {

    }

    @Override
    public void leaveAssignStm(AssignStm stm) {

    }

    @Override
    public void leaveCompoundStm(CompoundStm stm) {

    }

    @Override
    public void leaveEseqExp(EseqExp exp) {

    }

    @Override
    public void leaveIdExp(IdExp exp) {

    }

    @Override
    public void leaveNumExp(NumExp exp) {

    }

    @Override
    public void leaveOpExp(OpExp exp) {

    }

    @Override
    public void leavePairExpList(PairExpList expList) {

    }

    @Override
    public void leaveLastExpList(LastExpList expList) {

    }

    public Stack<PrintContext> getPrintContexts() {
        return printContexts;
    }

    public PrintContext getCurrentPrintContext() {
        return currentPrintContext;
    }
}
