package org.poormanscastle.studies.compilers.utils.grammartools.lr;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.utils.grammartools.Grammar;
import org.poormanscastle.studies.compilers.utils.grammartools.GrammarFlavor;
import org.poormanscastle.studies.compilers.utils.grammartools.GrammarReader;

/**
 * Created by 02eex612 on 12.02.2016.
 */
public class LR0GrammarTest {

    @Test
    public void testCalculateStatesAndTransitions() throws Exception {
        Grammar grammar = Grammar.createGrammar(GrammarFlavor.LR0);
        new GrammarReader().readGrammar(TestUtils.getTestdataAsInputStream("/grammartools/grammar3.20.gr"), grammar);
        assertNotNull(grammar);
    }
}