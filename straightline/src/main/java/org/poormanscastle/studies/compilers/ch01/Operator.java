package org.poormanscastle.studies.compilers.ch01;

/**
 * Created by georg on 11.12.15.
 */
public interface Operator {

    Operator PLUS = new OpPlus();

    Operator MINUS = new OpMinus();

    Operator TIMES = new OpTimes();

    Operator DIV = new OpDiv();

    int operate(int left, int right);

}
