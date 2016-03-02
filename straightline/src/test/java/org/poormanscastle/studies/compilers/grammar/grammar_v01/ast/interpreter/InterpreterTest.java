package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.interpreter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.Program;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.semantic.SymbolTableCreatorVisitor;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable.SymbolTable;
import org.poormanscastle.studies.compilers.utils.grammartools.exceptions.CompilerException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.semantic.ExpressionValidatorVisitor;

/**
 * Created by 02eex612 on 23.02.2016.
 */
public class InterpreterTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void testProgram1_v01() throws Exception {
        Program program = TestUtils.loadProgram("testprogram1.prog");
        SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
        program.accept(symbolTableCreator);
        assertTrue(symbolTableCreator.isAstValid());
        SymbolTable symbolTable = symbolTableCreator.getSymbolTable();

        Interpreter interpreter = new Interpreter(symbolTable);
        if (program.handleProceedWith(interpreter)) {
            program.accept(interpreter);
        } else {
            throw new CompilerException("Interpreter was not accepted by AST");
        }
        assertEquals("0 9 3 true false true false false 0 9 false John Connor Walter White ", systemOutRule.getLog());
    }

    @Test
    public void testMixedTypes_v01() throws Exception {
        Program program = TestUtils.loadProgram("mixedTypes.prog");
        SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
        program.accept(symbolTableCreator);
        assertTrue(symbolTableCreator.isAstValid());
        SymbolTable symbolTable = symbolTableCreator.getSymbolTable();

        Interpreter interpreter = new Interpreter(symbolTable);
        if (program.handleProceedWith(interpreter)) {
            program.accept(interpreter);
        } else {
            throw new CompilerException("Interpreter was not accepted by AST");
        }
        assertEquals("Der Umfang ist  94.2 ", systemOutRule.getLog());
    }

    @Test
    public void testHelloWorld_v01() throws Exception {
        Program program = TestUtils.loadProgram("HelloWorld_v0.1.prog");
        SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
        program.accept(symbolTableCreator);
        assertTrue(symbolTableCreator.isAstValid());
        SymbolTable symbolTable = symbolTableCreator.getSymbolTable();

        Interpreter interpreter = new Interpreter(symbolTable);
        if (program.handleProceedWith(interpreter)) {
            program.accept(interpreter);
        } else {
            throw new CompilerException("Interpreter was not accepted by AST");
        }
        assertEquals("Hello, World! Hello, World! Hello, World! ", systemOutRule.getLog());
    }

    @Test
    public void testEmptyProgram() throws Exception {
        Program program = TestUtils.loadProgram("ProgramWithoutStatements.prog");
        SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
        program.accept(symbolTableCreator);
        assertTrue(symbolTableCreator.isAstValid());
        SymbolTable symbolTable = symbolTableCreator.getSymbolTable();

        Interpreter interpreter = new Interpreter(symbolTable);
        if (program.handleProceedWith(interpreter)) {
            program.accept(interpreter);
        } else {
            throw new CompilerException("Interpreter was not accepted by AST");
        }
    }

    @Test
    public void testBlockScopeTest1() throws Exception {
        Program program = TestUtils.loadProgram("BlockScopeTest1.oh");
        SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
        program.accept(symbolTableCreator);
        assertTrue(symbolTableCreator.isAstValid());
        SymbolTable symbolTable = symbolTableCreator.getSymbolTable();

        Interpreter interpreter = new Interpreter(symbolTable);
        program.accept(interpreter);
        assertEquals("Hello, World! Hello, World! 5 Hello, World! ", systemOutRule.getLog());
    }
}
