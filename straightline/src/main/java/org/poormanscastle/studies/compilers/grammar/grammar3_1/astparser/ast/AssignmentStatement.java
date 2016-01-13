package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

/**
 * represents an assignment statement of the simple straight-line language as defined by grammar 3.1.
 * an assignment statement consists of the id of a variable and an expression which gets evaluated and
 * whose value gets assigned to the given variable.
 * Created by georg on 14.01.16.
 */
public class AssignmentStatement extends AbstractStatement {

    /**
     * name of some variable which shall get assigned the value as retrieved from the expression.
     */
    private String id;

    /**
     * the value retrieved from this expression shall be assigned to the variable as defined by the id.
     */
    private Expression expression;

    public AssignmentStatement(CodePosition codePosition, String id, Expression expression) {
        super(codePosition);
        this.id = id;
        this.expression = expression;
    }

    @Override
    public boolean handleProceedWith(AstItemVisitor visitor) {
        return visitor.proceedWithAssignmentStatement(this);
    }

    @Override
    public void accept(AstItemVisitor visitor) {
        visitor.visitAssignmentStatement(this);
        if(expression.handleProceedWith(visitor)){
            expression.accept(visitor);
        }
        visitor.leaveAssignmentStatement(this);
    }

    public String getId() {
        return id;
    }

    public Expression getExpression() {
        return expression;
    }

}
