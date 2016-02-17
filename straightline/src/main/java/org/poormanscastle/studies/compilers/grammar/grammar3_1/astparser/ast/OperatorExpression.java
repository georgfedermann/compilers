package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * Created by georg on 15.01.16.
 */
public class OperatorExpression extends AbstractAstItem implements Expression {

    private final Expression leftOperand, rightOperand;
    private final Operator operator;

    public OperatorExpression(CodePosition codePosition, Expression leftOperand, Operator operator, Expression rightOperand) {
        super(codePosition);
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.operator = operator;
    }

    public Expression getLeftOperand() {
        return leftOperand;
    }

    public Expression getRightOperand() {
        return rightOperand;
    }

    public Operator getOperator() {
        return operator;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithOperatorExpression(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitOperatorExpression(this);
        if (leftOperand.handleProceedWith(visitor)) {
            leftOperand.accept(visitor);
        }
        if (rightOperand.handleProceedWith(visitor)) {
            rightOperand.accept(visitor);
        }
        visitor.leaveOperatorExpression(this);
    }

}
