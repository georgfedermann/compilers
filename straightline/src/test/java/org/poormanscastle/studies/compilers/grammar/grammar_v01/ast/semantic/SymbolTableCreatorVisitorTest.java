package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.semantic;

import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.Program;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.parser.javacc.OhAstParser;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Symbol;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable.SymbolTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by 02eex612 on 19.02.2016.
 */
public class SymbolTableCreatorVisitorTest {

    @Test
    public void testGetSymbolTable() throws Exception {
        Program program = new OhAstParser(TestUtils.getTestdataAsInputStream("/grammar_v01/testprogram1.prog")).P();
        assertNotNull(program);
        SymbolTableCreatorVisitor visitor = new SymbolTableCreatorVisitor();
        if (program.handleProceedWith(visitor)) {
            program.accept(visitor);
        }
        assertTrue(visitor.isAstValid());
        SymbolTable symbolTable = visitor.getSymbolTable();
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
    }
}