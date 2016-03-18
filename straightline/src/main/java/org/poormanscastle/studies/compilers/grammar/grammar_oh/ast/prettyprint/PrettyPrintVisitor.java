package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.prettyprint;

import static com.google.common.base.Preconditions.checkState;

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
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ForStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Function;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.FunctionCall;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.IdExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.LastExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.LastParameterList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.LastStatementList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.NumExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.PairExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.PairParameterList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.PairStatementList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Parameter;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.PrintStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ProgramImpl;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ReturnStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.TextExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.UnaryOperatorExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.WhileBody;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.WhileStatement;

/**
 * creates a graphical view of the AST of an v0.1 grammar program using DOT syntax.
 * <p>
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
        StringBuilder labelDefinitions = new StringBuilder();
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
    public boolean proceedWithWhileStatement(WhileStatement whileStatement) {
        return true;
    }

    @Override
    public void visitWhileStatement(WhileStatement whileStatement) {
        addItem("While", "");
        addBufferLine();
    }

    @Override
    public void leaveWhileStatement(WhileStatement whileStatement) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithWhileBody(WhileBody whileBody) {
        return true;
    }

    @Override
    public void visitWhileBody(WhileBody whileBody) {
        addItem("WhileBody", "");
        addBufferLine();
    }

    @Override
    public void leaveWhileBody(WhileBody whileBody) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithForStatement(ForStatement forStatement) {
        return true;
    }

    @Override
    public void visitForStatement(ForStatement forStatement) {
        addItem("For", "");
        addBufferLine();
    }

    @Override
    public void leaveForStatement(ForStatement forStatement) {
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
    public boolean proceedWithUnaryOperatorExpression(UnaryOperatorExpression unaryOperatorExpression) {
        return true;
    }

    @Override
    public void visitUnaryOperatxorExpression(UnaryOperatorExpression unaryOperatorExpression) {
        addItem("UnOpExpr", unaryOperatorExpression.getOperator().getLabel());
        addBufferLine();
    }

    @Override
    public void leaveUnaryOperatorExpression(UnaryOperatorExpression unaryOperatorExpression) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithFunction(Function function) {
        return true;
    }

    @Override
    public void visitFunction(Function function) {
        addItem("Func", StringUtils.join(function.getId(), "()"));
        addBufferLine();
    }

    @Override
    public void leaveFunction(Function function) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithFunctionCall(FunctionCall functionCall) {
        return true;
    }

    @Override
    public void visitFunctionCall(FunctionCall functionCall) {
        addItem("CALL", StringUtils.join(functionCall.getFunctionId(), "()"));
        addBufferLine();
    }

    @Override
    public void leaveFunctionCall(FunctionCall functionCall) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithReturnStatement(ReturnStatement returnStatement) {
        return true;
    }

    @Override
    public void visitReturnStatement(ReturnStatement returnStatement) {
        addItem("RETURN", "");
        addBufferLine();
    }

    @Override
    public void leaveReturnStatement(ReturnStatement returnStatement) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithParameter(Parameter parameter) {
        return true;
    }

    @Override
    public void visitParameter(Parameter parameter) {
        addItem("Param", StringUtils.join(parameter.getType(), " ", parameter.getId()));
        addBufferLine();
    }

    @Override
    public void leaveParameter(Parameter parameter) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithPairParameterList(PairParameterList pairParameterList) {
        return true;
    }

    @Override
    public void visitPairParameterList(PairParameterList pairParameterList) {
        addItem("PairParamList", "");
        addBufferLine();
    }

    @Override
    public void leavePairParameterList(PairParameterList pairParameterList) {
        itemStack.pop();
    }

    @Override
    public boolean proceedWithLastParameterList(LastParameterList lastParameterList) {
        return true;
    }

    @Override
    public void visitLastParameterList(LastParameterList lastParameterList) {
        addItem("LastParamList", "");
        addBufferLine();
    }

    @Override
    public void leaveLastParameterList(LastParameterList lastParameterList) {
        itemStack.pop();
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
