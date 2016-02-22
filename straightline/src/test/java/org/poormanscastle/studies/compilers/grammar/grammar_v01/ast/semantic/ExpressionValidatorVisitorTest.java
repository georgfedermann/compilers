package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.semantic;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.Program;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.parser.javacc.V01AstParser;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.SymbolTable;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by 02eex612 on 22.02.2016.
 */
public class ExpressionValidatorVisitorTest {

    @Rule
    public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();

    @Test
    public void testValidateProgram1() throws Exception {
        Program program = new V01AstParser(TestUtils.getTestdataAsInputStream("/grammar_v01/testprogram1.prog")).P();
        assertNotNull(program);
        SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
        if (program.handleProceedWith(symbolTableCreator)) {
            program.accept(symbolTableCreator);
        } else {
            throw new RuntimeException("SymbolTableCreatorVisitor was not accepted by AST !?");
        }

        SymbolTable symbolTable = symbolTableCreator.getSymbolTable();

        ExpressionValidatorVisitor expressionValidator = new ExpressionValidatorVisitor(symbolTable);
        if (program.handleProceedWith(expressionValidator)) {
            program.accept(expressionValidator);
        } else {
            throw new RuntimeException("ExpressionValidatorVisitor was not accepted by AST ?!");
        }
        assertTrue(expressionValidator.isAstValid());
    }

    @Test
    public void testUndeclaredIdentifier() throws Exception {
        Program program = new V01AstParser(TestUtils.getTestdataAsInputStream("/grammar_v01/UndeclaredId.prog")).P();
        assertNotNull(program);
        SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
        if (program.handleProceedWith(symbolTableCreator)) {
            program.accept(symbolTableCreator);
        } else {
            throw new RuntimeException("SymbolTableCreatorVisitor was not accepted by AST !?");
        }

        SymbolTable symbolTable = symbolTableCreator.getSymbolTable();

        ExpressionValidatorVisitor expressionValidator = new ExpressionValidatorVisitor(symbolTable);
        if (program.handleProceedWith(expressionValidator)) {
            program.accept(expressionValidator);
        } else {
            throw new RuntimeException("ExpressionValidatorVisitor was not accepted by AST ?!");
        }
        assertFalse(expressionValidator.isAstValid());

        String exptectedErrMsg =
                "Error at begin line/column 5/5; end line/column 5/5: variable b may not have been declared.\n" +
                        "Error at begin line/column 11/21; end line/column 11/21: variable b may not have been declared.\n" +
                        "Error at begin line/column 12/20; end line/column 12/20: variable b may not have been declared.\n" +
                        "Error at begin line/column 15/22; end line/column 15/22: the operand types BOOLEAN and INT are incompatible.\n" +
                        "Error at begin line/column 18/11; end line/column 18/11: variable b may not have been declared.\n" +
                        "Error at begin line/column 18/48; end line/column 18/48: variable b may not have been declared.\n" +
                        "Error at begin line/column 18/62; end line/column 18/62: variable b may not have been declared.\n";
        assertEquals(exptectedErrMsg, systemErrRule.getLog());
    }

    @Test
    public void testUndeclaredIdentifierInAssignmentStm() throws Exception {
        Program program = new V01AstParser(TestUtils.getTestdataAsInputStream("/grammar_v01/UndeclaredIdAssignmentStatement.prog")).P();
        assertNotNull(program);
        SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
        if (program.handleProceedWith(symbolTableCreator)) {
            program.accept(symbolTableCreator);
        } else {
            throw new RuntimeException("SymbolTableCreatorVisitor was not accepted by AST !?");
        }

        SymbolTable symbolTable = symbolTableCreator.getSymbolTable();

        ExpressionValidatorVisitor expressionValidator = new ExpressionValidatorVisitor(symbolTable);
        if (program.handleProceedWith(expressionValidator)) {
            program.accept(expressionValidator);
        } else {
            throw new RuntimeException("ExpressionValidatorVisitor was not accepted by AST ?!");
        }
        assertFalse(expressionValidator.isAstValid());
        assertEquals("Error at begin line/column 2/5; end line/column 2/5: variable b may not have been declared.\n", systemErrRule.getLog());
    }

    @Test
    public void testInvalidNotOperand() throws Exception {
        Program program = new V01AstParser(TestUtils.getTestdataAsInputStream("/grammar_v01/InvalidNotOperand.prog")).P();
        assertNotNull(program);
        SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
        if (program.handleProceedWith(symbolTableCreator)) {
            program.accept(symbolTableCreator);
        } else {
            throw new RuntimeException("SymbolTableCreatorVisitor was not accepted by AST !?");
        }

        SymbolTable symbolTable = symbolTableCreator.getSymbolTable();

        ExpressionValidatorVisitor expressionValidator = new ExpressionValidatorVisitor(symbolTable);
        if (program.handleProceedWith(expressionValidator)) {
            program.accept(expressionValidator);
        } else {
            throw new RuntimeException("ExpressionValidatorVisitor was not accepted by AST ?!");
        }
        assertFalse(expressionValidator.isAstValid());

        String expectedErrorMessage =
                "Error at begin line/column 3/14; end line/column 3/14: operator ! is incompatible with operand type DOUBLE\n" +
                        "Error at begin line/column 3/14; end line/column 3/14: Expression is invalid.\n" +
                        "Error at begin line/column 4/10; end line/column 4/13: the operand types INT and BOOLEAN are incompatible.\n";

        assertEquals(expectedErrorMessage, systemErrRule.getLog());
    }

    @Test
    public void testDeclarationWithoutAssignment() throws Exception {
        Program program = new V01AstParser(TestUtils.getTestdataAsInputStream("/grammar_v01/DeclarationWithoutAssignment.prog")).P();
        assertNotNull(program);
        SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
        if (program.handleProceedWith(symbolTableCreator)) {
            program.accept(symbolTableCreator);
        } else {
            throw new RuntimeException("SymbolTableCreatorVisitor was not accepted by AST ");
        }

        ExpressionValidatorVisitor expressionValidatorVisitor = new ExpressionValidatorVisitor(symbolTableCreator.getSymbolTable());
        if (program.handleProceedWith(expressionValidatorVisitor)) {
            program.accept(expressionValidatorVisitor);
        } else {
            throw new RuntimeException("ExpressionValidatorVisitor was not accepted by AST");
        }
        assertTrue(expressionValidatorVisitor.isAstValid());
    }

}