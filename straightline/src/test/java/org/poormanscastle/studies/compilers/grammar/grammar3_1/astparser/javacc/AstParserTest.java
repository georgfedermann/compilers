package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.javacc;

import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;

/**
 * Created by georg on 13.01.16.
 */
public class AstParserTest {

    @Test
    public void astParserTest() throws Exception {
        new AstParser(TestUtils.getTestdataAsInputStream("/grammar3_1/testprogram1.txt")).P();
    }

}
