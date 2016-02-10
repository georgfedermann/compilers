package org.poormanscastle.studies.compilers.utils.grammartools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;

/**
 * Created by georg on 09.02.16.
 */
public class TableCreatorTest {

    @Test
    public void testProceed() throws Exception {
        TableCreator creator = new TableCreator();
        Grammar grammar = creator.proceed(TestUtils.getTestdataAsInputStream("/grammartools/grammar01.gr"));
        assertNotNull(grammar);
        assertEquals(45, grammar.getProductions().size());
        assertEquals(23, grammar.getTerminalSymbols().size());
        assertEquals(46, grammar.getSymbols().size());

        int counter = 0;
        for (Production production : grammar.getProductions()) {
            assertEquals(counter++ == 0, production.isStartProduction());
        }
        assertEquals(1, 1);
    }
}