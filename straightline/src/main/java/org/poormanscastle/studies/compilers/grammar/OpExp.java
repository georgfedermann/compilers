package org.poormanscastle.studies.compilers.grammar;

/**
 * the operator expression is kind of a compound expression consisting of two expressions which
 * will be combined by the given binary operator.
 * <p>
 * Created by georg on 02.12.15.
 */
public class OpExp extends Exp {
    private Exp left, right;
    private int operator;
    public final static int PLUS = 1, MINUS = 3, TIMES = 3, DIV = 4;

    public OpExp(Exp left, int operator, Exp right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }
}
