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

    @Test
    public void testBinaryOperators() throws Exception {
        Program program = TestUtils.loadOhProgram("BinaryOperatorTest");
        program.accept(interpreter);
        assertEquals("16-11=5. 2+3=5. 15/3=5. 16/3=5. 3*2-1=5. 3<4=true. 3<=4=true. 3<=2=false. 3==3=true. 3==4=false. 3!=4=true. 3!=3=false. 3>3=false. 3>2=true. true=true. false=false. !true=false. !false=true. true==true=true. true==false=false. true!=true=false. true!=false=true. true&true=true. true&false=false. false&false=false. true|true=true. true|false=true. false|false=false. true^true=false. false^false=false. true^false=true. 6.6/2=3.3. 3.3*2=6.6. 3.4+1.1=4.5. 3.7-1.2=2.5. 3.3==3.3 true. 3.3==17.5 false. 3.5<7.6 true. 3.5<=3.5 true. 3.5<=17.6 true. 3.95<=1.7125 false. 3.5>2 true. 3.5>=3.5 true. 3.5>=1.2 true. 3.5>=17.0 false. 3.5!=3.5=false. 3.5!=35=true. Hello==Hello true. Hello!=Hello false. Hello!=World true. Hello,+World!= Hello, World!", systemOutRule.getLog());
    }

}