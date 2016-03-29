package org.poormanscastle.studies.automata;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by georg on 26.03.16.
 */
public class Accept01Test {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    Accept01 probe = new Accept01();

    @Test
    public void testAccept() throws Exception {
        assertTrue(probe.accept("111111111110111"));
        assertFalse(probe.accept("111111000000"));
        assertFalse(probe.accept("0000"));
        assertFalse(probe.accept("11111"));
        assertFalse(probe.accept(""));
        assertTrue(probe.accept("01"));
        assertTrue(probe.accept("010101"));
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Illegal Input: 3");
        assertTrue(probe.accept("00113"));
    }
}