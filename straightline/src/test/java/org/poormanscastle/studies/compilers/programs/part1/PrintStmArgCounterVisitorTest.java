package org.poormanscastle.studies.compilers.programs.part1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.poormanscastle.studies.compilers.grammar.Stm;

/**
 * Created by georg on 03.12.15.
 */
public class PrintStmArgCounterVisitorTest {

    @Test
    public void testOnProgramA() throws Exception {
        Stm program = ProgramProvider.getProgramA();
        PrintStmArgCounterVisitor visitor = new PrintStmArgCounterVisitor();
        program.accept(visitor);
        assertNull(visitor.getCurrentPrintContext());
        assertEquals(2, visitor.getPrintContexts().size());
        assertEquals(2, visitor.getPrintContexts().get(0).getNumberOfArguments());
        assertEquals(1, visitor.getPrintContexts().get(1).getNumberOfArguments());
    }

    @Test
    public void testOnProgramB() throws Exception {

    }
}