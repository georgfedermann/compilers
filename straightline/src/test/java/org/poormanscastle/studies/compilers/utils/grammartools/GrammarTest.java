package org.poormanscastle.studies.compilers.utils.grammartools;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.poormanscastle.studies.compilers.utils.grammartools.ll1.LL1Grammar;
import org.poormanscastle.studies.compilers.utils.grammartools.lr.LR0Grammar;

/**
 * Created by georg on 13.02.16.
 */
public class GrammarTest {

    @Test
    public void testCreateGrammarLL1() throws Exception {
        Grammar grammar = Grammar.createGrammar(GrammarFlavor.LL1);
        assertEquals(LL1Grammar.class, grammar.getClass());
    }

    @Test
    public void testCreateGrammarLR0() throws Exception {
        Grammar grammar = Grammar.createGrammar(GrammarFlavor.LR0);
        assertEquals(LR0Grammar.class, grammar.getClass());
    }
}