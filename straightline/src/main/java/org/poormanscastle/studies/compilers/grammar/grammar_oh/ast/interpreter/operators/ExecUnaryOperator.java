package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.interpreter.operators;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Expression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Type;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.UnaryOperator;
import org.poormanscastle.studies.compilers.utils.grammartools.exceptions.CompilerException;

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
                throw new CompilerException(StringUtils.join("Unsupported unary operator: ", operator));
        }
    }
}
