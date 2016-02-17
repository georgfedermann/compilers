package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * A NUM factor holds some numeric value as defined by grammar 3.1 (where numeric values are
 * integers).
 * Created by georg on 14.01.16.
 */
public class NumExpression extends AbstractAstItem implements Expression {

    private final Integer num;

    public NumExpression(CodePosition codePosition, Integer num) {
        super(codePosition);
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithNumExpression(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitNumExpression(this);
        visitor.leaveNumExpression(this);
    }
}
