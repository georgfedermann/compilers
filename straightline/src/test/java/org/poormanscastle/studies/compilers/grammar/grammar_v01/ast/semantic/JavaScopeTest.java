package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.semantic;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by 02eex612 on 26.02.2016.
 */
public class JavaScopeTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    private String a = "Hello, World!";

    @Test
    public void scopeTest() {
        System.out.print(a);
        int a = 12;
        System.out.print(a);
        assertEquals("Hello, World!12", systemOutRule.getLog());
        {
            // empty blocks are allowed.
            int c = 3;
            assertEquals(3, c);
        }
        // assertEquals(3, c); // here c is not known any more
        {
            String c = "Hello, World!";
            assertEquals("Hello, World!", c);
        }
    }

    @Test
    public void scopeTest1() {
        int a = 3, b = a;
        assertEquals(a, b);
    }

}
