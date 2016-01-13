package org.poormanscastle.studies.compilers.grammar.grammar3_1.parser.javacc;

import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.parser.javacc.Grammar3_1;

/**
 * Created by georg on 12.01.16.
 */
public class Grammar3_1Test {

    @Test
    public void testProgram1() throws Exception {
        new Grammar3_1(TestUtils.getTestdataAsInputStream("/grammar3_1/testprogram1.txt")).Program();
    }

}
