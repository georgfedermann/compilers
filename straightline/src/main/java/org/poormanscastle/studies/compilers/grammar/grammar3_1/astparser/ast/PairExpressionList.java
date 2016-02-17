package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * implementation of expression list as suggested by Andrew Appel in his book Modern Compiler Implementation.
 * Created by georg on 15.01.16.
 */
public class PairExpressionList extends AbstractAstItem implements ExpressionList {

    private final Expression head;

    private final ExpressionList tail;

    public PairExpressionList(CodePosition codePosition, Expression head, ExpressionList tail) {
        super(codePosition);
        this.head = head;
        this.tail = tail;
    }

    public Expression getHead() {
        return head;
    }

    public ExpressionList getTail() {
        return tail;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithPairExpressionList(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitPairExpressionList(this);
        if (head.handleProceedWith(visitor)) {
            head.accept(visitor);
        }
        if (tail.handleProceedWith(visitor)) {
            tail.accept(visitor);
        }
        visitor.leavePairExpressionList(this);
    }
}
