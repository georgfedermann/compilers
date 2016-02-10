package org.poormanscastle.studies.compilers.utils.grammartools;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

    @Test
    public void testCreateTable() throws Exception {
        TableCreator creator = new TableCreator();
        Grammar grammar = creator.proceed(TestUtils.getTestdataAsInputStream("/grammartools/grammar01.gr"));
        String table = creator.createTable(grammar);
        assertNotNull(table);
        assertFalse(StringUtils.isBlank(table));

        FileOutputStream outputStream = new FileOutputStream(new File("grammartools/predictiveParserTableGrammar01.html"));
        outputStream.write(table.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
}