package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public final class BooleanExpression extends AbstractAstItem implements Expression {

    private final Boolean value;

    public BooleanExpression(CodePosition codePosition, Boolean value) {
        super(codePosition);
        checkArgument(value != null);
        this.value = value;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithBooleanExpression(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitBooleanExpression(this);
        visitor.leaveBooleanExpression(this);
    }
}
