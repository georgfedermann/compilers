package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * evaluates to a floating point value.
 * <p/>
 * Created by 02eex612 on 17.02.2016.
 */
public final class DecimalExpression extends AbstractAstItem implements Expression {

    private final Double value;

    public DecimalExpression(CodePosition codePosition, Double value) {
        super(codePosition);
        checkArgument(value != null);
        this.value = value;
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
