package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

/**
 * implements algorithms and logic which handle one aspect or interpretation of an AST for grammar 3.1.
 * like e.g. an interpreter of a program written in language defined by grammar 3.1 for which an AST
 * was created by the AST parser.
 * <p/>
 * Created by georg on 13.01.16.
 */
public interface AstItemVisitor {

    /**
     * this method can be called to check whether this visitor found that the AST is valid or not.
     *
     * @return
     */
    boolean isAstValid();

    boolean proceedWithPrintStatement(PrintStatement printStatement);

    void visitPrintStatement(PrintStatement printStatement);

    void leavePrintStatement(PrintStatement printStatement);

    boolean proceedWithProgramImpl(ProgramImpl program);

    void visitProgramImpl(ProgramImpl program);

    void leaveProgramImpl(ProgramImpl program);

    boolean proceedWithPairStatementList(PairStatementList pairStatementList);

    void visitPairStatementList(PairStatementList pairStatementList);

    void leavePairStatementList(PairStatementList pairStatementList);

    boolean proceedWithLastStatementList(LastStatementList lastStatementList);

    void visitLastStatementList(LastStatementList lastStatementList);

    void leaveLastStatementList(LastStatementList lastStatementList);

    boolean proceedWithPairExpressionList(PairExpressionList pairExpressionList);

    void visitPairExpressionList(PairExpressionList pairExpressionList);

    void leavePairExpressionList(PairExpressionList pairExpressionList);

    void leaveLastExpressionList(LastExpressionList lastExpressionList);

    void visitLastExpressionList(LastExpressionList lastExpressionList);

    boolean proceedWithLastExpressionList(LastExpressionList lastExpressionList);

    boolean proceedWithAssignmentStatement(AssignmentStatement assignmentStatement);

    void visitAssignmentStatement(AssignmentStatement assignmentStatement);

    void leaveAssignmentStatement(AssignmentStatement assignmentStatement);

    void leaveDeclarationStatement(DeclarationStatement declarationStatement);

    void visitDeclarationStatement(DeclarationStatement declarationStatement);

    boolean proceedWithDeclarationStatement(DeclarationStatement declarationStatement);

    void leaveIdExpression(IdExpression idExpression);

    void visitIdExpression(IdExpression idExpression);

    boolean proceedWithIdExpression(IdExpression idExpression);

    void leaveNumExpression(NumExpression numExpression);

    void visitNumExpression(NumExpression numExpression);

    boolean proceedWithNumExpression(NumExpression numExpression);

    void leaveDecimalExpression(DecimalExpression decimalExpression);

    void visitDecimalExpression(DecimalExpression decimalExpression);

    boolean proceedWithDecimalExpression(DecimalExpression decimalExpression);

    void leaveBooleanExpression(BooleanExpression booleanExpression);

    void visitBooleanExpression(BooleanExpression booleanExpression);

    boolean proceedWithBooleanExpression(BooleanExpression booleanExpression);

    void leaveTextExpression(TextExpression textExpression);

    void visitTextExpression(TextExpression textExpression);

    boolean proceedWithTextExpression(TextExpression textExpression);

    void leaveBinaryOperatorExpression(BinaryOperatorExpression binaryOperatorExpression);

    void visitBinaryOperatorExpression(BinaryOperatorExpression binaryOperatorExpression);

    boolean proceedWithBinaryOperatorExpression(BinaryOperatorExpression binaryOperatorExpression);

    void leaveUnaryOperatorExpression(UnaryOperatorExpression unaryOperatorExpression);

    void visitUnaryOperatxorExpression(UnaryOperatorExpression unaryOperatorExpression);

    boolean proceedWithUnaryOperatorExpression(UnaryOperatorExpression unaryOperatorExpression);

    boolean proceedWithConditionalStatement(ConditionalStatement conditionalStatement);

    void visitConditionalStatement(ConditionalStatement conditionalStatement);

    void leaveConditionalStatement(ConditionalStatement conditionalStatement);

    boolean proceedWithBlock(Block block);

    void visitBlock(Block block);

    void leaveBlock(Block block);

    boolean proceedWithThenStatement(ThenStatement thenStatement);

    void visitThenStatement(ThenStatement thenStatement);

    void leaveThenStatement(ThenStatement thenStatement);

    boolean proceedWithElseStatement(ElseStatement elseStatement);

    void visitElseStatement(ElseStatement elseStatement);

    void leaveElseStatement(ElseStatement elseStatement);

    boolean proceedWithWhileStatement(WhileStatement whileStatement);

    void visitWhileStatement(WhileStatement whileStatement);

    void leaveWhileStatement(WhileStatement whileStatement);

    boolean proceedWithWhileBody(WhileBody whileBody);

    void visitWhileBody(WhileBody whileBody);

    void leaveWhileBody(WhileBody whileBody);

    boolean proceedWithForStatement(ForStatement forStatement);

    void visitForStatement(ForStatement forStatement);

    void leaveForStatement(ForStatement forStatement);

}
