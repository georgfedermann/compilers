package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.semantic;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.Program;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Symbol;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable.SymbolTable;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
        Program program = TestUtils.loadProgram("testprogram1.prog");
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
        Program program = TestUtils.loadProgram("BlockScopeTest1.oh");
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
        Program program = TestUtils.loadProgram("BlockScopeTest2.oh");
        program.accept(symbolTableCreator);
        assertFalse(symbolTableCreator.isAstValid());
        assertEquals("Error at begin line/column 7/14; end line/column 7/14: variable a was already declared in this scope.\n",systemErrRule.getLog());
    }
}