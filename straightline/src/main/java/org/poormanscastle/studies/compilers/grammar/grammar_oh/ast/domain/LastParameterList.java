package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 02eex612 on 09.03.2016.
 */
public final class LastParameterList extends AbstractAstItem implements ParameterList {

    private final Parameter parameter;

    public LastParameterList(CodePosition codePosition, Parameter parameter) {
        super(codePosition);
        checkNotNull(parameter);
        this.parameter = parameter;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithLastParameterList(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitLastParameterList(this);
        if(parameter.handleProceedWith(visitor)){
            parameter.accept(visitor);
        }
        visitor.leaveLastParameterList(this);
    }
}
