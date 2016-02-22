package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by 02eex612 on 22.02.2016.
 */
public class TypeTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testAreTypesCompatible() throws Exception {
        assertTrue(Type.areTypesCompatible(Type.BOOLEAN, Type.BOOLEAN));
        assertTrue(Type.areTypesCompatible(Type.INT, Type.INT));
        assertTrue(Type.areTypesCompatible(Type.INT, Type.DOUBLE));
        assertTrue(Type.areTypesCompatible(Type.DOUBLE, Type.DOUBLE));
        assertTrue(Type.areTypesCompatible(Type.DOUBLE, Type.INT));
        assertTrue(Type.areTypesCompatible(Type.TEXT, Type.BOOLEAN));
        assertTrue(Type.areTypesCompatible(Type.TEXT, Type.INT));
        assertTrue(Type.areTypesCompatible(Type.TEXT, Type.DOUBLE));
        assertTrue(Type.areTypesCompatible(Type.TEXT, Type.TEXT));

        assertFalse(Type.areTypesCompatible(Type.BOOLEAN, Type.INT));
        assertFalse(Type.areTypesCompatible(Type.BOOLEAN, Type.DOUBLE));
    }

    @Test
    public void testGetInferredType() throws Exception {
        assertEquals(Type.DOUBLE, Type.getInferredType(Type.INT, Type.DOUBLE));
        assertEquals(Type.INT, Type.getInferredType(Type.INT, Type.INT));
        assertEquals(Type.BOOLEAN, Type.getInferredType(Type.BOOLEAN, Type.BOOLEAN));
        assertEquals(Type.TEXT, Type.getInferredType(Type.TEXT, Type.BOOLEAN));
        assertEquals(Type.TEXT, Type.getInferredType(Type.TEXT, Type.INT));
        assertEquals(Type.TEXT, Type.getInferredType(Type.TEXT, Type.DOUBLE));
        assertEquals(Type.TEXT, Type.getInferredType(Type.TEXT, Type.TEXT));
    }

    @Test
    public void testBooleanWithInteger() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("The two types BOOLEAN and INT are incompatible.");
        Type.getInferredType(Type.BOOLEAN, Type.INT);
    }

    @Test
    public void testBooleanWithDouble() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("The two types BOOLEAN and DOUBLE are incompatible.");
        Type.getInferredType(Type.BOOLEAN, Type.DOUBLE);
    }
}