package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.prettyprint;

import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.Program;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.parser.javacc.V01AstParser;

import static org.junit.Assert.assertNotNull;

/**
 * Created by 02eex612 on 18.02.2016.
 */
public class PrettyPrintVisitorTest {

    @Test
    public void testSerialize() throws Exception {
//        Program program = new V01AstParser(TestUtils.getTestdataAsInputStream("/grammar_v01/testprogram1.prog")).P();
//        Program program = new V01AstParser(TestUtils.getTestdataAsInputStream("/grammar_v01/UndeclaredId.prog")).P();
//        Program program = new V01AstParser(TestUtils.getTestdataAsInputStream("/grammar_v01/mixedTypes.prog")).P();
        Program program = new V01AstParser(TestUtils.getTestdataAsInputStream("/grammar_v01/assignDoubleValueToIntVariableBug.prog")).P();
        assertNotNull(program);
        PrettyPrintVisitor printer = new PrettyPrintVisitor();
        printer.initialize();
        if (program.handleProceedWith(printer)) {
            program.accept(printer);
        }
        String result = printer.serialize();
        System.out.println(result);
    }
}