package org.poormanscastle.studies.compilers.grammar.grammar3_1.interpreter.javacc;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.poormanscastle.studies.compilers.TestUtils;

/**
 * Created by georg on 13.01.16.
 */
public class InterpreterTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void testProgram1() throws Exception {
        new Interpreter(TestUtils.getTestdataAsInputStream("/grammar3_1/testprogram1.txt")).Program();
        assertEquals("5521 18 1 2 6 3 13 3 42 ", systemOutRule.getLog());
    }

}
