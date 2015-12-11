package org.poormanscastle.studies.compilers.grammar;

/**
 * the operator expression is kind of a compound expression consisting of two expressions which
 * will be combined by the given binary operator.
 * <p>
 * Created by georg on 02.12.15.
 */
public class OpExp extends AbstractExp {
    private AbstractExp left, right;
    private Operator operator;
    public final static int PLUS = 1, MINUS = 3, TIMES = 3, DIV = 4;

    public OpExp(AbstractExp left, Operator operator, AbstractExp right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public void accept(GrammarItemVisitor visitor) {
        visitor.visitOpExp(this);
        if (left.handleProceedWith(visitor)) {
            left.accept(visitor);
        }
        if (right.handleProceedWith(visitor)) {
            right.accept(visitor);
        }
        visitor.leaveOpExp(this);
    }

    @Override
    public boolean handleProceedWith(GrammarItemVisitor visitor) {
        return visitor.proceedWithOpExp(this);
    }

    @Override
    public ValueAndTable evaluate(Table table) {
        ValueAndTable vatLeft = left.evaluate(table);
        ValueAndTable vatRight = right.evaluate(vatLeft.getTable());
        return new ValueAndTable(operator.operate(vatLeft.getValue(), vatRight.getValue()), vatRight.getTable());
    }
}
