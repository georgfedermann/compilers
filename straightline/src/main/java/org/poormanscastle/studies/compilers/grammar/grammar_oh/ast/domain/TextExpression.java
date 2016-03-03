package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * evaluates to a string value.
 * <p/>
 * Created by 02eex612 on 17.02.2016.
 */
public final class TextExpression extends AbstractExpression implements Expression {

    private final String value;

    public TextExpression(CodePosition codePosition, String value) {
        super(codePosition);
        checkArgument(!StringUtils.isBlank(value));
        // string literals are delimted with quotes. JavaCC hands the quotes through. I remove all quotes here.
        // TODO maybe keep escaped quotes
        this.value = value.replaceAll("\"", "");
        setState(ExpressionState.VALID);
    }

    public String getValue() {
        return value;
    }

    @Override
    public Type getValueType() {
        return Type.TEXT;
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
