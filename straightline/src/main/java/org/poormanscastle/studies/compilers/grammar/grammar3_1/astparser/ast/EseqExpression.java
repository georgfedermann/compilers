package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

/**
 * EseqExpr is kind of a compound expression where a statement is evaluated for side effects,
 * then expression is evaluated for a result, as defined by grammar 3.1.
 * <p>
 * Created by georg on 15.01.16.
 */
public class EseqExpression extends AbstractAstItem implements Expression {

    private final Statement statement;

    private final Expression expression;

    public EseqExpression(CodePosition codePosition, Statement statement, Expression expression) {
        super(codePosition);
        this.statement = statement;
        this.expression = expression;
    }

    public Statement getStatement() {
        return statement;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithEseqExpression(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitEseqExpression(this);
        if (statement.handleProceedWith(visitor)) {
            statement.accept(visitor);
        }
        if (expression.handleProceedWith(visitor)) {
            expression.accept(visitor);
        }
        visitor.leaveEseqExpression(this);
    }
}
