package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public final class PairStatementList extends AbstractAstItem implements StatementList {

    private final Statement head;

    private final StatementList tail;

    public PairStatementList(CodePosition codePosition, Statement head, StatementList tail) {
        super(codePosition);
        this.head = head;
        this.tail = tail;
    }

    public PairStatementList(Statement head, StatementList tail) {
        super(head.getCodePosition());
        this.head = head;
        this.tail = tail;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithPairStatementList(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitPairStatementList(this);
        if (head.handleProceedWith(visitor)) {
            head.accept(visitor);
        }
        if (tail.handleProceedWith(visitor)) {
            tail.accept(visitor);
        }
        visitor.leavePairStatementList(this);
    }
}
