package org.poormanscastle.studies.compilers.grammar.minijava.javacc;

import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;

/**
 * Created by georg on 21.12.15.
 */
public class MiniJavaTest {

    @Test
    public void testStatement() throws Exception {
        new MiniJava(TestUtils.getTestdataAsInputStream("/miniJavaTestData/testclass.txt")).Statement();
    }
}