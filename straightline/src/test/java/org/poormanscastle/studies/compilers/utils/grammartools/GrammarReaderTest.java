package org.poormanscastle.studies.compilers.utils.grammartools;

import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by 02eex612 on 12.02.2016.
 */
public class GrammarReaderTest {

    @Test
    public void testReadGrammar() throws Exception {
        Grammar grammar = Grammar.createGrammar(GrammarFlavor.LL1);
        GrammarReader.readGrammar(TestUtils.getTestdataAsInputStream("/grammartools/grammar01.gr"), grammar);
        assertNotNull(grammar);
        assertEquals(45, grammar.getProductions().size());
        assertEquals(23, grammar.getTerminalSymbols().size());
        assertEquals(46, grammar.getSymbols().size());
        assertEquals(23, grammar.getLhsSymbols().size());

        int counter = 0;
        for (Production production : grammar.getProductions()) {
            assertEquals(counter++ == 0, production.isStartProduction());
        }
        assertEquals(1, 1);
    }
}