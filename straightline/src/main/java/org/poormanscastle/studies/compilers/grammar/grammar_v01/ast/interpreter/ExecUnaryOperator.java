package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.interpreter;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.Expression;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.Type;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.UnaryOperator;

/**
 * Created by 02eex612 on 23.02.2016.
 */
public interface ExecUnaryOperator {

    Object execute(Object operand);

    static ExecUnaryOperator getExecUnaryOperator(UnaryOperator operator, Expression operand) {
        Type type = operand.getValueType();
        switch (operator) {
            case NOT:
                switch (type) {
                    case BOOLEAN:
                        return new ExecOperatorNotBoolean();
                    default:
                }
            default:
                throw new RuntimeException(StringUtils.join("Unsupported unary operator: ", operator));
        }
    }
}
