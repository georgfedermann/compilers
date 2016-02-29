package org.poormanscastle.studies.compilers.utils.grammartools.ll1;

import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.utils.grammartools.Grammar;
import org.poormanscastle.studies.compilers.utils.grammartools.GrammarFlavor;
import org.poormanscastle.studies.compilers.utils.grammartools.GrammarReader;
import org.poormanscastle.studies.compilers.utils.grammartools.Production;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by georg on 13.02.16.
 */
public class LL1GrammarTest {

    @Test
    public void testGrammar_0_2() throws Exception {
        Grammar grammar = Grammar.createGrammar(GrammarFlavor.LL1);
        GrammarReader.readGrammar(TestUtils.getTestdataAsInputStream("/grammartools/grammar01.gr"), grammar);
        assertNotNull(grammar);
        assertEquals(45, grammar.getProductions().size());
        assertEquals(23, grammar.getTerminalSymbols().size());
        assertEquals(46, grammar.getSymbols().size());

        int counter = 0;
        for (Production production : grammar.getProductions()) {
            assertEquals(counter++ == 0, production.isStartProduction());
        }
        // TODO add checks for first sets, follow sets, nullability, etc.
        assertEquals(1, 1);
    }

    @Test
    public void testGrammar_3_10() throws Exception {
        Grammar grammar = Grammar.createGrammar(GrammarFlavor.LL1);
        GrammarReader.readGrammar(TestUtils.getTestdataAsInputStream("/grammartools/grammar3.10.gr"), grammar);
        assertNotNull(grammar);

        int counter = 0;
        for (Production production : grammar.getProductions()) {
            assertEquals(counter++ == 0, production.isStartProduction());
        }
        assertEquals(1, 1);
    }

}