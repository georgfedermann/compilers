package org.poormanscastle.studies.automata;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by georg on 26.03.16.
 */
public class Accept01Test {

    Accept01 probe = new Accept01();

    @Test
    public void testAccept() throws Exception {
        assertTrue(probe.accept("111111111110111"));
        assertFalse(probe.accept("111111000000"));
        assertFalse(probe.accept("0000"));
        assertFalse(probe.accept("11111"));
        assertFalse(probe.accept(""));
        assertTrue(probe.accept("01"));
    }
}