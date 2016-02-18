package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * declares the id and the type of a variable. Also optionally accepts an expression to
 * initialize the new variable with its value.
 * <p/>
 * Created by 02eex612 on 17.02.2016.
 */
public final class DeclarationStatement extends AbstractAstItem implements Statement {

    private final Type type;

    private final String id;

    private final Expression expression;

    public DeclarationStatement(CodePosition codePosition, String type, String id, Expression expression) {
        super(codePosition);
        this.type = Type.valueOf(type.toUpperCase());
        this.id = id;
        this.expression = expression;
    }

    public DeclarationStatement(String type, String id, Expression expression) {
        super(expression.getCodePosition());
        this.type = Type.valueOf(type.toUpperCase());
        this.id = id;
        this.expression = expression;
    }

    public DeclarationStatement(CodePosition codePosition, String type, String id) {
        super(codePosition);
        this.type = Type.valueOf(type.toUpperCase());
        this.id = id;
        expression = null;
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
