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

    public static Stm getProgramC() {
        // g := 17 * 4; h := 11 + (print( g, (i := g - 1, (print(1, 2, 3, 4, 5, 6), 8 + g)), g ), g - g); print(g, h, 5, g - h, i);
        return new CompoundStm(
                // g:= 17 * 4
                new AssignStm("g", new OpExp(new NumExp(17),OpExp.TIMES, new NumExp(4))),
                // h := 11 + (...); print(g, h, 5, g - h, i);
                new CompoundStm(
                        // h := 11 + (...);
                        new AssignStm("h", new OpExp(new NumExp(11), OpExp.PLUS,
                                // (print( g, (i := g - 1, (print(1, 2, 3, 4, 5, 6), 8 + g)), g ), g - g)
                                new EseqExp(new PrintStm(new PairExpList(new IdExp("g"), new PairExpList(
                                        // (i := g - 1, (print(1, 2, 3, 4, 5, 6), 8 + g)), g )
                                        new EseqExp(new AssignStm("i", new OpExp(new IdExp("g"), OpExp.MINUS, new NumExp(1))), new EseqExp(new PrintStm(
                                                new PairExpList(new NumExp(1), new PairExpList(new NumExp(2), new PairExpList(new NumExp(3), new PairExpList(new NumExp(4), new PairExpList(new NumExp(5), new LastExpList(new NumExp(6)))))))
                                        ),
                                                // 8 + g
                                                new OpExp(new NumExp(8), OpExp.PLUS, new IdExp("g")))),
                                new LastExpList(new IdExp("g"))))),
                                        // g - g
                                        new OpExp(new IdExp("g"), OpExp.MINUS, new IdExp("g"))))),
                        // print(g, h, 5, g-h, i)
                        new PrintStm(new PairExpList(new IdExp("g"), new PairExpList(new IdExp("h"), new PairExpList(new NumExp(5), new PairExpList(new OpExp(new IdExp("g"), OpExp.MINUS, new IdExp("h")), new LastExpList(new IdExp("i")))))))
                )
        );
    }

}
