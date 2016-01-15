package org.poormanscastle.studies.compilers.grammar.grammar3_1.visitors;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast.Statement;
import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.javacc.AstParser;

/**
 * Created by georg on 15.01.16.
 */
public class AstPrettyPrintVisitorTest {

    @Test
    public void testSerialize() throws Exception {
        Statement statement = new AstParser(TestUtils.getTestdataAsInputStream("/grammar3_1/testprogram1.txt")).P();
        assertNotNull(statement);
        AstPrettyPrintVisitor printer = new AstPrettyPrintVisitor();
        printer.initialize();
        if (statement.handleProceedWith(printer)) {
            statement.accept(printer);
        }
        String result = printer.serialize();
        System.out.println(result);
    }
}