package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public final class ProgramImpl extends AbstractAstItem implements Program {

    private final Statement statement;

    public ProgramImpl(CodePosition codePosition, Statement statement) {
        super(codePosition);
        this.statement = statement;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithProgramImpl(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitProgramImpl(this);
        if (statement.handleProceedWith(visitor)) {
            statement.accept(visitor);
        }
        visitor.leaveProgramImpl(this);
    }
}
