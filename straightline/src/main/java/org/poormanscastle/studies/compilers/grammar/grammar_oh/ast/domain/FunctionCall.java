package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by 02eex612 on 11.03.2016.
 */
public class FunctionCall extends AbstractExpression<Object> {

    private final String functionId;

    private final ExpressionList argumentList;

    private Type valueType;

    /**
     * the value returned by the respective function after execution
     */
    private Object value;

    public FunctionCall(CodePosition codePosition, String functionId, ExpressionList argumentList) {
        super(codePosition);
        checkArgument(!StringUtils.isBlank(functionId));
        this.functionId = functionId;
        this.argumentList = argumentList;
        // a function call is valid if the given arguments satisfy the function's parameter list.
        // if not, the AST will be invalidated during the argument match. so it should be safe
        // to set the state to valid here, so if a function call is used as sub-expression, the
        // respective super expression can go on being validated.
        setState(ExpressionState.VALID);
    }

    public FunctionCall(String functionId, ExpressionList expressionList) {
        this(expressionList.getCodePosition(), functionId, expressionList);
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithFunctionCall(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitFunctionCall(this);
        if (argumentList != null && argumentList.handleProceedWith(visitor)) {
            argumentList.accept(visitor);
        }
        visitor.leaveFunctionCall(this);
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public Type getValueType() {
        return valueType;
    }

    public void setValueType(Type type) {
        valueType = type;
    }

    public String getFunctionId() {
        return functionId;
    }

    public ExpressionList getArgumentList() {
        return argumentList;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }
}
