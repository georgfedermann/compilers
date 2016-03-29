package org.poormanscastle.studies.automata;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by 02eex612 on 29.03.2016.
 */
public class AcceptBalanced0s1sTest {

    AcceptBalanced0s1s probe = new AcceptBalanced0s1s();

    @Test
    public void testAccept() throws Exception {
        assertTrue(probe.accept("0101010101010101"));
        assertFalse(probe.accept("01010101010101011"));
        assertFalse(probe.accept("01021010101010101"));
        assertTrue(probe.accept("0110"));
    }
}