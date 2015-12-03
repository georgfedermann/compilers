package org.poormanscastle.studies.compilers.programs.part1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.poormanscastle.studies.compilers.grammar.Stm;

/**
 * Created by georg on 03.12.15.
 */
public class PrintStmArgCounterVisitorTest {

    @Test
    public void testGetCurrentPrintContext() throws Exception {
        Stm program = ProgramProvider.getProgram();
        PrintStmArgCounterVisitor visitor = new PrintStmArgCounterVisitor();
        program.accept(visitor);
        assertNull(visitor.getCurrentPrintContext());
        assertEquals(2, visitor.getPrintContexts().size());
    }
}