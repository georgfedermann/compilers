package org.poormanscastle.studies.automata;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by georg on 28.03.16.
 */
public class Accept00Test {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    Accept00 probe = new Accept00();

    @Test
    public void testAccept() throws Exception {
        assertTrue(probe.accept("00"));
        assertTrue(probe.accept("100"));
        assertTrue(probe.accept("1100100"));
        assertTrue(probe.accept("00100100000000000"));
        assertFalse(probe.accept("000001"));
        assertFalse(probe.accept(""));
        assertFalse(probe.accept("111111111111"));
        assertFalse(probe.accept("010"));
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Illegal Input: 2");
        assertTrue(probe.accept("012"));
    }
}