package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import org.apache.commons.lang3.StringUtils;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * declares the id and the type of a variable. Also optionally accepts an expression to
 * initialize the new variable with its value.
 * <p/>
 * Created by 02eex612 on 17.02.2016.
 */
public final class DeclarationStatement extends AbstractAstItem implements Statement {

    private final Type type;

    private final String id;

    /**
     * Expression may remain {@code null} if the declaration statement has no implicit initialization.
     */
    private final Expression expression;

    public DeclarationStatement(CodePosition codePosition, String type, String id, Expression expression) {
        super(codePosition);
        checkArgument(!StringUtils.isBlank(type));
        checkArgument(!StringUtils.isBlank(id));
        this.type = Type.valueOf(type.toUpperCase());
        this.id = id;
        this.expression = expression;
    }

    public DeclarationStatement(String type, String id, Expression expression) {
        this(expression.getCodePosition(), type, id, expression);
    }

    public DeclarationStatement(CodePosition codePosition, String type, String id) {
        this(codePosition, type, id, null);
        checkArgument(!StringUtils.isBlank(type));
        checkArgument(!StringUtils.isBlank(id));
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithDeclarationStatement(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitDeclarationStatement(this);
        if (expression != null && expression.handleProceedWith(visitor)) {
            expression.accept(visitor);
        }
        visitor.leaveDeclarationStatement(this);
    }
}
