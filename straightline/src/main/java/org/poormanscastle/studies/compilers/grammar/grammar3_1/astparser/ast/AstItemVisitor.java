package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

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

    boolean proceedWithIdFactor(IdFactor idFactor);

    void visitIdFactor(IdFactor idFactor);

    void leaveIdFactor(IdFactor idFactor);

    boolean proceedWithNumFactor(NumFactor numFactor);

    void visitNumFactor(NumFactor numFactor);

    void leaveNumFactor(NumFactor numFactor);

    boolean proceedWithPlusOperator(PlusOperator plusOperator);

    void visitPlusOperator(PlusOperator plusOperator);

    void leavePlusOperator(PlusOperator plusOperator);

    boolean proceedWithMinusOperator(MinusOperator minusOperator);

    void visitMinusOperator(MinusOperator minusOperator);

    void leaveMinusOperator(MinusOperator minusOperator);

    boolean proceedWithTimesOperator(TimesOperator timesOperator);

    void visitTimesOperator(TimesOperator timesOperator);

    void leaveTimesOperator(TimesOperator timesOperator);

    boolean proceedWithDivOperator(DivOperator divOperator);

    void visitDivOperator(DivOperator divOperator);

    void leaveDivOperator(DivOperator divOperator);
    
}
