package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.parser.javacc;

import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.Program;

import static org.junit.Assert.assertNotNull;

/**
 * Created by 02eex612 on 18.02.2016.
 */
public class V01AstParserTest {

    @Test
    public void astParserTest() throws Exception {
        Program program = new V01AstParser(TestUtils.getTestdataAsInputStream("/grammar_v01/testprogram1.prog")).P();
        assertNotNull(program);
    }

}