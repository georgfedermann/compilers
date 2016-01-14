package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

/**
 * A NUM factor holds some numeric value as defined by grammar 3.1 (where numeric values are
 * integers).
 * Created by georg on 14.01.16.
 */
public class NumFactor extends AbstractAstItem implements Factor {

    private Integer num;

    public NumFactor(CodePosition codePosition, Integer num) {
        super(codePosition);
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithNumFactor(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitNumFactor(this);
        visitor.leaveNumFactor(this);
    }
}
