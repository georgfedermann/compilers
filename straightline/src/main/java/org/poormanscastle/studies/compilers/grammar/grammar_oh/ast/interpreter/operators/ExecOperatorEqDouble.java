package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.interpreter.operators;

/**
 * Created by 02eex612 on 23.02.2016.
 */
public class ExecOperatorEqDouble implements ExecBinaryOperator {
    @Override
    public Object execute(Object lhs, Object rhs) {
        return lhs.equals(rhs);
    }
}
