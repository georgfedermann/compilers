package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * evaluates to a floating point value.
 *
 * Created by 02eex612 on 17.02.2016.
 */
public final class DecimalExpression extends AbstractAstItem implements Expression{

    private final double value;

    public DecimalExpression(CodePosition codePosition, double value) {
        super(codePosition);
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
