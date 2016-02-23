package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.interpreter;

/**
 * Created by 02eex612 on 23.02.2016.
 */
public class ExecOperatorNotBoolean implements ExecUnaryOperator {
    @Override
    public Object execute(Object operand) {
        return !(Boolean) operand;
    }
}
