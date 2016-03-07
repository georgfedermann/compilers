package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.interpreter;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Program;

/**
 * Created by 02eex612 on 23.02.2016.
 */
public class InterpreterTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void testProgram1_v01() throws Exception {
        Program program = TestUtils.loadTestProgram("testprogram1.prog", true);
        SmallTimeInterpreter interpreter = new SmallTimeInterpreter();
        program.accept(interpreter);
        assertEquals("0 9 3 true false true false false 0 9 false John Connor Walter White", systemOutRule.getLog());
    }

    @Test
    public void testMixedTypes_v01() throws Exception {
        Program program = TestUtils.loadTestProgram("mixedTypes.prog", true);
        SmallTimeInterpreter interpreter = new SmallTimeInterpreter();
        program.accept(interpreter);
        assertEquals("Der Umfang ist 94.2", systemOutRule.getLog());
    }

    @Test
    public void testHelloWorld_v01() throws Exception {
        Program program = TestUtils.loadTestProgram("HelloWorld_v0.1.prog", true);
        SmallTimeInterpreter interpreter = new SmallTimeInterpreter();
        program.accept(interpreter);
        assertEquals("Hello, World! Hello, World! Hello, World!", systemOutRule.getLog());
    }

    @Test
    public void testEmptyProgram() throws Exception {
        Program program = TestUtils.loadTestProgram("ProgramWithoutStatements.prog", true);
        SmallTimeInterpreter interpreter = new SmallTimeInterpreter();
        // the following should just not throw any exception, thus indicating that
        // the interpreter system can handle programs empty of statements.
        program.accept(interpreter);
    }

    @Test
    public void testBlockScopeTest1() throws Exception {
        Program program = TestUtils.loadTestProgram("BlockScopeTest1.oh", true);
        SmallTimeInterpreter interpreter = new SmallTimeInterpreter();
        program.accept(interpreter);
        assertEquals("Hello, World! Hello, World! 5 Hello, World!", systemOutRule.getLog());
    }

    @Test
    public void testConditionalStatement() throws Exception {
        Program program = TestUtils.loadTestProgram("ConditionalStatementTest.oh", true);
        SmallTimeInterpreter interpreter = new SmallTimeInterpreter();
        program.accept(interpreter);
        assertEquals("executing if statement; Not Hello, World! true true true", systemOutRule.getLog());
    }

}
