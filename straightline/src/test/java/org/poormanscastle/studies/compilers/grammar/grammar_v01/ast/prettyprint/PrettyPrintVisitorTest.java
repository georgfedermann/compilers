package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.prettyprint;

import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.Program;

import static org.junit.Assert.assertNotNull;

/**
 * Created by 02eex612 on 18.02.2016.
 */
public class PrettyPrintVisitorTest {

    @Test
    public void testSerialize() throws Exception {
//        Program program = TestUtils.loadProgram("testprogram1.prog");
//        Program program = TestUtils.loadProgram("UndeclaredId.prog");
//        Program program = TestUtils.loadProgram("mixedTypes.prog");
//        Program program = TestUtils.loadProgram("assignDoubleValueToIntVariableBug.prog");
        Program program = TestUtils.loadProgram("BlockScopeTest1.oh");
//        Program program = TestUtils.loadProgram("BlockScopeTest2.oh");
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