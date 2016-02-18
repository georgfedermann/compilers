package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * evaluates to a string value.
 * <p/>
 * Created by 02eex612 on 17.02.2016.
 */
public final class TextExpression extends AbstractAstItem implements Expression {

    private final String value;

    public TextExpression(CodePosition codePosition, String value) {
        super(codePosition);
        this.value = value;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithTextExpression(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitTextExpression(this);
        visitor.leaveTextExpression(this);
    }
}
