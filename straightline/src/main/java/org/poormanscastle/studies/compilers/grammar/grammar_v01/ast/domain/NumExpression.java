package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * evaluates to an integer expression.
 * <p/>
 * Created by 02eex612 on 17.02.2016.
 */
public final class NumExpression extends AbstractAstItem implements Expression {

    private final Integer value;

    public NumExpression(CodePosition codePosition, Integer value) {
        super(codePosition);
        checkArgument(value != null);
        this.value = value;
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
