package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.interpreter.operators;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.BinaryOperator;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Expression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Type;
import org.poormanscastle.studies.compilers.utils.grammartools.exceptions.CompilerException;

/**
 * this type is actually capable of executing operations on operands defined by operators.
 * <p/>
 * Created by 02eex612 on 23.02.2016.
 */
public interface ExecBinaryOperator {

    Object execute(Object lhs, Object rhs);

    static ExecBinaryOperator getExecOperator(BinaryOperator operator, Expression lhs, Expression rhs) {
        Type type = Type.getInferredType(lhs.getValueType(), rhs.getValueType());
        switch (operator) {
            case PLUS:
                switch (type) {
                    case INT:
                        return new ExecOperatorPlusInt();
                    case DOUBLE:
                        return new ExecOperatorPlusDouble();
                    case TEXT:
                        return new ExecOperatorPlusText();
                }
            case MINUS:
                switch (type) {
                    case INT:
                        return new ExecOperatorMinusInt();
                    case DOUBLE:
                        return new ExecOperatorMinusDouble();
                }
            case TIMES:
                switch (type) {
                    case INT:
                        return new ExecOperatorTimesInt();
                    case DOUBLE:
                        return new ExecOperatorTimesDouble();
                }
            case DIV:
                switch (type) {
                    case INT:
                        return new ExecOperatorDivInt();
                    case DOUBLE:
                        return new ExecOperatorDivDouble();
                }
            case XOR:
                return new ExecOperatorXorBoolean();
            case AND:
                return new ExecOperatorAndBoolean();
            case OR:
                return new ExecOperatorOrBoolean();
            case LT:
                switch (type) {
                    case INT:
                        return new ExecOperatorLtInt();
                    case DOUBLE:
                        return new ExecOperatorLtDouble();
                }
            case LTE:
                switch (type) {
                    case INT:
                        return new ExecOperatorLteInt();
                    case DOUBLE:
                        return new ExecOperatorLteDouble();
                }
            case GT:
                switch (type) {
                    case INT:
                        return new ExecOperatorGtInt();
                    case DOUBLE:
                        return new ExecOperatorGtDouble();
                }
            case GTE:
                switch (type) {
                    case INT:
                        return new ExecOperatorGteInt();
                    case DOUBLE:
                        return new ExecOperatorGteDouble();
                }
            case EQ:
                switch (type) {
                    case BOOLEAN:
                        return new ExecOperatorEqBoolean();
                    case INT:
                        return new ExecOperatorEqInt();
                    case DOUBLE:
                        return new ExecOperatorEqDouble();
                    case TEXT:
                        return new ExecOperatorEqText();
                }
            case NEQ:
                switch (type) {
                    case BOOLEAN:
                        return new ExecOperatorNeqBoolean();
                    case INT:
                        return new ExecOperatorNeqInt();
                    case DOUBLE:
                        return new ExecOperatorNeqDouble();
                    case TEXT:
                        return new ExecOperatorNeqText();
                }
            default:
                throw new CompilerException(StringUtils.join("Unsupported binary operator type: ", operator));
        }
    }
}
