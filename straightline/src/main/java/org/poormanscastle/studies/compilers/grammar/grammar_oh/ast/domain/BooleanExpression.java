package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import static com.google.common.base.Preconditions.checkArgument;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public final class BooleanExpression extends AbstractExpression<Boolean> {

    private final Boolean value;

    public BooleanExpression(CodePosition codePosition, Boolean value) {
        super(codePosition);
        checkArgument(value != null);
        this.value = value;
        setState(ExpressionState.VALID);
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(Boolean value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Type getValueType() {
        return Type.BOOLEAN;
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
