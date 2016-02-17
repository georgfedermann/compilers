package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * resolves to the value of a variable identified by an id.
 * <p/>
 * Created by 02eex612 on 17.02.2016.
 */
public final class IdExpression extends AbstractAstItem implements Expression {

    private final String id;

    public IdExpression(CodePosition codePosition, String id) {
        super(codePosition);
        this.id = id;
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
