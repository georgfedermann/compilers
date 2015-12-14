package org.poormanscastle.studies.compilers.programs.part1;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.poormanscastle.studies.compilers.grammar.IdExp;
import org.poormanscastle.studies.compilers.grammar.NumExp;
import org.poormanscastle.studies.compilers.grammar.Stm;
import org.poormanscastle.studies.compilers.programs.ProgramProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by georg on 03.12.15.
 */
public class PrintStmArgCounterVisitorTest {

    PrintStmArgCounterVisitor visitor;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void before() throws Exception {
        visitor = new PrintStmArgCounterVisitor();
    }

    @Test
    public void testVisitIdExp() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("This method must never be executed!");
        visitor.visitIdExp(new IdExp("a"));
    }

    @Test
    public void testLeadIdExp() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("This method must never be executed!");
        visitor.leaveIdExp(new IdExp("a"));
    }

    @Test
    public void testVisitNumExp() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("This method must never be executed!");
        visitor.visitNumExp(new NumExp(3));
    }

    @Test
    public void testLeaveNumExp() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("This method must never be executed!");
        visitor.leaveNumExp(new NumExp(3));
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

    @Test
    public void testOnProgramC() throws Exception {
        Stm program = ProgramProvider.getProgramC();
        if (program.handleProceedWith(visitor)) {
            program.accept(visitor);
        }
        assertNull(visitor.getCurrentPrintContext());
        assertEquals(3, visitor.getPrintContexts().size());
        assertEquals(6, visitor.getPrintContexts().get(0).getNumberOfArguments());
        assertEquals(3, visitor.getPrintContexts().get(1).getNumberOfArguments());
        assertEquals(5, visitor.getPrintContexts().get(2).getNumberOfArguments());
    }
}