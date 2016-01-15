package org.poormanscastle.studies.compilers.grammar.grammar3_1.visitors;

import java.util.Stack;

import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.AssignmentStatement;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.AstItem;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.AstItemVisitor;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.EseqExpression;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.IdExpression;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.LastExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.NumExpression;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.OperatorExpression;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.PairExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.PrintStatement;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.StatementList;

/**
 * Creates a graphical view of the AST using DOT syntax.
 * <p>
 * Created by georg on 15.01.16.
 */
public class AstPrettyPrintVisitor implements AstItemVisitor {

    /**
     * these items are put onto the stack and used to generate output.
     */
    private class StackItem{

    }

    /**
     * using this stack the visitor keeps track of the current parent element.
     */
    private Stack<AstItem> itemStack = new Stack<>();

    /**
     * output is buffered in this string buffer.
     */
    private StringBuffer buffer = new StringBuffer();

    @Override
    public boolean proceedWithAssignmentStatement(AssignmentStatement assignmentStatement) {
        return true;
    }

    @Override
    public void visitAssignmentStatement(AssignmentStatement assignmentStatement) {

    }

    @Override
    public void leaveAssignmentStatement(AssignmentStatement assignmentStatement) {

    }

    @Override
    public boolean proceedWithIdExpression(IdExpression idExpression) {
        return true;
    }

    @Override
    public void visitIdExpression(IdExpression idExpression) {

    }

    @Override
    public void leaveIdExpression(IdExpression idExpression) {

    }

    @Override
    public boolean proceedWithNumExpression(NumExpression numExpression) {
        return true;
    }

    @Override
    public void visitNumExpression(NumExpression numExpression) {

    }

    @Override
    public void leaveNumExpression(NumExpression numExpression) {

    }

    @Override
    public boolean proceedWithEseqExpression(EseqExpression eseqExpression) {
        return true;
    }

    @Override
    public void visitEseqExpression(EseqExpression eseqExpression) {

    }

    @Override
    public void leaveEseqExpression(EseqExpression eseqExpression) {

    }

    @Override
    public boolean proceedWithPrintStatement(PrintStatement printStatement) {
        return true;
    }

    @Override
    public void visitPrintStatement(PrintStatement printStatement) {

    }

    @Override
    public void leavePrintStatement(PrintStatement printStatement) {

    }

    @Override
    public boolean proceedWithPairExpressionList(PairExpressionList pairExpressionList) {
        return true;
    }

    @Override
    public void visitPairExpressionList(PairExpressionList pairExpressionList) {

    }

    @Override
    public void leavePairExpressionList(PairExpressionList pairExpressionList) {

    }

    @Override
    public boolean proceedWithLastExpressionList(LastExpressionList lastExpressionList) {
        return true;
    }

    @Override
    public void visitLastExpressionList(LastExpressionList lastExpressionList) {

    }

    @Override
    public void leaveLastExpressionList(LastExpressionList lastExpressionList) {

    }

    @Override
    public boolean proceedWithOperatorExpression(OperatorExpression operatorExpression) {
        return true;
    }

    @Override
    public void visitOperatorExpression(OperatorExpression operatorExpression) {

    }

    @Override
    public void leaveOperatorExpression(OperatorExpression operatorExpression) {

    }

    @Override
    public boolean proceedWithStatementList(StatementList statementList) {
        return true;
    }

    @Override
    public void visitStatementList(StatementList statementList) {

    }

    @Override
    public void leaveStatementList(StatementList statementList) {

    }

}
