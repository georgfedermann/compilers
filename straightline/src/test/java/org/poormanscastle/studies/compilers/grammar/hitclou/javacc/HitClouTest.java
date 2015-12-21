package org.poormanscastle.studies.compilers.grammar.hitclou.javacc;

import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by georg on 21.12.15.
 */
public class HitClouTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testInvalidClouBaustein() throws Exception {
        expectedException.expect(ParseException.class);
        expectedException.expectMessage("Was expecting:\n    \"#\\n\" ...");
        new HitClou(getTestdataAsInputStream("/clouTestData/notClou.clou")).Baustein();
    }

    @Test
    public void testCommentSingleLine() throws Exception {
        new HitClou(getTestdataAsInputStream("/clouTestData/commentsSingleLine.clou")).Baustein();
    }

    @Test
    public void testCommentSingleLineUnclosed() throws Exception {
        expectedException.expect(TokenMgrError.class);
        expectedException.expectMessage("Lexical error at line 6, column 2.  Encountered: \"*\" (42), after : \"\"");
        new HitClou(getTestdataAsInputStream("/clouTestData/commentsSingleLineUnclosed.clou")).Baustein();
    }

    @Test
    public void testCommentMultiline() throws Exception {
        new HitClou(getTestdataAsInputStream("/clouTestData/commentsMultiLine.clou")).Baustein();
    }

    @Test
    public void testCommentMultiLineUnclosed() throws Exception {
        expectedException.expect(TokenMgrError.class);
        expectedException.expectMessage("Lexical error at line 9, column 0.  Encountered: <EOF> after : \"#*    mehrzeiliger Kommentar");
        new HitClou(getTestdataAsInputStream("/clouTestData/commentsMultiLineUnclosed.clou")).Baustein();
    }

    @Test
    public void testPseudoKommentar() throws Exception {
        new HitClou(getTestdataAsInputStream("/clouTestData/pseudoComment.clou")).Baustein();
    }

    private InputStream getTestdataAsInputStream(String path) throws Exception {
        return new ByteArrayInputStream(IOUtils.toByteArray(getClass().getResourceAsStream(path)));
    }

}