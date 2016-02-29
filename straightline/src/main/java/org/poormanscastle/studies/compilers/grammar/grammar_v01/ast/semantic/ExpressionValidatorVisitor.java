package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.semantic;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.AssignmentStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.AstItemVisitorAdapter;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.BinaryOperator;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.BinaryOperatorExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.DeclarationStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.Expression;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.ExpressionState;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.IdExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.LastExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.LastStatementList;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.PairExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.PairStatementList;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.PrintStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.ProgramImpl;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.Type;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.UnaryOperator;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.UnaryOperatorExpression;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Binding;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Symbol;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable.SymbolTable;

import static com.google.common.base.Preconditions.checkState;

/**
 * this visitor searches for expression subtrees and having found one visits all subexpressions and
 * tests the operands for compatibility.
 * <p/>
 * Expressions are valid if both operands are of the same type.
 * <p/>
 * It is also possible to combine certain types. If two different types get combined in one expression,
 * it is necessary that one operand gets cast to the type of the other operand so that an operator implementation
 * can be picked. Here is a chart showing what types are compatible and in what direction the casting will occur.
 * <p/>
 * Expression can only be validated after all sub expressions have been checked. Thus, the evaluation logic should be
 * implemented in the leaveSomething methods. there, the state of subexpressions will be checked, as well as there
 * return value. This information will be used for the validation logic of the current expression.
 * <p/>
 * Created by 02eex612 on 22.02.2016.
 */
public class ExpressionValidatorVisitor extends AstItemVisitorAdapter {

    /**
     * SymbolTable gets created by, populated by and handed over from the SymbolTableCreatorVisitor.
     */
    private SymbolTable symbolTable;

    private boolean astIsValid = true;

    public ExpressionValidatorVisitor(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
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
    public boolean proceedWithAssignmentStatement(AssignmentStatement assignmentStatement) {
        return true;
    }

    @Override
    public boolean proceedWithBinaryOperatorExpression(BinaryOperatorExpression binaryOperatorExpression) {
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
    public boolean proceedWithLastStatementList(LastStatementList lastStatementList) {
        return true;
    }

    @Override
    public boolean proceedWithPrintStatement(PrintStatement printStatement) {
        return true;
    }

    @Override
    public boolean proceedWithUnaryOperatorExpression(UnaryOperatorExpression unaryOperatorExpression) {
        return true;
    }

    @Override
    public boolean proceedWithIdExpression(IdExpression idExpression) {
        return true;
    }

    @Override
    public void visitIdExpression(IdExpression idExpression) {
        String errMsg = "";
        Binding binding = symbolTable.getBinding(Symbol.getSymbol(idExpression.getId()));
        if (binding == null) {
            errMsg = StringUtils.join("Error at ", idExpression.getCodePosition(),
                    ": variable ", idExpression.getId(), " may not have been declared.");
        } else {
            idExpression.setValueType(Type.valueOf(binding.getDeclaredType()));
            idExpression.setState(ExpressionState.VALID);
        }
        if (!StringUtils.isBlank(errMsg)) {
            System.err.print(StringUtils.join(errMsg, "\n"));
            astIsValid = false;
        }
    }

    @Override
    public void leaveUnaryOperatorExpression(UnaryOperatorExpression unaryOperatorExpression) {
        checkState(unaryOperatorExpression.getState() == ExpressionState.NOT_DETERMINED_YET);
        Expression expression = unaryOperatorExpression.getExpression();
        UnaryOperator operator = unaryOperatorExpression.getOperator();

        String errMsg = "";
        if (expression.getState() != ExpressionState.VALID) {
            unaryOperatorExpression.setState(ExpressionState.OPERANDS_INVALID);
            errMsg = StringUtils.join("Error at ", unaryOperatorExpression.getCodePosition(),
                    ": sub expression is invalid.");
        } else if (!operator.supportsType(expression.getValueType())) {
            errMsg = StringUtils.join("Error at ", unaryOperatorExpression.getCodePosition(),
                    ": operator ", operator.getLabel(), " is incompatible with operand type ", expression.getValueType());
        } else {
            unaryOperatorExpression.setState(ExpressionState.VALID);
        }
        if (!StringUtils.isBlank(errMsg)) {
            System.err.print(StringUtils.join(errMsg, "\n"));
            astIsValid = false;
        }
    }

    @Override
    public boolean proceedWithDeclarationStatement(DeclarationStatement declarationStatement) {
        return true;
    }

    @Override
    public void visitDeclarationStatement(DeclarationStatement declarationStatement) {
        String errMsg = "";
        Binding binding = symbolTable.getBinding(Symbol.getSymbol(declarationStatement.getId()));
        if (binding == null) {
            errMsg = StringUtils.join("Error at ", declarationStatement.getCodePosition(),
                    ": variable ", declarationStatement.getId(), " may not have been declared.");
        }
        if (!StringUtils.isBlank(errMsg)) {
            System.err.print(StringUtils.join(errMsg, "\n"));
            astIsValid = false;
        }
    }

    @Override
    public void leaveDeclarationStatement(DeclarationStatement declarationStatement) {
        String errMsg = "";
        Expression rhs = declarationStatement.getExpression();
        // in the DeclStm the lhs is always ok, or it would not have been recognized as a DeclStm.
        // the rhs can be an expression or null. if the rhs is null than this DeclStm is valid.
        // if the rhs is not null, than it can be invalid or the types can be incompatible.
        // Otherwise this DeclStm is valid.

        // the next statement must work because this line was recognized as a DeclStm. If we run into an Exception here
        // the bug must be fixed somewhere else, e.g. SymbolTableCreatorVisitor.
        Type lhsType = Type.valueOf(symbolTable.getBinding(Symbol.getSymbol(declarationStatement.getId())).getDeclaredType());
        if (rhs != null && rhs.getState() != ExpressionState.VALID) {
            // this errMsg just vaguely repeats what was earlier reported more specifically for the sub expression
            // errMsg = StringUtils.join("Error at ", rhs.getCodePosition(), ": Expression is invalid.");
        } else if (rhs != null && !Type.isRhsAssignableToLhs(lhsType, rhs.getValueType())) {
            errMsg = StringUtils.join("Error at ", declarationStatement.getCodePosition(), ": the type ",
                    rhs.getValueType(), " cannot be assigned to ", lhsType, ".");
        }
        if (!StringUtils.isBlank(errMsg)) {
            System.err.print(StringUtils.join(errMsg, "\n"));
            astIsValid = false;
        }
    }

    @Override
    public void visitAssignmentStatement(AssignmentStatement assignmentStatement) {
    }

    @Override
    public void leaveAssignmentStatement(AssignmentStatement assignmentStatement) {
        String errMsg = "";
        Expression rhs = assignmentStatement.getExpression();
        Binding binding = symbolTable.getBinding(Symbol.getSymbol(assignmentStatement.getId()));
        Type lhsType = binding == null ? Type.UNDEFINED : Type.valueOf(binding.getDeclaredType());
        if (lhsType == Type.UNDEFINED || lhsType == null) {
            errMsg = StringUtils.join("Error at ", assignmentStatement.getCodePosition(),
                    ": variable ", assignmentStatement.getId(), " may not have been declared.");
        } else if (rhs.getState() != ExpressionState.VALID) {
            // this errMsg just vaguely repeats what was earlier reported more specifically for the sub expression
            // errMsg = StringUtils.join("Error at ", assignmentStatement.getCodePosition(), ": Expression is invalid.");
        } else if (!Type.areTypesCompatible(lhsType, rhs.getValueType())) {
            errMsg = StringUtils.join("Error at ", assignmentStatement.getCodePosition(), ": the operand types ",
                    lhsType, " and ", rhs.getValueType(), " are incompatible.");
        }
        if (!StringUtils.isBlank(errMsg)) {
            System.err.print(StringUtils.join(errMsg, "\n"));
            astIsValid = false;
        }
    }

    @Override
    public void leaveBinaryOperatorExpression(BinaryOperatorExpression binaryOperatorExpression) {
        checkState(binaryOperatorExpression.getState() == ExpressionState.NOT_DETERMINED_YET);
        Expression lhs = binaryOperatorExpression.getLhs(), rhs = binaryOperatorExpression.getRhs();
        BinaryOperator operator = binaryOperatorExpression.getOperator();

        String errMsg = "";
        if (lhs.getState() != ExpressionState.VALID || rhs.getState() != ExpressionState.VALID) {
            binaryOperatorExpression.setState(ExpressionState.OPERANDS_INVALID);
            // this errMsg just vaguely repeats what was earlier reported more specifically for the sub expression
            // errMsg = StringUtils.join("Error at ", binaryOperatorExpression.getCodePosition(), ": One or more sub expressions are invalid.");
        } else if (lhs.getValueType() == rhs.getValueType() && operator.supportsType(lhs.getValueType())) {
            binaryOperatorExpression.setState(ExpressionState.VALID);
        } else if (lhs.getValueType() == rhs.getValueType() && !operator.supportsType(lhs.getValueType())) {
            binaryOperatorExpression.setState(ExpressionState.OPERATOR_INCOMPATIBLE);
            errMsg = StringUtils.join("Error at ", binaryOperatorExpression.getCodePosition(),
                    ": operator ", operator.getLabel(), " is incompatible with operand types ", lhs.getValueType(),
                    " and ", rhs.getValueType(), ".");
        } else if (lhs.getValueType() != rhs.getValueType() && !Type.areTypesCompatible(lhs.getValueType(), rhs.getValueType())) {
            binaryOperatorExpression.setState(ExpressionState.OPERANDS_INCOMPATIBLE);
            errMsg = StringUtils.join("Error at ", binaryOperatorExpression.getCodePosition(),
                    ": the operand types ", lhs.getValueType(), " and ", rhs.getValueType(), " are incompatible.");
        } else {
            binaryOperatorExpression.setState(ExpressionState.VALID);
        }
        if (!StringUtils.isBlank(errMsg)) {
            System.err.print(StringUtils.join(errMsg, "\n"));
            astIsValid = false;
        }
    }

    @Override
    public boolean isAstValid() {
        return astIsValid;
    }
}
