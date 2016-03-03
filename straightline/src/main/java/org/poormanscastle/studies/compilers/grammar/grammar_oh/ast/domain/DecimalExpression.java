package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * evaluates to a floating point value.
 * <p/>
 * Created by 02eex612 on 17.02.2016.
 */
public final class DecimalExpression extends AbstractExpression implements Expression {

    private final Double value;

    public DecimalExpression(CodePosition codePosition, Double value) {
        super(codePosition);
        checkArgument(value != null);
        this.value = value;
        setState(ExpressionState.VALID);
    }

    @Override
    public Type getValueType() {
        return Type.DOUBLE;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithDecimalExpression(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitDecimalExpression(this);
        visitor.leaveDecimalExpression(this);
    }
}