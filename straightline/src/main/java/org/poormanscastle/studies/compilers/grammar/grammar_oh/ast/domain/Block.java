package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 02eex612 on 26.02.2016.
 */
public class Block extends AbstractAstItem implements Statement {

    /**
     * can remain null if the Block is empty.
     */
    private Statement statement;

    public Block(CodePosition codePosition, Statement statement) {
        super(codePosition);
        checkNotNull(statement);
        this.statement = statement;
    }

    public Block(Statement statement) {
        this(statement.getCodePosition(), statement);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitBlock(this);
        if (statement.handleProceedWith(visitor)) {
            statement.accept(visitor);
        }
        visitor.leaveBlock(this);
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithBlock(this);
    }
}
