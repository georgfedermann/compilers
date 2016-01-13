package org.poormanscastle.studies.compilers.grammar.minijava.javacc;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;

/**
 * Created by georg on 21.12.15.
 */
public class MiniJavaTest {

    @Test
    public void testStatement() throws Exception {
        System.out.println(IOUtils.toString(TestUtils.getTestdataAsInputStream("/miniJavaTestData/testclass.txt")));
        // new MiniJava(TestUtils.getTestdataAsInputStream("/miniJavaTestData/testclass.txt")).Program();
    }
}