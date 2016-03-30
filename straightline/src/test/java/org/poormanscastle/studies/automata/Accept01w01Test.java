package org.poormanscastle.studies.automata;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by 02eex612 on 30.03.2016.
 */
public class Accept01w01Test {

    @Test
    public void testAccept() throws Exception {
        Accept01w01 automaton = new Accept01w01();
        assertTrue(automaton.accept("0111111000"));
        assertTrue(automaton.startsWith01());
        assertFalse(automaton.endsWith01());
        assertFalse(automaton.startsAndEndsWith01());

        assertTrue(automaton.accept("01"));
        assertTrue(automaton.startsWith01());
        assertTrue(automaton.endsWith01());
        assertTrue(automaton.startsAndEndsWith01());

        assertTrue(automaton.accept("0101"));
        assertTrue(automaton.startsWith01());
        assertTrue(automaton.endsWith01());
        assertTrue(automaton.startsAndEndsWith01());

        assertTrue(automaton.accept("00110101"));
        assertFalse(automaton.startsWith01());
        assertTrue(automaton.endsWith01());
        assertFalse(automaton.startsAndEndsWith01());

        assertFalse(automaton.accept("00101011"));
        assertFalse(automaton.startsWith01());
        assertFalse(automaton.endsWith01());
        assertFalse(automaton.startsAndEndsWith01());

        assertFalse(automaton.accept("00101010"));
        assertFalse(automaton.startsWith01());
        assertFalse(automaton.endsWith01());
        assertFalse(automaton.startsAndEndsWith01());

        assertFalse(automaton.accept(""));
        assertFalse(automaton.startsWith01());
        assertFalse(automaton.endsWith01());
        assertFalse(automaton.startsAndEndsWith01());

        assertFalse(automaton.accept("0"));
        assertFalse(automaton.startsWith01());
        assertFalse(automaton.endsWith01());
        assertFalse(automaton.startsAndEndsWith01());

        assertFalse(automaton.accept("1"));
        assertFalse(automaton.startsWith01());
        assertFalse(automaton.endsWith01());
        assertFalse(automaton.startsAndEndsWith01());

        assertFalse(automaton.accept("01201"));
        assertFalse(automaton.startsWith01());
        assertFalse(automaton.endsWith01());
        assertFalse(automaton.startsAndEndsWith01());

        assertTrue(automaton.accept("01011"));
        assertTrue(automaton.startsWith01());
        assertFalse(automaton.endsWith01());
        assertFalse(automaton.startsAndEndsWith01());

        assertTrue(automaton.accept("01010"));
        assertTrue(automaton.startsWith01());
        assertFalse(automaton.endsWith01());
        assertFalse(automaton.startsAndEndsWith01());

        assertFalse(automaton.accept("1100"));
        assertFalse(automaton.startsWith01());
        assertFalse(automaton.endsWith01());
        assertFalse(automaton.startsAndEndsWith01());

    }
}