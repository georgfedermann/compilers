package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * evaluates to an integer expression.
 * <p/>
 * Created by 02eex612 on 17.02.2016.
 */
public final class NumExpression extends AbstractExpression implements Expression {

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

    public Integer getValue() {
        return value;
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