package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.interpreter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Program;

import static junit.framework.TestCase.assertEquals;

/**
 * tests the Oh system's language features.
 * <p/>
 * Created by 02eex612 on 07.03.2016.
 */
public class OhCodeTester {

    SmallTimeInterpreter interpreter;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Before
    public void init() {
        interpreter = new SmallTimeInterpreter();
    }

    @Test
    public void testHelloWorld() throws Exception {
        Program program = TestUtils.loadOhProgram("HelloWorld");
        program.accept(interpreter);
        assertEquals("Hello, World!", systemOutRule.getLog());
    }

    @Test
    public void testCircumference() throws Exception {
        Program program = TestUtils.loadOhProgram("Circumference");
        program.accept(interpreter);
        assertEquals("Given the diameter 15 and the pi approximation 3.14, the circumference is 47.1.", systemOutRule.getLog());
    }

    @Test
    public void testIfConditionExpressions() throws Exception {
        Program program = TestUtils.loadOhProgram("TestifConditionExpressions");
        program.accept(interpreter);
        assertEquals("okokokokokokokokokokokokokok", systemOutRule.getLog());
    }

    @Test
    public void testIfConditionElse() throws Exception {
        Program program = TestUtils.loadOhProgram("TestIfConditionElseTest");
        program.accept(interpreter);
        assertEquals("okokscope0scope4scope0", systemOutRule.getLog());
    }

}
