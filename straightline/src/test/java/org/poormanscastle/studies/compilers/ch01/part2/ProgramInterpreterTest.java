package org.poormanscastle.studies.compilers.ch01.part2;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.poormanscastle.studies.compilers.ch01.part2.ProgramInterpreter;
import org.poormanscastle.studies.compilers.programs.ProgramProvider;

import static org.junit.Assert.assertEquals;

/**
 * Created by georg on 11.12.15.
 */
public class ProgramInterpreterTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    private ProgramInterpreter interpreter;

    @Before
    public void setUp() throws Exception {
        interpreter = new ProgramInterpreter();
    }

    @Test
    public void testInterpProgramA() throws Exception {
        // expected output: 8 7 80
        interpreter.interp(ProgramProvider.getProgramA());
        assertEquals("8 7 80 ", systemOutRule.getLog());
    }

    @Test
    public void testInterpProgramB() throws Exception {
        // expected output: 0 3 5
        interpreter.interp(ProgramProvider.getProgramB());
        assertEquals("0 3 5 ", systemOutRule.getLog());
    }

    @Test
    public void testInterpProgramC() throws Exception {
        // expected output:
        // 1 2 3 4 5 6 68 76 68 68 11 5 57 67
        // 1 2 3 4 5 6 68 76 68 68 11 5 57 67
        interpreter.interp(ProgramProvider.getProgramC());
        assertEquals("1 2 3 4 5 6 68 76 68 68 11 5 57 67 ", systemOutRule.getLog());
    }
}