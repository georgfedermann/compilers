package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.interpreter;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.Program;

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
        SmallTimeInterpreter interpreter = new SmallTimeInterpreter();
        program.accept(interpreter);
        assertEquals("0 9 3 true false true false false 0 9 false John Connor Walter White", systemOutRule.getLog());
    }

    @Test
    public void testMixedTypes_v01() throws Exception {
        Program program = TestUtils.loadProgram("mixedTypes.prog");
        SmallTimeInterpreter interpreter = new SmallTimeInterpreter();
        program.accept(interpreter);
        assertEquals("Der Umfang ist 94.2", systemOutRule.getLog());
    }

    @Test
    public void testHelloWorld_v01() throws Exception {
        Program program = TestUtils.loadProgram("HelloWorld_v0.1.prog");
        SmallTimeInterpreter interpreter = new SmallTimeInterpreter();
        program.accept(interpreter);
        assertEquals("Hello, World! Hello, World! Hello, World!", systemOutRule.getLog());
    }

    @Test
    public void testEmptyProgram() throws Exception {
        Program program = TestUtils.loadProgram("ProgramWithoutStatements.prog");
        SmallTimeInterpreter interpreter = new SmallTimeInterpreter();
        // the following should just not throw any exception, thus indicating that
        // the interpreter system can handle programs empty of statements.
        program.accept(interpreter);
    }

    @Test
    public void testBlockScopeTest1() throws Exception {
        Program program = TestUtils.loadProgram("BlockScopeTest1.oh");
        SmallTimeInterpreter interpreter = new SmallTimeInterpreter();
        program.accept(interpreter);
        assertEquals("Hello, World! Hello, World! 5 Hello, World!", systemOutRule.getLog());
    }

}
