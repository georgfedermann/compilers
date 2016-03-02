package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.semantic;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.Program;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable.SymbolTable;
import org.poormanscastle.studies.compilers.utils.grammartools.exceptions.CompilerException;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by 02eex612 on 22.02.2016.
 */
public class ExpressionValidatorVisitorTest {

    @Rule
    public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();

    @Test
    public void testValidateProgram1() throws Exception {
        Program program = TestUtils.loadProgram("testprogram1.prog");
        SymbolTable symbolTable = TestUtils.getSymbolTableForProgram(program);

        ExpressionValidatorVisitor expressionValidator = new ExpressionValidatorVisitor(symbolTable);
        if (program.handleProceedWith(expressionValidator)) {
            program.accept(expressionValidator);
        } else {
            throw new CompilerException("ExpressionValidatorVisitor was not accepted by AST ?!");
        }
        assertTrue(expressionValidator.isAstValid());
    }

    @Test
    public void testUndeclaredIdentifier() throws Exception {
        Program program = TestUtils.loadProgram("UndeclaredId.prog");
        SymbolTable symbolTable = TestUtils.getSymbolTableForProgram(program);

        ExpressionValidatorVisitor expressionValidator = new ExpressionValidatorVisitor(symbolTable);
        if (program.handleProceedWith(expressionValidator)) {
            program.accept(expressionValidator);
        } else {
            throw new CompilerException("ExpressionValidatorVisitor was not accepted by AST ?!");
        }
        assertFalse(expressionValidator.isAstValid());

        String exptectedErrMsg =
                "Error at begin line/column 5/5; end line/column 5/5: variable b may not have been declared.\n" +
                        "Error at begin line/column 11/21; end line/column 11/21: variable b may not have been declared.\n" +
                        "Error at begin line/column 12/20; end line/column 12/20: variable b may not have been declared.\n" +
                        "Error at begin line/column 18/11; end line/column 18/11: variable b may not have been declared.\n" +
                        "Error at begin line/column 18/48; end line/column 18/48: variable b may not have been declared.\n" +
                        "Error at begin line/column 18/62; end line/column 18/62: variable b may not have been declared.\n";
        assertEquals(exptectedErrMsg, systemErrRule.getLog());
    }

    @Test
    public void testUndeclaredIdentifierInAssignmentStm() throws Exception {
        Program program = TestUtils.loadProgram("UndeclaredIdAssignmentStatement.prog");
        SymbolTable symbolTable = TestUtils.getSymbolTableForProgram(program);

        ExpressionValidatorVisitor expressionValidator = new ExpressionValidatorVisitor(symbolTable);
        if (program.handleProceedWith(expressionValidator)) {
            program.accept(expressionValidator);
        } else {
            throw new CompilerException("ExpressionValidatorVisitor was not accepted by AST ?!");
        }
        assertFalse(expressionValidator.isAstValid());
        assertEquals("Error at begin line/column 2/5; end line/column 2/5: variable b may not have been declared.\n", systemErrRule.getLog());
    }

    @Test
    public void testInvalidNotOperand() throws Exception {
        Program program = TestUtils.loadProgram("InvalidNotOperand.prog");
        SymbolTable symbolTable = TestUtils.getSymbolTableForProgram(program);

        ExpressionValidatorVisitor expressionValidator = new ExpressionValidatorVisitor(symbolTable);
        if (program.handleProceedWith(expressionValidator)) {
            program.accept(expressionValidator);
        } else {
            throw new CompilerException("ExpressionValidatorVisitor was not accepted by AST ?!");
        }
        assertFalse(expressionValidator.isAstValid());

        String expectedErrorMessage =
                "Error at begin line/column 3/14; end line/column 3/14: operator ! is incompatible with operand type DOUBLE\n" +
                        "Error at begin line/column 4/10; end line/column 4/13: the type BOOLEAN cannot be assigned to INT.\n";

        assertEquals(expectedErrorMessage, systemErrRule.getLog());
    }

    @Test
    public void testDeclarationWithoutAssignment() throws Exception {
        Program program = TestUtils.loadProgram("DeclarationWithoutAssignment.prog");
        SymbolTable symbolTable = TestUtils.getSymbolTableForProgram(program);

        ExpressionValidatorVisitor expressionValidatorVisitor = new ExpressionValidatorVisitor(symbolTable);
        if (program.handleProceedWith(expressionValidatorVisitor)) {
            program.accept(expressionValidatorVisitor);
        } else {
            throw new CompilerException("ExpressionValidatorVisitor was not accepted by AST");
        }
        assertTrue(expressionValidatorVisitor.isAstValid());
    }

    @Test
    public void testAssignDoubleValueToIntVariable() throws Exception {
        Program program = TestUtils.loadProgram("assignDoubleValueToIntVariableBug.prog");
        SymbolTable symbolTable = TestUtils.getSymbolTableForProgram(program);

        ExpressionValidatorVisitor expressionValidator = new ExpressionValidatorVisitor(symbolTable);
        if (program.handleProceedWith(expressionValidator)) {
            program.accept(expressionValidator);
        } else {
            throw new CompilerException("ExpressionValidatorVisitor was not accepted by AST ?!");
        }
        assertFalse(expressionValidator.isAstValid());

        String expectedErrorMessage =
                "Error at begin line/column 6/9; end line/column 6/9: the type DOUBLE cannot be assigned to INT.\n";

        assertEquals(expectedErrorMessage, systemErrRule.getLog());
    }


    @Test
    public void testAssignIntToTextVariable() throws Exception {
        Program program = TestUtils.loadProgram("assignIntToText.prog");
        SymbolTable symbolTable = TestUtils.getSymbolTableForProgram(program);

        ExpressionValidatorVisitor expressionValidator = new ExpressionValidatorVisitor(symbolTable);
        if (program.handleProceedWith(expressionValidator)) {
            program.accept(expressionValidator);
        } else {
            throw new RuntimeException("ExpressionValidatorVisitor was not accepted by AST ?!");
        }
        assertFalse(expressionValidator.isAstValid());

        String expectedErrorMessage =
                "Error at begin line/column 3/13; end line/column 3/13: the type INT cannot be assigned to TEXT.\n";

        assertEquals(expectedErrorMessage, systemErrRule.getLog());
    }

    @Test
    public void testIntToText_v01() throws Exception {
        Program program = TestUtils.loadProgram("assignIntToText.prog");
        SymbolTable symbolTable = TestUtils.getSymbolTableForProgram(program);

        ExpressionValidatorVisitor expressionValidator = new ExpressionValidatorVisitor(symbolTable);
        if (program.handleProceedWith(expressionValidator)) {
            program.accept(expressionValidator);
        } else {
            throw new CompilerException("ExpressionValidatorVisitor was not accepted by AST");
        }
        Assert.assertFalse(expressionValidator.isAstValid());
    }

    @Test
    public void testBlockScope1() throws Exception {
        Program program = TestUtils.loadProgram("BlockScopeTest1.oh");
        SymbolTable symbolTable = TestUtils.getSymbolTableForProgram(program);

        ExpressionValidatorVisitor expressionValidator = new ExpressionValidatorVisitor(symbolTable);
        if (program.handleProceedWith(expressionValidator)) {
            program.accept(expressionValidator);
        } else {
            throw new CompilerException("ExpressionValidatorVisitor was not accepted by AST");
        }
        // Assert.assertTrue(expressionValidator.isAstValid());
    }

}