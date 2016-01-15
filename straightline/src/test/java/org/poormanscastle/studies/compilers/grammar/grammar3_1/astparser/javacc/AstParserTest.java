package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.javacc;

import static junit.framework.TestCase.assertNotNull;

import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.Statement;

/**
 * Created by georg on 13.01.16.
 */
public class AstParserTest {

    @Test
    public void astParserTest() throws Exception {
        Statement statement = new AstParser(TestUtils.getTestdataAsInputStream("/grammar3_1/testprogram1.txt")).P();
        assertNotNull(statement);
    }

}
