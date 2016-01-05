package org.poormanscastle.studies.compilers.ch01.part1;

import org.junit.Before;
import org.junit.Test;
import org.poormanscastle.studies.compilers.ch01.part1.ProgramScanner;
import org.poormanscastle.studies.compilers.programs.ProgramProvider;

import static org.junit.Assert.assertEquals;

/**
 * Created by georg on 11.12.15.
 */
public class ProgramScannerTest {

    ProgramScanner scanner;

    @Before
    public void init() throws Exception {
        scanner = new ProgramScanner();
    }

    @Test
    public void testMaxArgsProgramA() throws Exception {
        assertEquals(2, scanner.maxArgs(ProgramProvider.getProgramA()));
    }

    @Test
    public void testMaxArgsProgramB() throws Exception {
        assertEquals(2, scanner.maxArgs(ProgramProvider.getProgramB()));
    }

    @Test
    public void testMaxArgsProgramC() throws Exception {
        assertEquals(6, scanner.maxArgs(ProgramProvider.getProgramC()));
    }
}