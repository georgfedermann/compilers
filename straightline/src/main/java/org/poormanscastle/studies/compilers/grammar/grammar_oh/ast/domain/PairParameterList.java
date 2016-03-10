package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 02eex612 on 09.03.2016.
 */
public final class PairParameterList extends AbstractAstItem implements ParameterList {

    private final Parameter parameter;

    private final ParameterList parameterList;

    public PairParameterList(CodePosition codePosition, Parameter parameter, ParameterList parameterList) {
        super(codePosition);
        checkNotNull(parameter);
        checkNotNull(parameterList);
        this.parameter = parameter;
        this.parameterList = parameterList;
    }

    public PairParameterList(Parameter parameter, ParameterList parameterList) {
        this(parameter.getCodePosition(), parameter, parameterList);
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithPairParameterList(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitPairParameterList(this);
        if (parameter.handleProceedWith(visitor)) {
            parameter.accept(visitor);
        }
        visitor.leavePairParameterList(this);
    }
}
