package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * functions are like statements that only get executed on function calls.
 * <p/>
 * Created by 02eex612 on 09.03.2016.
 */
public final class Function extends AbstractAstItem implements Statement {

    private final ParameterList parameterList;

    private final Statement functionBody;

    private final String id;

    private final Type type;

    public Function(CodePosition codePosition, String type, String id, Statement functionBody, ParameterList parameterList) {
        super(codePosition);
        checkNotNull(functionBody);
        checkNotNull(parameterList);
        checkArgument(!StringUtils.isBlank(type));
        checkArgument(!StringUtils.isBlank(id));
        this.type = Type.valueOf(type.toUpperCase());
        this.id = id;
        this.functionBody = functionBody;
        this.parameterList = parameterList;
    }

    public Function(String type, String id, Statement functionBody, ParameterList parameterList) {
        this(functionBody.getCodePosition(), type, id, functionBody, parameterList);
    }

    public ParameterList getParameterList() {
        return parameterList;
    }

    public Statement getFunctionBody() {
        return functionBody;
    }

    public String getId() {
        return id;
    }

    public Type getValueType() {
        return type;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithFunction(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitFunction(this);
        if (parameterList.handleProceedWith(visitor)) {
            parameterList.accept(visitor);
        }
        if (functionBody.handleProceedWith(visitor)) {
            functionBody.accept(visitor);
        }
        visitor.leaveFunction(this);
    }
}
