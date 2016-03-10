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

    private final String id;

    private final Type type;

    public Parameter(CodePosition codePosition, String type, String id) {
        super(codePosition);
        checkArgument(!StringUtils.isBlank(id));
        checkNotNull(!StringUtils.isBlank(type));
        this.id = id;
        this.type = Type.valueOf(type.toUpperCase());
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
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
