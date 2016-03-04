package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import static com.google.common.base.Preconditions.checkArgument;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * evaluates to an integer expression.
 * <p>
 * Created by 02eex612 on 17.02.2016.
 */
public final class NumExpression extends AbstractExpression<Integer> {

    private final Integer value;

    public NumExpression(CodePosition codePosition, Integer value) {
        super(codePosition);
        checkArgument(value != null);
        this.value = value;
        setState(ExpressionState.VALID);
    }

    @Override
    public Type getValueType() {
        return Type.INT;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Integer value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithNumExpression(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitNumExpression(this);
        visitor.leaveNumExpression(this);
    }
}
