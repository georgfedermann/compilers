package org.poormanscastle.studies.compilers.grammar.hitclou.javacc;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.poormanscastle.studies.compilers.TestUtils;

/**
 * Created by georg on 21.12.15.
 */
public class HitClouTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testInvalidClouBaustein() throws Exception {
        expectedException.expect(ParseException.class);
        expectedException.expectMessage("Was expecting:");
        new HitClou(TestUtils.getTestdataAsInputStream("/clouTestData/notClou.clou")).Baustein();
    }

    @Test
    public void testCommentSingleLine() throws Exception {
        new HitClou(TestUtils.getTestdataAsInputStream("/clouTestData/commentsSingleLine.clou")).Baustein();
    }

    @Test
    public void testCommentSingleLineUnclosed() throws Exception {
        expectedException.expect(TokenMgrError.class);
        expectedException.expectMessage("Lexical error at line 6, column 2.  Encountered: \"*\" (42), after : \"\"");
        new HitClou(TestUtils.getTestdataAsInputStream("/clouTestData/commentsSingleLineUnclosed.clou")).Baustein();
    }

    @Test
    public void testCommentMultiline() throws Exception {
        new HitClou(TestUtils.getTestdataAsInputStream("/clouTestData/commentsMultiLine.clou")).Baustein();
    }

    @Test
    public void testCommentMultiLineUnclosed() throws Exception {
        expectedException.expect(TokenMgrError.class);
        expectedException.expectMessage("Lexical error at line 9, column 0.  Encountered: <EOF> after : \"#*    mehrzeiliger Kommentar");
        new HitClou(TestUtils.getTestdataAsInputStream("/clouTestData/commentsMultiLineUnclosed.clou")).Baustein();
    }

    @Test
    public void testPseudoKommentar() throws Exception {
        new HitClou(TestUtils.getTestdataAsInputStream("/clouTestData/pseudoComment.clou")).Baustein();
    }

}