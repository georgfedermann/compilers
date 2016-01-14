package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

/**
 * An ID factor holds the name of some variable to whose value it evaluates.
 * Created by georg on 14.01.16.
 */
public class IdFactor extends AbstractAstItem implements Factor {

    /**
     * the id of some variable to whose value this factor shall be evaluated.
     */
    private String id;

    public IdFactor(CodePosition codePosition, String id) {
        super(codePosition);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithIdFactor(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitIdFactor(this);
        visitor.leaveIdFactor(this);
    }
    
}
