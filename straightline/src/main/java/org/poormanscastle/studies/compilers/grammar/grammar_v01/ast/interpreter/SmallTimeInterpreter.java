package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.interpreter;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.AssignmentStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.AstItemVisitorAdapter;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.BinaryOperatorExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.Block;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.BooleanExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.ConditionalStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.DecimalExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.DeclarationStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.IdExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.LastExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.LastStatementList;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.NumExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.PairExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.PairStatementList;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.PrintStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.ProgramImpl;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.TextExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.UnaryOperatorExpression;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Symbol;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable.SymbolTable;

import com.google.common.collect.Lists;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * SmallTimeInterpreter sits directly on the semantic analysis phase of the compiler and uses the symboltable's
 * environments to manage its variables and scopes.
 * <p/>
 * Created by 02eex612 on 02.03.2016.
 */
public final class SmallTimeInterpreter extends AstItemVisitorAdapter {

    private final Stack<Object> operandStack;

    /**
     * this data structure keeps tracks of expressions within expressionLists.
     * Since expressionLists are only allowed within PrintStatements and PrintStatements cannot be nested,
     * it's sufficient to hold one single List<Expression> which can be reset or initialized anew within
     * visitPrintStatement() and can be consumed within leavePrintStatement().
     */
    private final List<Object> expressionList;

    /**
     * The SmallTimeInterpreter mimicks the behavior of the compiler's semantic phase's SymbolTableCreator to keep
     * track of identifiers, their types, scopes and values.
     */
    private final SymbolTable symbolTable;

    public SmallTimeInterpreter() {
        symbolTable = new SymbolTable();
        expressionList = new LinkedList<>();
        operandStack = new Stack<>();
    }

    @Override
    public boolean proceedWithPrintStatement(PrintStatement printStatement) {
        return true;
    }

    @Override
    public void visitPrintStatement(PrintStatement printStatement) {
        expressionList.clear();
    }

    @Override
    public void leavePrintStatement(PrintStatement printStatement) {
        for (Object expression : Lists.reverse(expressionList)) {
            System.out.print(expression);
        }
    }

    @Override
    public boolean proceedWithPairExpressionList(PairExpressionList pairExpressionList) {
        return true;
    }

    @Override
    public void leavePairExpressionList(PairExpressionList pairExpressionList) {
        expressionList.add(operandStack.pop());
    }

    @Override
    public boolean proceedWithLastExpressionList(LastExpressionList lastExpressionList) {
        return true;
    }

    @Override
    public void leaveLastExpressionList(LastExpressionList lastExpressionList) {
        expressionList.add(operandStack.pop());
    }

    @Override
    public boolean proceedWithDeclarationStatement(DeclarationStatement declarationStatement) {
        return true;
    }

    @Override
    public void leaveDeclarationStatement(DeclarationStatement declarationStatement) {
        if (declarationStatement.getExpression() != null) {
            symbolTable.getBinding(Symbol.getSymbol(declarationStatement.getId())).setValue(operandStack.pop());
        }
    }

    @Override
    public boolean proceedWithAssignmentStatement(AssignmentStatement assignmentStatement) {
        return true;
    }

    @Override
    public void leaveAssignmentStatement(AssignmentStatement assignmentStatement) {
        symbolTable.getBinding(Symbol.getSymbol(assignmentStatement.getId())).setValue(operandStack.pop());
    }

    @Override
    public boolean proceedWithBinaryOperatorExpression(BinaryOperatorExpression binaryOperatorExpression) {
        return true;
    }

    @Override
    public void leaveBinaryOperatorExpression(BinaryOperatorExpression binaryOperatorExpression) {
        Object rhs = operandStack.pop();
        Object lhs = operandStack.pop();
        Object result = ExecBinaryOperator.getExecOperator(binaryOperatorExpression.getOperator(),
                binaryOperatorExpression.getLhs(), binaryOperatorExpression.getRhs()).execute(lhs, rhs);
        checkNotNull(result);
        operandStack.push(result);
    }

    @Override
    public boolean proceedWithUnaryOperatorExpression(UnaryOperatorExpression unaryOperatorExpression) {
        return true;
    }

    @Override
    public void leaveUnaryOperatorExpression(UnaryOperatorExpression unaryOperatorExpression) {
        Object result = ExecUnaryOperator.getExecUnaryOperator(unaryOperatorExpression.getOperator(),
                unaryOperatorExpression.getExpression()).execute(operandStack.pop());
        operandStack.push(result);
    }

    @Override
    public boolean proceedWithBooleanExpression(BooleanExpression booleanExpression) {
        return true;
    }

    @Override
    public void visitBooleanExpression(BooleanExpression booleanExpression) {
        operandStack.push(booleanExpression.getValue());
    }

    @Override
    public boolean proceedWithDecimalExpression(DecimalExpression decimalExpression) {
        return true;
    }

    @Override
    public void visitDecimalExpression(DecimalExpression decimalExpression) {
        operandStack.push(decimalExpression.getValue());
    }

    @Override
    public boolean proceedWithIdExpression(IdExpression idExpression) {
        return true;
    }

    @Override
    public void visitIdExpression(IdExpression idExpression) {
        operandStack.push(symbolTable.getBinding(Symbol.getSymbol(idExpression.getId())).getValue());
    }

    @Override
    public boolean proceedWithBlock(Block block) {
        return true;
    }

    @Override
    public boolean proceedWithNumExpression(NumExpression numExpression) {
        return true;
    }

    @Override
    public void visitNumExpression(NumExpression numExpression) {
        operandStack.push(numExpression.getValue());
    }

    @Override
    public boolean proceedWithTextExpression(TextExpression textExpression) {
        return true;
    }

    @Override
    public void visitTextExpression(TextExpression textExpression) {
        operandStack.push(textExpression.getValue());
    }

    @Override
    public boolean proceedWithProgramImpl(ProgramImpl program) {
        return true;
    }

    @Override
    public boolean proceedWithPairStatementList(PairStatementList pairStatementList) {
        return true;
    }

    @Override
    public boolean proceedWithLastStatementList(LastStatementList lastStatementList) {
        return true;
    }

    @Override
    public boolean proceedWithConditionalStatement(ConditionalStatement conditionalStatement) {
        return true;
    }
}
