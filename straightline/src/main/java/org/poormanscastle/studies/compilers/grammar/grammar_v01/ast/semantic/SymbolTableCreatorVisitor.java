package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.semantic;

import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.AssignmentStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.AstItemVisitor;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.BinaryOperatorExpression;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.BooleanExpression;
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
import org.poormanscastle.studies.compilers.utils.grammartools.ast.SymbolTable;

/**
 * this visitor has to create all entries for the symbol table. therefore it needs to visit
 * all nodes which can create new identifiers, which are declaration statements in language v0.1
 * <p/>
 * The checking of AssignmentStatements and OperatorExpressions etc. for correctness is done
 * by the next visitor, which uses the symbol table created by this visitor.
 * <p/>
 * Created by 02eex612 on 19.02.2016.
 */
public class SymbolTableCreatorVisitor implements AstItemVisitor {

    private SymbolTable symbolTable = new SymbolTable();

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    @Override
    public void leaveDeclarationStatement(DeclarationStatement declarationStatement) {

    }

    @Override
    public void visitDeclarationStatement(DeclarationStatement declarationStatement) {
        symbolTable.addSymbol(declarationStatement.getId(), declarationStatement.getType().name());
    }

    @Override
    public boolean proceedWithDeclarationStatement(DeclarationStatement declarationStatement) {
        return true;
    }

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
        return true;
    }

    @Override
    public void visitProgramImpl(ProgramImpl program) {

    }

    @Override
    public void leaveProgramImpl(ProgramImpl program) {

    }

    @Override
    public boolean proceedWithPairStatementList(PairStatementList pairStatementList) {
        return true;
    }

    @Override
    public void visitPairStatementList(PairStatementList pairStatementList) {

    }

    @Override
    public void leavePairStatementList(PairStatementList pairStatementList) {

    }

    @Override
    public boolean proceedWithLastStatementList(LastStatementList lastStatementList) {
        return true;
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
