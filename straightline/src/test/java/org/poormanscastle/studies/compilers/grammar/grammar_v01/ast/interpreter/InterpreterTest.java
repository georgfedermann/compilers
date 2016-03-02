package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.interpreter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.Program;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.parser.javacc.OhAstParser;
//import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.semantic.ExpressionValidatorVisitor;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.semantic.SymbolTableCreatorVisitor;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable.SymbolTable;
import org.poormanscastle.studies.compilers.utils.grammartools.exceptions.CompilerException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by 02eex612 on 23.02.2016.
 */
public class InterpreterTest {
/*
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void testProgram1_v01() throws Exception {
        Program program = new OhAstParser(TestUtils.getTestdataAsInputStream("/grammar_v01/testprogram1.prog")).P();
        assertNotNull(program);
        SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
        if (program.handleProceedWith(symbolTableCreator)) {
            program.accept(symbolTableCreator);
        } else {
            throw new CompilerException("SymbolTableCreatorVisitor was not accepted by AST");
        }
        SymbolTable symbolTable = symbolTableCreator.getSymbolTable();
        ExpressionValidatorVisitor expressionValidator = new ExpressionValidatorVisitor(symbolTable);
        if (program.handleProceedWith(expressionValidator)) {
            program.accept(expressionValidator);
        } else {
            throw new CompilerException("ExpressionValidatorVisitor was not accepted by AST");
        }
        assertTrue(expressionValidator.isAstValid());

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
        Program program = new OhAstParser(TestUtils.getTestdataAsInputStream("/grammar_v01/mixedTypes.prog")).P();
        assertNotNull(program);
        SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
        if (program.handleProceedWith(symbolTableCreator)) {
            program.accept(symbolTableCreator);
        } else {
            throw new CompilerException("SymbolTableCreatorVisitor was not accepted by AST");
        }
        SymbolTable symbolTable = symbolTableCreator.getSymbolTable();
        ExpressionValidatorVisitor expressionValidator = new ExpressionValidatorVisitor(symbolTable);
        if (program.handleProceedWith(expressionValidator)) {
            program.accept(expressionValidator);
        } else {
            throw new CompilerException("ExpressionValidatorVisitor was not accepted by AST");
        }
        assertTrue(expressionValidator.isAstValid());

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
        Program program = new OhAstParser(TestUtils.getTestdataAsInputStream("/grammar_v01/HelloWorld_v0.1.prog")).P();
        assertNotNull(program);
        SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
        if (program.handleProceedWith(symbolTableCreator)) {
            program.accept(symbolTableCreator);
        } else {
            throw new CompilerException("SymbolTableCreatorVisitor was not accepted by AST");
        }
        SymbolTable symbolTable = symbolTableCreator.getSymbolTable();
        ExpressionValidatorVisitor expressionValidator = new ExpressionValidatorVisitor(symbolTable);
        if (program.handleProceedWith(expressionValidator)) {
            program.accept(expressionValidator);
        } else {
            throw new CompilerException("ExpressionValidatorVisitor was not accepted by AST");
        }
        assertTrue(expressionValidator.isAstValid());

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
        Program program = new OhAstParser(TestUtils.getTestdataAsInputStream("/grammar_v01/ProgramWithoutStatements.prog")).P();
        assertNotNull(program);
        SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
        if (program.handleProceedWith(symbolTableCreator)) {
            program.accept(symbolTableCreator);
        } else {
            throw new CompilerException("SymbolTableCreatorVisitor was not accepted by AST");
        }
        SymbolTable symbolTable = symbolTableCreator.getSymbolTable();
        ExpressionValidatorVisitor expressionValidator = new ExpressionValidatorVisitor(symbolTable);
        if (program.handleProceedWith(expressionValidator)) {
            program.accept(expressionValidator);
        } else {
            throw new CompilerException("ExpressionValidatorVisitor was not accepted by AST");
        }
        assertTrue(expressionValidator.isAstValid());

        Interpreter interpreter = new Interpreter(symbolTable);
        if (program.handleProceedWith(interpreter)) {
            program.accept(interpreter);
        } else {
            throw new CompilerException("Interpreter was not accepted by AST");
        }
    }
    */
}
