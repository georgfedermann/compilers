package org.poormanscastle.studies.compilers.grammar.grammar3_1.visitors;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.AssignmentStatement;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.AstItemVisitor;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.EseqExpression;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.IdExpression;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.LastExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.NumExpression;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.OperatorExpression;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.PairExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.PrintStatement;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.StatementList;

import static com.google.common.base.Preconditions.checkState;

/**
 * Creates a graphical view of the AST using DOT syntax.
 * <p/>
 * Created by georg on 15.01.16.
 */
public class AstPrettyPrintVisitor implements AstItemVisitor {

    private static long sequence = 0;

    /**
     * these items are put onto the stack and used to generate output.
     */
    private class StackItem {

        /**
         * some id to uniquely identify the item, so to refer to it in the graph definition.
         */
        private String id = StringUtils.join("i", ++AstPrettyPrintVisitor.sequence);
        /**
         * a name of the item, probably the simple class name of the AST item's type.
         */
        private String name;
        /**
         * for items which carry a value it will be used for the label
         */
        private String value;

        public StackItem(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getLabel() {
            return StringUtils.join(name, ":", value);
        }

        public String getId() {
            return id;
        }
    }

    /**
     * using this stack the visitor keeps track of the current parent element.
     */
    private Stack<StackItem> itemStack = new Stack<>();
    private List<StackItem> stackItems = new LinkedList<>();

    /**
     * output is buffered in this string buffer.
     */
    private StringBuffer buffer = new StringBuffer();

    public AstPrettyPrintVisitor() {
        initialize();
    }

    public void initialize() {
        itemStack = new Stack<>();
        buffer = new StringBuffer("digraph ast {\n%labels%\nnode [shape = circle];\n");
    }

    public void addBufferLine() {
        checkState(itemStack.size() > 0);
        if (itemStack.size() > 1) {
            StackItem child = itemStack.pop();
            StackItem parent = itemStack.pop();
            buffer.append(StringUtils.join(parent.getId(), " -> ", child.getId(), ";\n"));
            itemStack.push(parent);
            itemStack.push(child);
        }
    }

    public String serialize() {
        buffer.append("}");
        String result = buffer.toString();
        StringBuffer labelDefinitions = new StringBuffer();
        for (StackItem stackItem : stackItems) {
            labelDefinitions.append(StringUtils.join(stackItem.getId(), " [label=\"", stackItem.getLabel(), "\"];\n"));
        }
        result = result.replace("%labels%", labelDefinitions.toString());
        return result;
    }

    @Override
    public boolean proceedWithAssignmentStatement(AssignmentStatement assignmentStatement) {
        return true;
    }

    public void addItem(String name, String value) {
        StackItem stackItem = new StackItem(name, value);
        itemStack.push(stackItem);
        stackItems.add(stackItem);
    }

    @Override
    public void visitAssignmentStatement(AssignmentStatement assignmentStatement) {
        addItem("AssStm", assignmentStatement.getId());
        addBufferLine();
    }

    @Override
    public void leaveAssignmentStatement(AssignmentStatement assignmentStatement) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithIdExpression(IdExpression idExpression) {
        return true;
    }

    @Override
    public void visitIdExpression(IdExpression idExpression) {
        addItem("IdExp", idExpression.getId());
        addBufferLine();
    }

    @Override
    public void leaveIdExpression(IdExpression idExpression) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithNumExpression(NumExpression numExpression) {
        return true;
    }

    @Override
    public void visitNumExpression(NumExpression numExpression) {
        addItem("NumExp", String.valueOf(numExpression.getNum()));
        addBufferLine();
    }

    @Override
    public void leaveNumExpression(NumExpression numExpression) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithEseqExpression(EseqExpression eseqExpression) {
        return true;
    }

    @Override
    public void visitEseqExpression(EseqExpression eseqExpression) {
        addItem("EseqExp", "[]");
        addBufferLine();
    }

    @Override
    public void leaveEseqExpression(EseqExpression eseqExpression) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithPrintStatement(PrintStatement printStatement) {
        return true;
    }

    @Override
    public void visitPrintStatement(PrintStatement printStatement) {
        addItem("PrtStm", "[]");
        addBufferLine();
    }

    @Override
    public void leavePrintStatement(PrintStatement printStatement) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithPairExpressionList(PairExpressionList pairExpressionList) {
        return true;
    }

    @Override
    public void visitPairExpressionList(PairExpressionList pairExpressionList) {
        addItem("PairExpList", "[]");
        addBufferLine();
    }

    @Override
    public void leavePairExpressionList(PairExpressionList pairExpressionList) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithLastExpressionList(LastExpressionList lastExpressionList) {
        return true;
    }

    @Override
    public void visitLastExpressionList(LastExpressionList lastExpressionList) {
        addItem("LastExpList", "[]");
        addBufferLine();
    }

    @Override
    public void leaveLastExpressionList(LastExpressionList lastExpressionList) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithOperatorExpression(OperatorExpression operatorExpression) {
        return true;
    }

    @Override
    public void visitOperatorExpression(OperatorExpression operatorExpression) {
        addItem("OpExp", operatorExpression.getOperator().getLabel());
        addBufferLine();
    }

    @Override
    public void leaveOperatorExpression(OperatorExpression operatorExpression) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithStatementList(StatementList statementList) {
        return true;
    }

    @Override
    public void visitStatementList(StatementList statementList) {
        addItem("StmList", "[]");
        addBufferLine();
    }

    @Override
    public void leaveStatementList(StatementList statementList) {
        itemStack.pop();
    }

}
