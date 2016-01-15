package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

/**
 * Created by georg on 15.01.16.
 */
public class LastExpressionList extends AbstractAstItem implements ExpressionList {

    private final Expression head;

    public LastExpressionList(CodePosition codePosition, Expression head) {
        super(codePosition);
        this.head = head;
    }

    public Expression getHead() {
        return head;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithLastExpressionList(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitLastExpressionList(this);
        if (head.handleProceedWith(visitor)) {
            head.accept(visitor);
        }
        visitor.leaveLastExpressionList(this);
    }
}
