package org.poormanscastle.studies.compilers.programs;

import org.poormanscastle.studies.compilers.grammar.AssignStm;
import org.poormanscastle.studies.compilers.grammar.CompoundStm;
import org.poormanscastle.studies.compilers.grammar.EseqExp;
import org.poormanscastle.studies.compilers.grammar.IdExp;
import org.poormanscastle.studies.compilers.grammar.LastExpList;
import org.poormanscastle.studies.compilers.grammar.NumExp;
import org.poormanscastle.studies.compilers.grammar.OpExp;
import org.poormanscastle.studies.compilers.grammar.PairExpList;
import org.poormanscastle.studies.compilers.grammar.PrintStm;
import org.poormanscastle.studies.compilers.grammar.Stm;

/**
 * Created by georg on 03.12.15.
 */
public class HelloWorld {

    public Stm getProgram() {
        return new CompoundStm(
                new AssignStm("a", new OpExp(new NumExp(5), OpExp.PLUS, new NumExp(3))),
                new CompoundStm(
                        new AssignStm("b",
                                new EseqExp(new PrintStm(new PairExpList(new IdExp("a"),
                                        new LastExpList(
                                                new OpExp(new IdExp("a"), OpExp.MINUS, new NumExp(1))))),
                                        new OpExp(new NumExp(10), OpExp.TIMES, new IdExp("a")))
                        ), new PrintStm(new LastExpList(new IdExp("b")))));
    }

    /**
     * calculates and returns the max number of arguments for any given
     * print statement contained inside the input parameter stm.
     *
     * @param stm
     * @return the maximum number of arguments to any print statement contained within
     * the program wrapped inside the stm input parameter.
     */
    public int maxArgs(Stm stm) {
        // look at stm
        // if stm is not a print stm, iterate over its children
        // if stm is a print stm iterate over its children wich are ExpList instances and count them for good.
        // Each ExpList implementation counts for 1 here, because PairExp will have one printable expression, and
        // another ExpList, which will either be another PairExpList or a LastExpList, which will have one printable
        // child expression. voila.

        return 0;
    }

    public static void main(String[] args) {
        HelloWorld myApp = new HelloWorld();
        System.out.println(myApp.maxArgs(myApp.getProgram()));
    }

}
