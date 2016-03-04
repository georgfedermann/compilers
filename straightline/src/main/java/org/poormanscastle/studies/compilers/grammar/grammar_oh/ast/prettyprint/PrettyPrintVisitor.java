package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.prettyprint;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.AssignmentStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.AstItemVisitorAdapter;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.BinaryOperatorExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Block;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.BooleanExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ConditionalStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.DecimalExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.DeclarationStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ElseStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.IdExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.LastExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.LastStatementList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.NumExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.PairExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.PairStatementList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.PrintStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ProgramImpl;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.TextExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ThenStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.UnaryOperatorExpression;

import static com.google.common.base.Preconditions.checkState;

/**
 * creates a graphical view of the AST of an v0.1 grammar program using DOT syntax.
 * <p/>
 * Created by 02eex612 on 18.02.2016.
 */
public class PrettyPrintVisitor extends AstItemVisitorAdapter {

    private static long sequence = 0;

    /**
     * using this stack the pretty printer keeps track of the current parent elements.
     */
    private Stack<StackItem> itemStack = new Stack<>();
    private List<StackItem> stackItems = new LinkedList<>();

    private StringBuffer buffer = new StringBuffer();

    public PrettyPrintVisitor() {
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

    public void addItem(String name, String value) {
        StackItem stackItem = new StackItem(name, value);
        itemStack.push(stackItem);
        stackItems.add(stackItem);
    }

    @Override
    public boolean proceedWithPrintStatement(PrintStatement printStatement) {
        return true;
    }

    @Override
    public void visitPrintStatement(PrintStatement printStatement) {
        addItem("PrntStm", "");
        addBufferLine();
    }

    @Override
    public void leavePrintStatement(PrintStatement printStatement) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithConditionalStatement(ConditionalStatement conditionalStatement) {
        return true;
    }

    @Override
    public void visitConditionalStatement(ConditionalStatement conditionalStatement) {
        addItem("If", "");
        addBufferLine();
    }

    @Override
    public void leaveConditionalStatement(ConditionalStatement conditionalStatement) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithThenStatement(ThenStatement thenStatement) {
        return true;
    }

    @Override
    public void visitThenStatement(ThenStatement thenStatement) {
        addItem("Then", "");
        addBufferLine();
    }

    @Override
    public void leaveThenStatement(ThenStatement thenStatement) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithElseStatement(ElseStatement elseStatement) {
        return true;
    }

    @Override
    public void visitElseStatement(ElseStatement elseStatement) {
        addItem("Else", "");
        addBufferLine();
    }

    @Override
    public void leaveElseStatement(ElseStatement elseStatement) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithProgramImpl(ProgramImpl program) {
        return true;
    }

    @Override
    public void visitProgramImpl(ProgramImpl program) {
        addItem("Program", "[Stm]");
        addBufferLine();
    }

    @Override
    public void leaveProgramImpl(ProgramImpl program) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithPairStatementList(PairStatementList pairStatementList) {
        return true;
    }

    @Override
    public void visitPairStatementList(PairStatementList pairStatementList) {
        addItem("PairStmList", "[Stm]");
        addBufferLine();
    }

    @Override
    public void leavePairStatementList(PairStatementList pairStatementList) {
        itemStack.pop();
    }

    @Override
    public void leaveBlock(Block block) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithBlock(Block block) {
        return true;
    }

    @Override
    public void visitBlock(Block block) {
        addItem("Block", "");
        addBufferLine();
    }

    @Override
    public boolean proceedWithLastStatementList(LastStatementList lastStatementList) {
        return true;
    }

    @Override
    public void visitLastStatementList(LastStatementList lastStatementList) {
        addItem("LastStmList", "[Stm]");
        addBufferLine();
    }

    @Override
    public void leaveLastStatementList(LastStatementList lastStatementList) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithPairExpressionList(PairExpressionList pairExpressionList) {
        return true;
    }

    @Override
    public void visitPairExpressionList(PairExpressionList pairExpressionList) {
        addItem("PairExpList", "[Exp]");
        addBufferLine();
    }

    @Override
    public void leavePairExpressionList(PairExpressionList pairExpressionList) {
        itemStack.pop();
    }

    @Override
    public void leaveLastExpressionList(LastExpressionList lastExpressionList) {
        itemStack.pop();
    }

    @Override
    public void visitLastExpressionList(LastExpressionList lastExpressionList) {
        addItem("LastExpList", "[Exp]");
        addBufferLine();
    }

    @Override
    public boolean proceedWithLastExpressionList(LastExpressionList lastExpressionList) {
        return true;
    }

    @Override
    public boolean proceedWithAssignmentStatement(AssignmentStatement assignmentStatement) {
        return true;
    }

    @Override
    public void visitAssignmentStatement(AssignmentStatement assignmentStatement) {
        addItem("AssignStm", assignmentStatement.getId());
        addBufferLine();
    }

    @Override
    public void leaveAssignmentStatement(AssignmentStatement assignmentStatement) {
        itemStack.pop();
    }

    @Override
    public void leaveDeclarationStatement(DeclarationStatement declarationStatement) {
        itemStack.pop();
    }

    @Override
    public void visitDeclarationStatement(DeclarationStatement declarationStatement) {
        addItem("DclStm", StringUtils.join(declarationStatement.getType(), " ", declarationStatement.getId()));
        addBufferLine();
    }

    @Override
    public boolean proceedWithDeclarationStatement(DeclarationStatement declarationStatement) {
        return true;
    }

    @Override
    public void leaveIdExpression(IdExpression idExpression) {
        itemStack.pop();
    }

    @Override
    public void visitIdExpression(IdExpression idExpression) {
        addItem("IdExpr", idExpression.getId());
        addBufferLine();
    }

    @Override
    public boolean proceedWithIdExpression(IdExpression idExpression) {
        return true;
    }

    @Override
    public void leaveNumExpression(NumExpression numExpression) {
        itemStack.pop();
    }

    @Override
    public void visitNumExpression(NumExpression numExpression) {
        addItem("NumExpr", String.valueOf(numExpression.getValue()));
        addBufferLine();
    }

    @Override
    public boolean proceedWithNumExpression(NumExpression numExpression) {
        return true;
    }

    @Override
    public void leaveDecimalExpression(DecimalExpression decimalExpression) {
        itemStack.pop();
    }

    @Override
    public void visitDecimalExpression(DecimalExpression decimalExpression) {
        addItem("DecExp", String.valueOf(decimalExpression.getValue()));
        addBufferLine();
    }

    @Override
    public boolean proceedWithDecimalExpression(DecimalExpression decimalExpression) {
        return true;
    }

    @Override
    public void leaveBooleanExpression(BooleanExpression booleanExpression) {
        itemStack.pop();
    }

    @Override
    public void visitBooleanExpression(BooleanExpression booleanExpression) {
        addItem("BoolExpr", String.valueOf(booleanExpression.getValue()));
        addBufferLine();
    }

    @Override
    public boolean proceedWithBooleanExpression(BooleanExpression booleanExpression) {
        return true;
    }

    @Override
    public void leaveTextExpression(TextExpression textExpression) {
        itemStack.pop();
    }

    @Override
    public void visitTextExpression(TextExpression textExpression) {
        addItem("TxtExpr", textExpression.getValue());
        addBufferLine();
    }

    @Override
    public boolean proceedWithTextExpression(TextExpression textExpression) {
        return true;
    }

    @Override
    public void leaveBinaryOperatorExpression(BinaryOperatorExpression binaryOperatorExpression) {
        itemStack.pop();
    }

    @Override
    public void visitBinaryOperatorExpression(BinaryOperatorExpression binaryOperatorExpression) {
        addItem("BinOpExpr", binaryOperatorExpression.getOperator().getLabel());
        addBufferLine();
    }

    @Override
    public boolean proceedWithBinaryOperatorExpression(BinaryOperatorExpression binaryOperatorExpression) {
        return true;
    }

    @Override
    public void leaveUnaryOperatorExpression(UnaryOperatorExpression unaryOperatorExpression) {
        itemStack.pop();
    }

    @Override
    public void visitUnaryOperatxorExpression(UnaryOperatorExpression unaryOperatorExpression) {
        addItem("UnOpExpr", unaryOperatorExpression.getOperator().getLabel());
        addBufferLine();
    }

    @Override
    public boolean proceedWithUnaryOperatorExpression(UnaryOperatorExpression unaryOperatorExpression) {
        return true;
    }

    /**
     * the pretty printer uses a stack to keep track of the current nodes.
     */
    private class StackItem {
        private String id = StringUtils.join("i", PrettyPrintVisitor.sequence++);

        /**
         * name will be used as label for the DOT diagram.
         */
        private final String name;

        /**
         * if an item carries a semantic value (like the id of an idToken, etc.) the value
         * will be stored here.
         */
        private final String value;

        public StackItem(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getLabel() {
            return StringUtils.isBlank(value) ? name : StringUtils.join(name, ":", value);
        }

        public String getId() {
            return id;
        }
    }

}
