package org.poormanscastle.studies.compilers.programs.part1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.poormanscastle.studies.compilers.grammar.Stm;

/**
 * Created by georg on 03.12.15.
 */
public class PrintStmArgCounterVisitorTest {

    PrintStmArgCounterVisitor visitor;

    @Before
    public void before() throws Exception {
        visitor = new PrintStmArgCounterVisitor();
    }

    @Test
    public void testOnProgramA() throws Exception {
        Stm program = ProgramProvider.getProgramA();
        if (program.handleProceedWith(visitor)) {
            program.accept(visitor);
        }
        assertNull(visitor.getCurrentPrintContext());
        assertEquals(2, visitor.getPrintContexts().size());
        assertEquals(2, visitor.getPrintContexts().get(0).getNumberOfArguments());
        assertEquals(1, visitor.getPrintContexts().get(1).getNumberOfArguments());
    }

    @Test
    public void testOnProgramB() throws Exception {
        Stm program = ProgramProvider.getProgramB();
        if (program.handleProceedWith(visitor)) {
            program.accept(visitor);
        }
        assertNull(visitor.getCurrentPrintContext());
        assertEquals(2, visitor.getPrintContexts().size());
        assertEquals(1, visitor.getPrintContexts().get(0).getNumberOfArguments());
        assertEquals(2, visitor.getPrintContexts().get(1).getNumberOfArguments());
    }
}