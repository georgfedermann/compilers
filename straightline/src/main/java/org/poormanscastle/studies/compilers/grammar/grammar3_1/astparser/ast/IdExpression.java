package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

/**
 * An ID expression holds the name used as an identifier.
 * <p>
 * Created by georg on 14.01.16.
 */
public class IdExpression extends AbstractAstItem implements Expression {

    /**
     * the id of some variable to whose value this factor shall be evaluated.
     */
    private final String id;

    public IdExpression(CodePosition codePosition, String id) {
        super(codePosition);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithIdExpression(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitIdExpression(this);
        visitor.leaveIdExpression(this);
    }

}
