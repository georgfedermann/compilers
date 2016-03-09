package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * formal parameter for function calls.
 * <p/>
 * Created by 02eex612 on 09.03.2016.
 */
public final class Parameter extends AbstractAstItem {

    private final String name;

    private final Type type;

    private final Object value;

    public Parameter(CodePosition codePosition, String name, Type type, Object value) {
        super(codePosition);
        checkArgument(!StringUtils.isBlank(name));
        checkNotNull(type);
        checkNotNull(value);
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithParameter(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitParameter(this);
        visitor.leaveParameter(this);
    }
}
