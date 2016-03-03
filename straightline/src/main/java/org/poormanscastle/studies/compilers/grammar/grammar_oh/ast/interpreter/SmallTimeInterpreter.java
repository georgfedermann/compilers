package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.interpreter;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.AstItemVisitorAdapter;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.LastStatementList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.AssignmentStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.BinaryOperator;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.BinaryOperatorExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Block;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.BooleanExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ConditionalStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.DecimalExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.DeclarationStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Expression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ExpressionState;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.IdExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.LastExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.NumExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.PairExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.PairStatementList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.PrintStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ProgramImpl;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.TextExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Type;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.UnaryOperator;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.UnaryOperatorExpression;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Binding;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Symbol;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable.SymbolTable;
import org.poormanscastle.studies.compilers.utils.grammartools.exceptions.CompilerException;

import com.google.common.collect.Lists;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

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
    public void visitDeclarationStatement(DeclarationStatement declarationStatement) {
        // handle symboltable management part
        try {
            symbolTable.addSymbol(declarationStatement.getId(), declarationStatement.getType().name());
        } catch (CompilerException e) {
            System.err.print(StringUtils.join("Error at ", declarationStatement.getCodePosition(),
                    ": variable ", declarationStatement.getId(), " was already declared in this scope.\n"));
            invalidateAst();
        }
        // handle expression validation TODO this cannot really happen, after the statement above. OK to delete it?
        if (symbolTable.getBinding(Symbol.getSymbol(declarationStatement.getId())) == null) {
            System.err.println(StringUtils.join("Error at ", declarationStatement.getCodePosition(),
                    ": variable ", declarationStatement.getId(), " may not have been declared."));
            invalidateAst();
        }
    }

    @Override
    public void leaveDeclarationStatement(DeclarationStatement declarationStatement) {
        // semantic analysis part:
        Expression rhs = declarationStatement.getExpression();
        // in the DeclStm the lhs is always ok, or it would not have been recognized as a DeclStm.
        // the rhs can be an expression or null. if the rhs is null than this DeclStm is valid.
        // if the rhs is not null, than it can be invalid or the types can be incompatible.
        // Otherwise this DeclStm is valid.

        // the next statement must work because this line was recognized as a DeclStm. If we run into an Exception here
        // the bug must be fixed somewhere else, e.g. SymbolTableCreatorVisitor.
        Type lhsType = Type.valueOf(symbolTable.getBinding(Symbol.getSymbol(declarationStatement.getId())).getDeclaredType());
        // if rhs.getState() != ExpressionState.VALID the error was reported when the expression was invalidated. skip it here.
        if (rhs != null && rhs.getState() == ExpressionState.VALID && !Type.isRhsAssignableToLhs(lhsType, rhs.getValueType())) {
            System.err.print(StringUtils.join("Error at ", declarationStatement.getCodePosition(), ": the type ",
                    rhs.getValueType(), " cannot be assigned to ", lhsType, ".\n"));
            invalidateAst();
        }

        // interpreter part:
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
        // semantic analysis part:
        Expression rhs = assignmentStatement.getExpression();
        Binding binding = symbolTable.getBinding(Symbol.getSymbol(assignmentStatement.getId()));
        Type lhsType = binding == null ? Type.UNDEFINED : Type.valueOf(binding.getDeclaredType());
        // rhs.getState() != ExpressionState.VALID has been delt with when the expression was invalidated. Skip it here.
        if (lhsType == Type.UNDEFINED || lhsType == null) {
            System.err.print(StringUtils.join("Error at ", assignmentStatement.getCodePosition(),
                    ": variable ", assignmentStatement.getId(), " may not have been declared.\n"));
            invalidateAst();
        } else if (!Type.areTypesCompatible(lhsType, rhs.getValueType())) {
            System.err.print(StringUtils.join("Error at ", assignmentStatement.getCodePosition(), ": the operand types ",
                    lhsType, " and ", rhs.getValueType(), " are incompatible.\n"));
            invalidateAst();
        }

        // interpreter part:
        symbolTable.getBinding(Symbol.getSymbol(assignmentStatement.getId())).setValue(operandStack.pop());
    }

    @Override
    public boolean proceedWithBinaryOperatorExpression(BinaryOperatorExpression binaryOperatorExpression) {
        return true;
    }

    @Override
    public void leaveBinaryOperatorExpression(BinaryOperatorExpression binaryOperatorExpression) {
        // semantic analysis part:
        checkState(binaryOperatorExpression.getState() == ExpressionState.NOT_DETERMINED_YET);
        Expression lhs = binaryOperatorExpression.getLhs(), rhs = binaryOperatorExpression.getRhs();
        BinaryOperator operator = binaryOperatorExpression.getOperator();

        String errMsg = "";
        if (lhs.getState() != ExpressionState.VALID || rhs.getState() != ExpressionState.VALID) {
            binaryOperatorExpression.setState(ExpressionState.OPERANDS_INVALID);
            invalidateAst();
            // this errMsg just vaguely repeats what was earlier reported more specifically for the sub expression
            // errMsg = StringUtils.join("Error at ", binaryOperatorExpression.getCodePosition(), ": One or more sub expressions are invalid.");
        } else if (lhs.getValueType() == rhs.getValueType() && !operator.supportsType(lhs.getValueType())) {
            binaryOperatorExpression.setState(ExpressionState.OPERATOR_INCOMPATIBLE);
            System.err.print(StringUtils.join("Error at ", binaryOperatorExpression.getCodePosition(),
                    ": operator ", operator.getLabel(), " is incompatible with operand types ", lhs.getValueType(),
                    " and ", rhs.getValueType(), ".\n"));
            invalidateAst();
        } else if (lhs.getValueType() != rhs.getValueType() && !Type.areTypesCompatible(lhs.getValueType(), rhs.getValueType())) {
            binaryOperatorExpression.setState(ExpressionState.OPERANDS_INCOMPATIBLE);
            System.err.print(StringUtils.join("Error at ", binaryOperatorExpression.getCodePosition(),
                    ": the oerand types ", lhs.getValueType(), " and ", rhs.getValueType(), " are incompatible.\n"));
            invalidateAst();
        } else {
            binaryOperatorExpression.setState(ExpressionState.VALID);
        }

        // interpreter part:
        Object rhsValue = operandStack.pop();
        Object lhsValue = operandStack.pop();
        Object result = ExecBinaryOperator.getExecOperator(binaryOperatorExpression.getOperator(),
                binaryOperatorExpression.getLhs(), binaryOperatorExpression.getRhs()).execute(lhsValue, rhsValue);
        checkNotNull(result);
        operandStack.push(result);
    }

    @Override
    public boolean proceedWithUnaryOperatorExpression(UnaryOperatorExpression unaryOperatorExpression) {
        return true;
    }

    @Override
    public void leaveUnaryOperatorExpression(UnaryOperatorExpression unaryOperatorExpression) {
        // semantic analysis part:
        checkState(unaryOperatorExpression.getState() == ExpressionState.NOT_DETERMINED_YET);
        Expression expression = unaryOperatorExpression.getExpression();
        UnaryOperator operator = unaryOperatorExpression.getOperator();

        if (expression.getState() != ExpressionState.VALID) {
            unaryOperatorExpression.setState(ExpressionState.OPERANDS_INVALID);
            System.err.print(StringUtils.join("Error at ", unaryOperatorExpression.getCodePosition(),
                    ": sub expression is invalid.\n"));
            invalidateAst();
        } else if (!operator.supportsType(expression.getValueType())) {
            unaryOperatorExpression.setState(ExpressionState.OPERATOR_INCOMPATIBLE);
            System.err.print(StringUtils.join("Error at ", unaryOperatorExpression.getCodePosition(),
                    ": operator ", operator.getLabel(), " is incompatible with operand type ", expression.getValueType(), ".\n"));
            invalidateAst();
        } else {
            unaryOperatorExpression.setState(ExpressionState.VALID);
        }

        // interpreter part:
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
        // semantic analysis part:
        checkState(idExpression.getState() == ExpressionState.NOT_DETERMINED_YET);
        Binding binding = symbolTable.getBinding(Symbol.getSymbol(idExpression.getId()));
        if (binding == null) {
            System.err.print(StringUtils.join("Error at ", idExpression.getCodePosition(),
                    ": variable ", idExpression.getId(), " may not have been declared.\n"));
            invalidateAst();
        } else {
            idExpression.setValueType(Type.valueOf(binding.getDeclaredType()));
            idExpression.setState(ExpressionState.VALID);
        }

        // interpreter part:
        operandStack.push(symbolTable.getBinding(Symbol.getSymbol(idExpression.getId())).getValue());
    }

    @Override
    public boolean proceedWithBlock(Block block) {
        return true;
    }

    @Override
    public void visitBlock(Block block) {
        symbolTable.newScope();
    }

    @Override
    public void leaveBlock(Block block) {
        symbolTable.endScope();
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
