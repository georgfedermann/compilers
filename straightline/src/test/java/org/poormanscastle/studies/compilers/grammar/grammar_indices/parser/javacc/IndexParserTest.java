package org.poormanscastle.studies.compilers.grammar.grammar_indices.parser.javacc;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.ByteArrayInputStream;

/**
 * Created by georg on 7/20/16.
 */
public class IndexParserTest {

    @Test
    public void index() throws Exception {
        new IndexParser(new ByteArrayInputStream(IOUtils.toByteArray(IndexParserTest.class.getResourceAsStream(
                "/indexgrammar/inputdata.ig")))).index();
    }

}