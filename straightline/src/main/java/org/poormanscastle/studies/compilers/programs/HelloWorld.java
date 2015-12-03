package org.poormanscastle.studies.compilers.programs;

import org.poormanscastle.studies.compilers.grammer.AssignStm;
import org.poormanscastle.studies.compilers.grammer.CompoundStm;
import org.poormanscastle.studies.compilers.grammer.EseqExp;
import org.poormanscastle.studies.compilers.grammer.IdExp;
import org.poormanscastle.studies.compilers.grammer.LastExpList;
import org.poormanscastle.studies.compilers.grammer.NumExp;
import org.poormanscastle.studies.compilers.grammer.OpExp;
import org.poormanscastle.studies.compilers.grammer.PairExpList;
import org.poormanscastle.studies.compilers.grammer.PrintStm;
import org.poormanscastle.studies.compilers.grammer.Stm;

/**
 * Created by georg on 03.12.15.
 */
public class HelloWorld {

    public static void main(String[] args) {
        Stm program = new CompoundStm(
                new AssignStm("a", new OpExp(new NumExp(5), OpExp.PLUS, new NumExp(3))),
                new CompoundStm(
                        new AssignStm("b",
                                new EseqExp(new PrintStm(new PairExpList(new IdExp("a"),
                                        new LastExpList(
                                                new OpExp(new IdExp("a"), OpExp.MINUS, new NumExp(1))))),
                                        new OpExp(new NumExp(10), OpExp.TIMES, new IdExp("a")))
                        ), new PrintStm(new LastExpList(new IdExp("b")))));
    }

}
