package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.semantic;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Program;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Symbol;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable.SymbolTable;

/**
 * Created by 02eex612 on 19.02.2016.
 */
public class SymbolTableCreatorVisitorTest {

    @Rule
    public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();

    SymbolTableCreatorVisitor symbolTableCreator;

    @Before
    public void init() {
        symbolTableCreator = new SymbolTableCreatorVisitor();
    }

    @Test
    public void testGetSymbolTable() throws Exception {
        Program program = TestUtils.loadProgram("testprogram1.prog", false);
        assertNotNull(program);
        SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
        if (program.handleProceedWith(symbolTableCreator)) {
            program.accept(symbolTableCreator);
        }
        assertTrue(symbolTableCreator.isAstValid());
        SymbolTable symbolTable = symbolTableCreator.getSymbolTable();
        assertNotNull(symbolTable);
        assertEquals(13, symbolTable.getSize());
        assertEquals("INT", symbolTable.getBinding(Symbol.getSymbol("a")).getDeclaredType());

        assertEquals("INT", symbolTable.getBinding(Symbol.getSymbol("b")).getDeclaredType());
        assertEquals("INT", symbolTable.getBinding(Symbol.getSymbol("c")).getDeclaredType());
        assertEquals("DOUBLE", symbolTable.getBinding(Symbol.getSymbol("f")).getDeclaredType());
        assertEquals("DOUBLE", symbolTable.getBinding(Symbol.getSymbol("g")).getDeclaredType());
        assertEquals("BOOLEAN", symbolTable.getBinding(Symbol.getSymbol("yes")).getDeclaredType());
        assertEquals("BOOLEAN", symbolTable.getBinding(Symbol.getSymbol("no")).getDeclaredType());
        assertEquals("BOOLEAN", symbolTable.getBinding(Symbol.getSymbol("maybe")).getDeclaredType());
        assertEquals("BOOLEAN", symbolTable.getBinding(Symbol.getSymbol("maybeNot")).getDeclaredType());
        assertEquals("TEXT", symbolTable.getBinding(Symbol.getSymbol("nameHero")).getDeclaredType());
        assertEquals("TEXT", symbolTable.getBinding(Symbol.getSymbol("nameEvil")).getDeclaredType());
        assertEquals("BOOLEAN", symbolTable.getBinding(Symbol.getSymbol("anotherTry")).getDeclaredType());
        assertEquals("BOOLEAN", symbolTable.getBinding(Symbol.getSymbol("k")).getDeclaredType());
        assertTrue(symbolTableCreator.isAstValid());
    }

    @Test
    public void testBlockScope1() throws Exception {
        Program program = TestUtils.loadProgram("BlockScopeTest1.oh", false);
        SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
        if (program.handleProceedWith(symbolTableCreator)) {
            program.accept(symbolTableCreator);
        }

        assertTrue(symbolTableCreator.isAstValid());

        SymbolTable symbolTable = symbolTableCreator.getSymbolTable();
        assertEquals(1, symbolTable.getSize());
        assertEquals("TEXT", symbolTable.getBinding(Symbol.getSymbol("a")).getDeclaredType());
    }

    @Test
    public void testBlockScope2() throws Exception {
        Program program = TestUtils.loadProgram("BlockScopeTest2.oh", false);
        program.accept(symbolTableCreator);
        assertFalse(symbolTableCreator.isAstValid());
        assertEquals("Error at begin line/column 7/14; end line/column 7/14: variable a was already declared in this scope.\n", systemErrRule.getLog());
    }


    @Test
    public void testUndeclaredIdentifier() throws Exception {
        Program program = TestUtils.loadProgram("UndeclaredId.prog", false);
        program.accept(symbolTableCreator);
        assertFalse(symbolTableCreator.isAstValid());

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
        Program program = TestUtils.loadProgram("UndeclaredIdAssignmentStatement.prog", false);
        program.accept(symbolTableCreator);

        assertFalse(symbolTableCreator.isAstValid());
        assertEquals("Error at begin line/column 2/5; end line/column 2/5: variable b may not have been declared.\n", systemErrRule.getLog());
    }

    @Test
    public void testInvalidNotOperand() throws Exception {
        Program program = TestUtils.loadProgram("InvalidNotOperand.prog", false);
        program.accept(symbolTableCreator);

        assertFalse(symbolTableCreator.isAstValid());
        String expectedErrorMessage =
                "Error at begin line/column 3/14; end line/column 3/14: operator ! is incompatible with operand type DOUBLE.\n" +
                        "Error at begin line/column 4/10; end line/column 4/13: the type BOOLEAN cannot be assigned to INT.\n";
        assertEquals(expectedErrorMessage, systemErrRule.getLog());
    }

    @Test
    public void testDeclarationWithoutAssignment() throws Exception {
        Program program = TestUtils.loadProgram("DeclarationWithoutAssignment.prog", false);
        program.accept(symbolTableCreator);

        assertTrue(symbolTableCreator.isAstValid());
    }

    @Test
    public void testAssignDoubleValueToIntVariable() throws Exception {
        Program program = TestUtils.loadProgram("assignDoubleValueToIntVariableBug.prog", false);
        program.accept(symbolTableCreator);

        assertFalse(symbolTableCreator.isAstValid());
        String expectedErrorMessage =
                "Error at begin line/column 6/9; end line/column 6/9: the type DOUBLE cannot be assigned to INT.\n";
        assertEquals(expectedErrorMessage, systemErrRule.getLog());
    }

    @Test
    public void testAssignIntToTextVariable() throws Exception {
        Program program = TestUtils.loadProgram("AssignIntToText.prog", false);
        program.accept(symbolTableCreator);
        assertFalse(symbolTableCreator.isAstValid());
        String expectedErrorMessage =
                "Error at begin line/column 3/13; end line/column 3/13: the type INT cannot be assigned to TEXT.\n" +
                        "Error at begin line/column 5/8; end line/column 5/8: the type INT cannot be assigned to TEXT.\n";
        assertEquals(expectedErrorMessage, systemErrRule.getLog());
    }

}