package org.poormanscastle.studies.compilers.programs.part1;

import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.grammar.AssignStm;
import org.poormanscastle.studies.compilers.grammar.CompoundStm;
import org.poormanscastle.studies.compilers.grammar.EseqExp;
import org.poormanscastle.studies.compilers.grammar.Exp;
import org.poormanscastle.studies.compilers.grammar.ExpList;
import org.poormanscastle.studies.compilers.grammar.GrammarItemVisitor;
import org.poormanscastle.studies.compilers.grammar.IdExp;
import org.poormanscastle.studies.compilers.grammar.LastExpList;
import org.poormanscastle.studies.compilers.grammar.NumExp;
import org.poormanscastle.studies.compilers.grammar.OpExp;
import org.poormanscastle.studies.compilers.grammar.PairExpList;
import org.poormanscastle.studies.compilers.grammar.PrintStm;
import org.poormanscastle.studies.compilers.grammar.Stm;
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
    public void visit(PrintStm stm) {
        currentPrintContext = new PrintContext();
    }

    @Override
    public void leave(PrintStm stm) {
        printContexts.push(currentPrintContext);
        currentPrintContext = null;
    }

    @Override
    public boolean proceedWith(Stm stm) {
        throw new RuntimeException(StringUtils.join("Add an implementation for the type ", stm.getClass().getName()));
    }

    @Override
    public boolean proceedWith(AssignStm stm) {
        return true;
    }

    @Override
    public boolean proceedWith(CompoundStm stm) {
        return true;
    }

    @Override
    public boolean proceedWith(PrintStm stm) {
        return true;
    }

    @Override
    public boolean proceedWith(Exp exp) {
        throw new RuntimeException(StringUtils.join("Add an implementation for the type ", exp.getClass().getName()));
    }

    @Override
    public boolean proceedWith(EseqExp exp) {
        return true;
    }

    @Override
    public boolean proceedWith(IdExp exp) {
        logger.debug(StringUtils.join("Ignoring IdExp since it cannot hold print statements: ", exp.toString()));
        return false;
    }

    @Override
    public boolean proceedWith(NumExp exp) {
        logger.debug(StringUtils.join("Ignoring NumExp since it cannot hold print statements: ", exp.toString()));
        return true;
    }

    @Override
    public boolean proceedWith(OpExp exp) {
        return true;
    }

    @Override
    public boolean proceedWith(ExpList expList) {
        throw new RuntimeException(StringUtils.join("Add an implementation for the type ", expList.getClass().getName()));
    }

    @Override
    public boolean proceedWith(PairExpList expList) {
        return true;
    }

    @Override
    public boolean proceedWith(LastExpList expList) {
        return true;
    }

    @Override
    public void visit(Stm stm) {
        throw new RuntimeException(StringUtils.join("Add an implementation for the type ", stm.getClass().getName()));
    }

    @Override
    public void visit(AssignStm stm) {

    }

    @Override
    public void visit(CompoundStm stm) {

    }

    @Override
    public void visit(Exp exp) {
        throw new RuntimeException(StringUtils.join("Add an implementation for the type ", exp.getClass().getName()));
    }

    @Override
    public void visit(EseqExp exp) {

    }

    @Override
    public void visit(IdExp exp) {

    }

    @Override
    public void visit(NumExp exp) {

    }

    @Override
    public void visit(OpExp exp) {

    }

    @Override
    public void visit(ExpList expList) {
        throw new RuntimeException(StringUtils.join("Add an implementation for the type ", expList.getClass().getName()));
    }

    @Override
    public void visit(PairExpList expList) {

    }

    @Override
    public void visit(LastExpList expList) {

    }

    @Override
    public void leave(Stm stm) {
        throw new RuntimeException(StringUtils.join("Add an implementation for the type ", stm.getClass().getName()));
    }

    @Override
    public void leave(AssignStm stm) {

    }

    @Override
    public void leave(CompoundStm stm) {

    }

    @Override
    public void leave(Exp exp) {
        throw new RuntimeException(StringUtils.join("Add an implementation for the type ", exp.getClass().getName()));
    }

    @Override
    public void leave(EseqExp exp) {

    }

    @Override
    public void leave(IdExp exp) {

    }

    @Override
    public void leave(NumExp exp) {

    }

    @Override
    public void leave(OpExp exp) {

    }

    @Override
    public void leave(ExpList expList) {
        throw new RuntimeException(StringUtils.join("Add an implementation for the type ", expList.getClass().getName()));
    }

    @Override
    public void leave(PairExpList expList) {

    }

    @Override
    public void leave(LastExpList expList) {

    }

    public Stack<PrintContext> getPrintContexts() {
        return printContexts;
    }

    public PrintContext getCurrentPrintContext() {
        return currentPrintContext;
    }
}
