package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

/**
 * adapter class delivers default implementation of the AstItemVisitor interface so that the methods implementing
 * logic in any given visitor implementation don't disappear in the masses.
 * <p/>
 * Created by 02eex612 on 22.02.2016.
 */
public abstract class AstItemVisitorAdapter implements AstItemVisitor {

    @Override
    public boolean proceedWithPrintStatement(PrintStatement printStatement) {
        return false;
    }

    @Override
    public void visitPrintStatement(PrintStatement printStatement) {

    }

    @Override
    public void leavePrintStatement(PrintStatement printStatement) {

    }

    @Override
    public boolean proceedWithProgramImpl(ProgramImpl program) {
        return false;
    }

    @Override
    public void visitProgramImpl(ProgramImpl program) {

    }

    @Override
    public void leaveProgramImpl(ProgramImpl program) {

    }

    @Override
    public boolean proceedWithPairStatementList(PairStatementList pairStatementList) {
        return false;
    }

    @Override
    public void visitPairStatementList(PairStatementList pairStatementList) {

    }

    @Override
    public void leavePairStatementList(PairStatementList pairStatementList) {

    }

    @Override
    public boolean proceedWithLastStatementList(LastStatementList lastStatementList) {
        return false;
    }

    @Override
    public void visitLastStatementList(LastStatementList lastStatementList) {

    }

    @Override
    public void leaveLastStatementList(LastStatementList lastStatementList) {

    }

    @Override
    public boolean proceedWithPairExpressionList(PairExpressionList pairExpressionList) {
        return false;
    }

    @Override
    public void visitPairExpressionList(PairExpressionList pairExpressionList) {

    }

    @Override
    public void leavePairExpressionList(PairExpressionList pairExpressionList) {

    }

    @Override
    public void leaveLastExpressionList(LastExpressionList lastExpressionList) {

    }

    @Override
    public void visitLastExpressionList(LastExpressionList lastExpressionList) {

    }

    @Override
    public boolean proceedWithLastExpressionList(LastExpressionList lastExpressionList) {
        return false;
    }

    @Override
    public boolean proceedWithAssignmentStatement(AssignmentStatement assignmentStatement) {
        return false;
    }

    @Override
    public void visitAssignmentStatement(AssignmentStatement assignmentStatement) {

    }

    @Override
    public void leaveAssignmentStatement(AssignmentStatement assignmentStatement) {

    }

    @Override
    public void leaveDeclarationStatement(DeclarationStatement declarationStatement) {

    }

    @Override
    public void visitDeclarationStatement(DeclarationStatement declarationStatement) {

    }

    @Override
    public boolean proceedWithDeclarationStatement(DeclarationStatement declarationStatement) {
        return false;
    }

    @Override
    public void leaveIdExpression(IdExpression idExpression) {

    }

    @Override
    public void visitIdExpression(IdExpression idExpression) {

    }

    @Override
    public boolean proceedWithIdExpression(IdExpression idExpression) {
        return false;
    }

    @Override
    public void leaveNumExpression(NumExpression numExpression) {

    }

    @Override
    public void visitNumExpression(NumExpression numExpression) {

    }

    @Override
    public boolean proceedWithNumExpression(NumExpression numExpression) {
        return false;
    }

    @Override
    public void leaveDecimalExpression(DecimalExpression decimalExpression) {

    }

    @Override
    public void visitDecimalExpression(DecimalExpression decimalExpression) {

    }

    @Override
    public boolean proceedWithDecimalExpression(DecimalExpression decimalExpression) {
        return false;
    }

    @Override
    public void leaveBooleanExpression(BooleanExpression booleanExpression) {

    }

    @Override
    public void visitBooleanExpression(BooleanExpression booleanExpression) {

    }

    @Override
    public boolean proceedWithBooleanExpression(BooleanExpression booleanExpression) {
        return false;
    }

    @Override
    public void leaveTextExpression(TextExpression textExpression) {

    }

    @Override
    public void visitTextExpression(TextExpression textExpression) {

    }

    @Override
    public boolean proceedWithTextExpression(TextExpression textExpression) {
        return false;
    }

    @Override
    public void leaveBinaryOperatorExpression(BinaryOperatorExpression binaryOperatorExpression) {

    }

    @Override
    public void visitBinaryOperatorExpression(BinaryOperatorExpression binaryOperatorExpression) {

    }

    @Override
    public boolean proceedWithBinaryOperatorExpression(BinaryOperatorExpression binaryOperatorExpression) {
        return false;
    }

    @Override
    public void leaveUnaryOperatorExpression(UnaryOperatorExpression unaryOperatorExpression) {

    }

    @Override
    public void visitUnaryOperatxorExpression(UnaryOperatorExpression unaryOperatorExpression) {

    }

    @Override
    public boolean proceedWithUnaryOperatorExpression(UnaryOperatorExpression unaryOperatorExpression) {
        return false;
    }
}
