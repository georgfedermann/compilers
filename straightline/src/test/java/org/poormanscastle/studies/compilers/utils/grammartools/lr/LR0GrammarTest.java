package org.poormanscastle.studies.compilers.utils.grammartools.lr;

import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.utils.grammartools.Grammar;
import org.poormanscastle.studies.compilers.utils.grammartools.GrammarFlavor;
import org.poormanscastle.studies.compilers.utils.grammartools.GrammarReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by 02eex612 on 12.02.2016.
 */
public class LR0GrammarTest {

    @Test
    public void testCalculateStatesAndTransitions() throws Exception {
        LR0Grammar grammar = (LR0Grammar) Grammar.createGrammar(GrammarFlavor.LR0);
        new GrammarReader().readGrammar(TestUtils.getTestdataAsInputStream("/grammartools/grammar3.20.gr"), grammar);
        assertNotNull(grammar);
        assertEquals(9, grammar.getStates().size());
        assertEquals(12, grammar.getEdges().size());
        assertEquals(5, grammar.getProductions().size());
        assertEquals(8, grammar.getSymbols().size());
        assertEquals(5, grammar.getTerminalSymbols().size());
    }

    @Test
    public void testCalculateStatesAndTransitionsGrammar01() throws Exception {
        Grammar grammar = Grammar.createGrammar(GrammarFlavor.LR0);
        new GrammarReader().readGrammar(TestUtils.getTestdataAsInputStream("/grammartools/grammar01.gr"), grammar);
        assertNotNull(grammar);
    }
}