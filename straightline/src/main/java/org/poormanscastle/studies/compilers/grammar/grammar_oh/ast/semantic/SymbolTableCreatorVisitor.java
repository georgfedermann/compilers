package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.semantic;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Function;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.FunctionCall;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.IdExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.LastExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.LastParameterList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.LastStatementList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.PairExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.PairParameterList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.PairStatementList;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Parameter;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.PrintStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ProgramImpl;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ReturnStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.ThenStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Type;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.UnaryOperator;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.UnaryOperatorExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.WhileBody;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.WhileStatement;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Binding;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Symbol;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable.FunctionDeclaration;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable.SymbolTable;
import org.poormanscastle.studies.compilers.utils.grammartools.exceptions.SymbolAlreadyDefinedException;

import com.google.common.collect.Lists;

import static com.google.common.base.Preconditions.checkState;

/**
 * this type takes care of the semantic analysis of an AST tree representing an Oh program. while traversing the AST
 * tree it creates and manages the symbol table and validates all expressions, assignments and function calls for
 * validity.
 * <p/>
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

    /**
     * while the SymbolTableCreator is traversing a function structure, a reference to
     * this function is stored here for reference.
     */
    private Function currentFunction;
    /**
     * when traversing parameter lists of function definition, the parameters get
     * stored here intermittently until the function can grep them and reset the list.
     */
    private List<Parameter> parameters = new LinkedList<>();
    /**
     * while traversing a function call the symbol table creator uses this as a flag
     */
    private FunctionCall currentFunctionCall;
    /**
     * while traversing a function call the symbol table creator can cache argument expression here
     * for later reference.
     */
    private List<Expression> argumentList = new LinkedList<>();

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
        // handle symbol table management part
        try {
            symbolTable.addSymbol(declarationStatement.getId(), declarationStatement.getType().name());
        } catch (SymbolAlreadyDefinedException e) {
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
    public boolean proceedWithFunction(Function function) {
        return true;
    }

    @Override
    public void visitFunction(Function function) {
        this.currentFunction = function;
        symbolTable.newScope();
    }

    @Override
    public void leaveFunction(Function function) {
        symbolTable.addFunctionDeclaration(function, parameters);
        parameters.clear();
        currentFunction = null;
        symbolTable.endScope();
    }

    @Override
    public boolean proceedWithReturnStatement(ReturnStatement returnStatement) {
        return true;
    }

    @Override
    public void leaveReturnStatement(ReturnStatement returnStatement) {
        if (!Type.isRhsAssignableToLhs(currentFunction.getValueType(), returnStatement.getExpression().getValueType())) {
            System.err.print(StringUtils.join("Error at ", returnStatement.getCodePosition(), ": the type ",
                    returnStatement.getExpression().getValueType(),
                    " of the return statement is incompatible with the function's declared return value type identifier ",
                    currentFunction.getValueType(), ".\n"));
            invalidateAst();
        }
    }

    @Override
    public boolean proceedWithFunctionCall(FunctionCall functionCall) {
        return true;
    }

    @Override
    public void visitFunctionCall(FunctionCall functionCall) {
        currentFunctionCall = functionCall;
    }

    @Override
    public void leaveFunctionCall(FunctionCall functionCall) {
        boolean ok = true;
        List<String> parameterNames = null;
        Map<Symbol, Binding> parameterBindings = null;
        FunctionDeclaration functionDeclaration = null;
        if (!symbolTable.isFunctionDeclared(functionCall.getFunctionId())) {
            System.err.print(StringUtils.join("Error at ", functionCall.getCodePosition(), ": the function ",
                    functionCall.getFunctionId(), " might not have been declared.\n"));
            invalidateAst();
        } else {
            if (ok) {
                functionDeclaration = symbolTable.lookupFunctionDeclaration(functionCall.getFunctionId());
                argumentList = Lists.reverse(argumentList);
                parameterNames = functionDeclaration.getParameterNames();
                parameterBindings = functionDeclaration.getParameterBindings();
                if (argumentList.size() != parameterNames.size()) {
                    ok = false;
                }
            }
            if (ok) {
                for (int c = 0; c < argumentList.size(); c++) {
                    if (!Type.isRhsAssignableToLhs(Type.valueOf(parameterBindings.get(Symbol.getSymbol(parameterNames.get(c))).getDeclaredType()), argumentList.get(c).getValueType())) {

                        invalidateAst();
                        ok = false;
                        break;
                    }
                }
            }
            if (!ok) {
                StringBuilder errMsg = new StringBuilder("Error at ").append(functionCall.getCodePosition()).append(": Illegal arguments: expected (");
                int flag = 0;
                for (String parameterName : functionDeclaration.getParameterNames()) {
                    errMsg.append(flag++ == 0 ? "" : ",").append(functionDeclaration.getParameterBindings().get(Symbol.getSymbol(parameterName)).getDeclaredType());
                }
                errMsg.append(") but found (");
                flag = 0;
                for (Expression arg : argumentList) {
                    errMsg.append(flag++ == 0 ? "" : ",").append(arg.getValueType());
                }
                errMsg.append(").\n");
                System.err.print(errMsg);
                invalidateAst();
            }
        }
        // on leaving the FunctionCall element, reset the flag on the tree walker.
        currentFunctionCall = null;
        argumentList.clear();
    }

    @Override
    public boolean proceedWithPairParameterList(PairParameterList pairParameterList) {
        return true;
    }

    @Override
    public boolean proceedWithLastParameterList(LastParameterList lastParameterList) {
        return true;
    }

    @Override
    public boolean proceedWithParameter(Parameter parameter) {
        return true;
    }

    @Override
    public void visitParameter(Parameter parameter) {
        parameters.add(parameter);
        symbolTable.addSymbol(parameter.getId(), parameter.getType().name());
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
    public boolean proceedWithPrintStatement(PrintStatement printStatement) {
        return true;
    }

    @Override
    public boolean proceedWithPairExpressionList(PairExpressionList pairExpressionList) {
        return true;
    }

    @Override
    public void visitPairExpressionList(PairExpressionList pairExpressionList) {
        if (currentFunctionCall != null) {
            argumentList.add(pairExpressionList.getExpression());
        }
    }

    @Override
    public boolean proceedWithLastExpressionList(LastExpressionList lastExpressionList) {
        return true;
    }

    @Override
    public void visitLastExpressionList(LastExpressionList lastExpressionList) {
        if (currentFunctionCall != null) {
            argumentList.add(lastExpressionList.getExpression());
        }
    }

}
