package org.poormanscastle.studies.compilers.ch01;

/**
 * Created by georg on 11.12.15.
 */
public class OpDiv implements Operator {
    @Override
    public int operate(int left, int right) {
        return left / right;
    }
}
