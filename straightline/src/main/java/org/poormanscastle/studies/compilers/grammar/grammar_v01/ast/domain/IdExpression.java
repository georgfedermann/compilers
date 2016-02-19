package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * resolves to the value of a variable identified by an id.
 * <p/>
 * Created by 02eex612 on 17.02.2016.
 */
public final class IdExpression extends AbstractAstItem implements Expression {

    private final String id;

    public IdExpression(CodePosition codePosition, String id) {
        super(codePosition);
        checkArgument(!StringUtils.isBlank(id));
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithIdExpression(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitIdExpression(this);
        visitor.leaveIdExpression(this);
    }
}
