package org.poormanscastle.studies.compilers.utils.grammartools;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;

/**
 * Created by georg on 09.02.16.
 */
public class LL1TableCreatorTest {

    @Test
    public void testCreateTable() throws Exception {
        Grammar grammar = Grammar.createGrammar(GrammarFlavor.LL1);
        new GrammarReader().readGrammar(TestUtils.getTestdataAsInputStream("/grammartools/grammar01.gr"), grammar);
        TableCreator creator = TableCreator.getTableCreator(GrammarFlavor.LL1);
        String table = creator.createTable(grammar);
        assertNotNull(table);
        assertEquals(8215, table.length());
        assertFalse(StringUtils.isBlank(table));

        FileOutputStream outputStream = new FileOutputStream(new File("grammartools/predictiveParserTableGrammar01.html"));
        outputStream.write(table.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
}