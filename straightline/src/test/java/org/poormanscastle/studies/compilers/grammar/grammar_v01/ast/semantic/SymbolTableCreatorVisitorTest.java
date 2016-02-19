package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.semantic;

import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.Program;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.parser.javacc.V01AstParser;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Symbol;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.SymbolTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by 02eex612 on 19.02.2016.
 */
public class SymbolTableCreatorVisitorTest {

    @Test
    public void testGetSymbolTable() throws Exception {
        Program program = new V01AstParser(TestUtils.getTestdataAsInputStream("/grammar_v01/testprogram1.txt")).P();
        assertNotNull(program);
        SymbolTableCreatorVisitor visitor = new SymbolTableCreatorVisitor();
        if (program.handleProceedWith(visitor)) {
            program.accept(visitor);
        }
        SymbolTable symbolTable = visitor.getSymbolTable();
        assertNotNull(symbolTable);
        assertEquals(13, symbolTable.getSize());
        assertEquals("INT", symbolTable.getBinding(Symbol.createSymbol("a")).getDeclaredType());

        assertEquals("INT", symbolTable.getBinding(Symbol.createSymbol("b")).getDeclaredType());
        assertEquals("INT", symbolTable.getBinding(Symbol.createSymbol("c")).getDeclaredType());
        assertEquals("DOUBLE", symbolTable.getBinding(Symbol.createSymbol("f")).getDeclaredType());
        assertEquals("DOUBLE", symbolTable.getBinding(Symbol.createSymbol("g")).getDeclaredType());
        assertEquals("BOOLEAN", symbolTable.getBinding(Symbol.createSymbol("yes")).getDeclaredType());
        assertEquals("BOOLEAN", symbolTable.getBinding(Symbol.createSymbol("no")).getDeclaredType());
        assertEquals("BOOLEAN", symbolTable.getBinding(Symbol.createSymbol("maybe")).getDeclaredType());
        assertEquals("BOOLEAN", symbolTable.getBinding(Symbol.createSymbol("maybeNot")).getDeclaredType());
        assertEquals("TEXT", symbolTable.getBinding(Symbol.createSymbol("nameHero")).getDeclaredType());
        assertEquals("TEXT", symbolTable.getBinding(Symbol.createSymbol("nameEvil")).getDeclaredType());
        assertEquals("BOOLEAN", symbolTable.getBinding(Symbol.createSymbol("anotherTry")).getDeclaredType());
        assertEquals("BOOLEAN", symbolTable.getBinding(Symbol.createSymbol("k")).getDeclaredType());

    }
}