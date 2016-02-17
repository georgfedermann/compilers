package org.poormanscastle.studies.compilers.utils.grammartools.ast;

import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.AssignmentStatement;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.EseqExpression;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.IdExpression;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.LastExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.NumExpression;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.OperatorExpression;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.PairExpressionList;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.PrintStatement;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.StatementList;

/**
 * implements algorithms and logic which handle one aspect or interpretation of an AST for grammar 3.1.
 * like e.g. an interpreter of a program written in language defined by grammar 3.1 for which an AST
 * was created by the AST parser.
 * <p>
 * Created by georg on 13.01.16.
 */
public interface AstItemVisitor {

    boolean proceedWithAssignmentStatement(AssignmentStatement assignmentStatement);

    void visitAssignmentStatement(AssignmentStatement assignmentStatement);

    void leaveAssignmentStatement(AssignmentStatement assignmentStatement);

    boolean proceedWithIdExpression(IdExpression idExpression);

    void visitIdExpression(IdExpression idExpression);

    void leaveIdExpression(IdExpression idExpression);

    boolean proceedWithNumExpression(NumExpression numExpression);

    void visitNumExpression(NumExpression numExpression);

    void leaveNumExpression(NumExpression numExpression);

    boolean proceedWithEseqExpression(EseqExpression eseqExpression);

    void visitEseqExpression(EseqExpression eseqExpression);

    void leaveEseqExpression(EseqExpression eseqExpression);

    boolean proceedWithPrintStatement(PrintStatement printStatement);

    void visitPrintStatement(PrintStatement printStatement);

    void leavePrintStatement(PrintStatement printStatement);

    boolean proceedWithPairExpressionList(PairExpressionList pairExpressionList);

    void visitPairExpressionList(PairExpressionList pairExpressionList);

    void leavePairExpressionList(PairExpressionList pairExpressionList);

    boolean proceedWithLastExpressionList(LastExpressionList lastExpressionList);

    void visitLastExpressionList(LastExpressionList lastExpressionList);

    void leaveLastExpressionList(LastExpressionList lastExpressionList);

    boolean proceedWithOperatorExpression(OperatorExpression operatorExpression);

    void visitOperatorExpression(OperatorExpression operatorExpression);

    void leaveOperatorExpression(OperatorExpression operatorExpression);

    boolean proceedWithStatementList(StatementList statementList);

    void visitStatementList(StatementList statementList);

    void leaveStatementList(StatementList statementList);
    
}
