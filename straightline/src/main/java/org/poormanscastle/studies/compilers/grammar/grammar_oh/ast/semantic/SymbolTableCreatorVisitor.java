package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.semantic;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.AssignmentStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.AstItemVisitorAdapter;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.BinaryOperator;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.BinaryOperatorExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Block;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ConditionalStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.DeclarationStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ElseStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Expression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ExpressionState;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ForStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.IdExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.LastExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.LastStatementList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.PairExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.PairStatementList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.PrintStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ProgramImpl;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ThenStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Type;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.UnaryOperator;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.UnaryOperatorExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.WhileBody;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.WhileStatement;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Binding;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Symbol;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable.SymbolTable;
import org.poormanscastle.studies.compilers.utils.grammartools.exceptions.CompilerException;

import static com.google.common.base.Preconditions.checkState;

/**
 * Each new identifier declaration (variable, function name, what ever) creates a new environment.
 * A variable can only be used after it's been declared. Thus, also within a block or even within a language dialect
 * that supports no blocks at all, there are multiple environments or else the semantic analysis could not
 * clearly decide if a variable is used before it was declared.
 * <p/>
 * SymbolTable management and expression validation have to be merged into one visitor, because environments will be
 * removed when they go out of scope. Validation has to take place while environments created within the symboltable
 * are still valid.
 * <p/>
 * this visitor searches for expression subtrees and having found one visits all subexpressions and tests the operands
 * for compatibility.
 * <p/>
 * Expressions are valid if both operands are of the same type, or the types of the operands are compatible,
 * and the types of the operands are compatible with the operator.
 * <p/>
 * Expressions can only be validated after all sub expressions have been checked. Thus, the evaluation logic can only
 * be implemented in the leaveSomething-methods(). There, the state of the subexpressions will be checked, as well as
 * their respective value types. This collected information will be used for the validation logic of the current
 * expression.
 * <p/>
 * Created by 02eex612 on 19.02.2016.
 */
public class SymbolTableCreatorVisitor extends AstItemVisitorAdapter {

    private SymbolTable symbolTable = new SymbolTable();

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    @Override
    public boolean proceedWithProgramImpl(ProgramImpl program) {
        return true;
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
    }

    @Override
    public boolean proceedWithAssignmentStatement(AssignmentStatement assignmentStatement) {
        return true;
    }

    @Override
    public void leaveAssignmentStatement(AssignmentStatement assignmentStatement) {
        Expression rhs = assignmentStatement.getExpression();
        Binding binding = symbolTable.getBinding(Symbol.getSymbol(assignmentStatement.getId()));
        Type lhsType = binding == null ? Type.UNDEFINED : Type.valueOf(binding.getDeclaredType());
        // rhs.getState() != ExpressionState.VALID has been delt with when the expression was invalidated. Skip it here.
        if (lhsType == Type.UNDEFINED || lhsType == null) {
            System.err.print(StringUtils.join("Error at ", assignmentStatement.getCodePosition(),
                    ": variable ", assignmentStatement.getId(), " may not have been declared.\n"));
            invalidateAst();
        } else if (!Type.isRhsAssignableToLhs(lhsType, rhs.getValueType())) {
            System.err.print(StringUtils.join("Error at ", assignmentStatement.getCodePosition(), ": the type ",
                    rhs.getValueType(), " cannot be assigned to ", lhsType, ".\n"));
            invalidateAst();
        }
    }

    @Override
    public boolean proceedWithIdExpression(IdExpression idExpression) {
        return true;
    }

    @Override
    public void visitIdExpression(IdExpression idExpression) {
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
    }

    @Override
    public boolean proceedWithUnaryOperatorExpression(UnaryOperatorExpression unaryOperatorExpression) {
        return true;
    }

    @Override
    public void leaveUnaryOperatorExpression(UnaryOperatorExpression unaryOperatorExpression) {
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
    }

    @Override
    public boolean proceedWithBinaryOperatorExpression(BinaryOperatorExpression binaryOperatorExpression) {
        return true;
    }

    @Override
    public void leaveBinaryOperatorExpression(BinaryOperatorExpression binaryOperatorExpression) {
        checkState(binaryOperatorExpression.getState() == ExpressionState.NOT_DETERMINED_YET);
        Expression lhs = binaryOperatorExpression.getLhs(), rhs = binaryOperatorExpression.getRhs();
        BinaryOperator operator = binaryOperatorExpression.getOperator();

        if (lhs.getState() != ExpressionState.VALID || rhs.getState() != ExpressionState.VALID) {
            binaryOperatorExpression.setState(ExpressionState.OPERANDS_INVALID);
            invalidateAst();
            // this errMsg just vaguely repeats what was earlier reported more specifically for the sub expression
            // errMsg = StringUtils.join("Error at ", binaryOperatorExpression.getCodePosition(), ": One or more sub expressions are invalid.");
        } else if (!Type.areTypesCompatible(lhs.getValueType(), rhs.getValueType())) {
            binaryOperatorExpression.setState(ExpressionState.OPERANDS_INCOMPATIBLE);
            System.err.print(StringUtils.join("Error at ", binaryOperatorExpression.getCodePosition(),
                    ": the operand types ", lhs.getValueType(), " and ", rhs.getValueType(), " are incompatible.\n"));
            invalidateAst();
        } else if (!operator.supportsType(lhs.getValueType())) {
            binaryOperatorExpression.setState(ExpressionState.OPERATOR_INCOMPATIBLE);
            System.err.print(StringUtils.join("Error at ", binaryOperatorExpression.getCodePosition(),
                    ": operator ", operator.getLabel(), " is incompatible with operand types ", lhs.getValueType(),
                    " and ", rhs.getValueType(), ".\n"));
            invalidateAst();
        } else {
            binaryOperatorExpression.setState(ExpressionState.VALID);
        }
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
    public boolean proceedWithConditionalStatement(ConditionalStatement conditionalStatement) {
        return true;
    }

    @Override
    public void leaveConditionalStatement(ConditionalStatement conditionalStatement) {
        Expression condition = conditionalStatement.getCondition();
        if (condition.getState() == ExpressionState.VALID && !Type.isRhsAssignableToLhs(Type.BOOLEAN, condition.getValueType())) {
            System.err.print(StringUtils.join("Error at ", condition.getCodePosition(),
                    ": expected expression type BOOLEAN but found ", condition.getValueType(), "."));
            invalidateAst();
        }
    }

    @Override
    public boolean proceedWithThenStatement(ThenStatement thenStatement) {
        return true;
    }

    @Override
    public boolean proceedWithElseStatement(ElseStatement elseStatement) {
        return true;
    }

    @Override
    public boolean proceedWithWhileStatement(WhileStatement whileStatement) {
        return true;
    }

    @Override
    public void leaveWhileStatement(WhileStatement whileStatement) {
        Expression condition = whileStatement.getCondition();
        if (!Type.isRhsAssignableToLhs(Type.BOOLEAN, condition.getValueType())) {
            System.err.print(StringUtils.join("Error at ", condition.getCodePosition(),
                    ": expected expression type BOOLEAN but found ", condition.getValueType(), "."));
            invalidateAst();
        }
    }

    @Override
    public boolean proceedWithForStatement(ForStatement forStatement) {
        return true;
    }

    @Override
    public void leaveForStatement(ForStatement forStatement) {
        Expression condition = forStatement.getCondition();
        if (!Type.isRhsAssignableToLhs(Type.BOOLEAN, condition.getValueType())) {
            System.err.print(StringUtils.join("Error at ", condition.getCodePosition(),
                    ": expected expression type BOOLEAN but found ", condition.getValueType(), "."));
            invalidateAst();
        }
    }

    @Override
    public boolean proceedWithWhileBody(WhileBody whileBody) {
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
    public boolean proceedWithPairExpressionList(PairExpressionList pairExpressionList) {
        return true;
    }

    @Override
    public boolean proceedWithLastExpressionList(LastExpressionList lastExpressionList) {
        return true;
    }

    @Override
    public boolean proceedWithPrintStatement(PrintStatement printStatement) {
        return true;
    }

}
