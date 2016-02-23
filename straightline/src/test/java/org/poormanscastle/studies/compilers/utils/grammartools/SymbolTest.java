package org.poormanscastle.studies.compilers.utils.grammartools;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by 02eex612 on 15.02.2016.
 */
public class SymbolTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testCreateTerminalSymbol() throws Exception {
        Symbol symbol = Symbol.createTerminalSymbol("id");
        assertNotNull(symbol);
        assertEquals("id", symbol.getId());
        assertTrue(symbol.isTerminal());
        assertFalse(symbol.isNullable());
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Terminal symbols cannot be nullable.");
        symbol.setNullable(true);
        assertFalse(symbol.isNullable());
    }

    @Test
    public void testCreateEof() throws Exception {
        Symbol symbol = Symbol.createTerminalSymbol("$");
        assertNotNull(symbol);
        assertEquals(Symbol.EOF, symbol);
        assertTrue(Symbol.EOF == symbol);
        assertTrue(symbol.isTerminal());
        assertFalse(symbol.isNullable());
    }

    @Test
    public void testCreateNonterminalSymbol() throws Exception {
        Symbol symbol = Symbol.createNonterminalSymbol("P'");
        assertNotNull(symbol);
        assertEquals("P'", symbol.getId());
        assertFalse(symbol.isTerminal());
        assertFalse(symbol.isNullable());
        symbol.setNullable(true);
        assertTrue(symbol.isNullable());
    }
}