package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * Created by 02eex612 on 22.02.2016.
 */
public abstract class AbstractExpression extends AbstractAstItem implements Expression {

    private ExpressionState state = ExpressionState.NOT_DETERMINED_YET;

    public AbstractExpression(CodePosition codePosition) {
        super(codePosition);
    }

    @Override
    public ExpressionState getState() {
        return state;
    }

    public void setState(ExpressionState state) {
        this.state = state;
    }

}
