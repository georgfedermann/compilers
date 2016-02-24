package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.interpreter;

/**
 * Created by 02eex612 on 23.02.2016.
 */
public class ExecOperatorLtInt implements ExecBinaryOperator {
    @Override
    public Object execute(Object lhs, Object rhs) {
        return ((Number) lhs).intValue() < ((Number) rhs).intValue();
    }
}
