package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

/**
 * implements algorithms and logic which handle one aspect or interpretation of an AST for grammar 3.1.
 * like e.g. an interpreter of a program written in language defined by grammar 3.1 for which an AST
 * was created by the AST parser.
 *
 * Created by georg on 13.01.16.
 */
public interface AstItemVisitor {
    boolean proceedWithAssignmentStatement(AssignmentStatement assignmentStatement);

    void visitAssignmentStatement(AssignmentStatement assignmentStatement);
}
