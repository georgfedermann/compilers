package org.poormanscastle.studies.compilers.programs;

import org.poormanscastle.studies.compilers.grammar.*;

/**
 * Provides programs implemented in the simple straight-line language.
 * Created by georg on 03.12.15.
 */
public class ProgramProvider {

    public static AbstractStm getProgramA() {
        // a := 5 + 3; b := (print(a, a-1), 10 * a); print(b);
        // expected output: 8 7 80
        return new CompoundStm(
                new AssignStm("a", new OpExp(new NumExp(5), Operator.PLUS, new NumExp(3))),
                new CompoundStm(
                        new AssignStm("b",
                                new EseqExp(new PrintStm(new PairExpList(new IdExp("a"),
                                        new LastExpList(
                                                new OpExp(new IdExp("a"), Operator.MINUS, new NumExp(1))))),
                                        new OpExp(new NumExp(10), Operator.TIMES, new IdExp("a")))
                        ), new PrintStm(new LastExpList(new IdExp("b")))));
    }

    public static AbstractStm getProgramB() {
        // print((print(0), 3), 5);
        // expected output: 0 3 5
        return new PrintStm(new PairExpList(
                new EseqExp(new PrintStm(new LastExpList(new NumExp(0))), new NumExp(3)),
                new LastExpList(new NumExp(5))));
    }

    public static AbstractStm getProgramC() {
        // g := 17 * 4; h := 11 + (print( g, (i := g - 1, (print(1, 2, 3, 4, 5, 6), 8 + g)), g ), g - g); print(g, h, 5, g - h, i);
        // expected output: 1 2 3 4 5 6 68 76 68 68 11 5 57 67
        // g 68
        // h 11+0
        // i 67
        return new CompoundStm(
                // g:= 17 * 4
                new AssignStm("g", new OpExp(new NumExp(17), Operator.TIMES, new NumExp(4))),
                // h := 11 + (...); print(g, h, 5, g - h, i);
                new CompoundStm(
                        // h := 11 + (...);
                        new AssignStm("h", new OpExp(new NumExp(11), Operator.PLUS,
                                // (print( g, (i := g - 1, (print(1, 2, 3, 4, 5, 6), 8 + g)), g ), g - g)
                                new EseqExp(new PrintStm(new PairExpList(new IdExp("g"), new PairExpList(
                                        // (i := g - 1, (print(1, 2, 3, 4, 5, 6), 8 + g)), g )
                                        new EseqExp(new AssignStm("i", new OpExp(new IdExp("g"), Operator.MINUS, new NumExp(1))), new EseqExp(new PrintStm(
                                                new PairExpList(new NumExp(1), new PairExpList(new NumExp(2), new PairExpList(new NumExp(3), new PairExpList(new NumExp(4), new PairExpList(new NumExp(5), new LastExpList(new NumExp(6)))))))
                                        ),
                                                // 8 + g
                                                new OpExp(new NumExp(8), Operator.PLUS, new IdExp("g")))),
                                        new LastExpList(new IdExp("g"))))),
                                        // g - g
                                        new OpExp(new IdExp("g"), Operator.MINUS, new IdExp("g"))))),
                        // print(g, h, 5, g-h, i)
                        new PrintStm(new PairExpList(new IdExp("g"), new PairExpList(new IdExp("h"), new PairExpList(new NumExp(5), new PairExpList(new OpExp(new IdExp("g"), Operator.MINUS, new IdExp("h")), new LastExpList(new IdExp("i")))))))
                )
        );
    }

}
