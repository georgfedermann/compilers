package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.interpreter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Program;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.semantic.SymbolTableCreatorVisitor;

import static junit.framework.TestCase.assertEquals;

/**
 * tests the Oh system's language features.
 * <p/>
 * Created by 02eex612 on 07.03.2016.
 */
public class OhCodeTester {

    SymbolTableCreatorVisitor symbolTableCreator;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Before
    public void init() {
        symbolTableCreator = new SymbolTableCreatorVisitor();
    }

    @Test
    public void testHelloWorld() throws Exception {
        Program program = TestUtils.loadOhProgram("HelloWorld");
        program.accept(symbolTableCreator);
        program.accept(new SmallTimeInterpreter(symbolTableCreator.getSymbolTable()));
        assertEquals("Hello, World!", systemOutRule.getLog());
    }

    @Test
    public void testCircumference() throws Exception {
        Program program = TestUtils.loadOhProgram("Circumference");
        program.accept(symbolTableCreator);
        program.accept(new SmallTimeInterpreter(symbolTableCreator.getSymbolTable()));
        assertEquals("Given the diameter 15 and the pi approximation 3.14, the circumference is 47.1.", systemOutRule.getLog());
    }

    @Test
    public void testIfConditionExpressions() throws Exception {
        Program program = TestUtils.loadOhProgram("TestIfConditionExpressions");
        program.accept(symbolTableCreator);
        program.accept(new SmallTimeInterpreter(symbolTableCreator.getSymbolTable()));
        assertEquals("okokokokokokokokokokokokokok", systemOutRule.getLog());
    }

    @Test
    public void testIfConditionElse() throws Exception {
        Program program = TestUtils.loadOhProgram("TestIfConditionElseTest");
        program.accept(symbolTableCreator);
        program.accept(new SmallTimeInterpreter(symbolTableCreator.getSymbolTable()));
        assertEquals("okokscope0scope4scope0", systemOutRule.getLog());
    }

    @Test
    public void testBinaryOperators() throws Exception {
        Program program = TestUtils.loadOhProgram("BinaryOperatorTest");
        program.accept(symbolTableCreator);
        program.accept(new SmallTimeInterpreter(symbolTableCreator.getSymbolTable()));
        assertEquals("16-11=5. 2+3=5. 15/3=5. 16/3=5. 3*2-1=5. 3<4=true. 3<=4=true. 3<=2=false. 3==3=true. 3==4=false. 3!=4=true. 3!=3=false. 3>3=false. 3>2=true. true=true. false=false. !true=false. !false=true. true==true=true. true==false=false. true!=true=false. true!=false=true. true&true=true. true&false=false. false&false=false. true|true=true. true|false=true. false|false=false. true^true=false. false^false=false. true^false=true. 6.6/2=3.3. 3.3*2=6.6. 3.4+1.1=4.5. 3.7-1.2=2.5. 3.3==3.3 true. 3.3==17.5 false. 3.5<7.6 true. 3.5<=3.5 true. 3.5<=17.6 true. 3.95<=1.7125 false. 3.5>2 true. 3.5>=3.5 true. 3.5>=1.2 true. 3.5>=17.0 false. 3.5!=3.5=false. 3.5!=35=true. Hello==Hello true. Hello!=Hello false. Hello!=World true. Hello,+World!= Hello, World!", systemOutRule.getLog());
    }

    @Test
    public void testSimpleWhile() throws Exception {
        Program program = TestUtils.loadOhProgram("SimpleWhile");
        program.accept(symbolTableCreator);
        program.accept(new SmallTimeInterpreter(symbolTableCreator.getSymbolTable()));
        assertEquals("0 1 2 3 4 5 6 7 8 9 finished", systemOutRule.getLog());
    }

    @Test
    public void testWhileBlocks() throws Exception {
        Program program = TestUtils.loadOhProgram("WhileBlocks");
        program.accept(symbolTableCreator);
        program.accept(new SmallTimeInterpreter(symbolTableCreator.getSymbolTable()));
        assertEquals("0 hi 1 hi 2 hi 3 hi 4 hi 5 hi 6 hi 7 hi 8 hi 9 hi a after loop 10 finished.", systemOutRule.getLog());
    }

    @Test
    public void testMultipleValidFunctionsAndCalls() throws Exception {
        Program program = TestUtils.loadOhProgram("MultipleValidFunctionsAndCalls");
        program.accept(symbolTableCreator);
        program.accept(new SmallTimeInterpreter(symbolTableCreator.getSymbolTable()));
        assertEquals("The sum is 58; the mean is 2.0.", systemOutRule.getLog());
    }

    @Test
    public void testFibonacciRecursive() throws Exception {
        Program program = TestUtils.loadOhProgram("FibonacciRecursive");
        program.accept(symbolTableCreator);
        program.accept(new SmallTimeInterpreter(symbolTableCreator.getSymbolTable()));
        assertEquals("This are the Fibonacci numbers from 0 through 10: 0->1; 1->1; 2->1; 3->2; 4->3; 5->5; 6->8; 7->13; 8->21; 9->34; 10->55; ", systemOutRule.getLog());
    }

}
