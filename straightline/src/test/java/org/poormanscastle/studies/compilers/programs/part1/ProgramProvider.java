package org.poormanscastle.studies.compilers.programs.part1;

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
 * Provides programs implemented in the simple straight-line language.
 * Created by georg on 03.12.15.
 */
public class ProgramProvider {

    public static Stm getProgramA() {
        // a := 5 + 3; b := (print(a, a-1), 10 * a); print(b);
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

    public static Stm getProgramB() {
        // print((print(0)), 3, 5);
        return new PrintStm(new PairExpList(
                new EseqExp(new PrintStm(new LastExpList(new NumExp(0))), new NumExp(3)),
                new LastExpList(new NumExp(5))));
    }

}
