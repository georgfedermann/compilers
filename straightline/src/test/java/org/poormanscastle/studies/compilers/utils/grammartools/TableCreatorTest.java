package org.poormanscastle.studies.compilers.utils.grammartools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.poormanscastle.studies.compilers.utils.grammartools.ll1.LL1TableCreator;

/**
 * Created by georg on 13.02.16.
 */
public class TableCreatorTest {

    @Test
    public void testGetTableCreatorLL1() throws Exception {
        TableCreator creator = TableCreator.getTableCreator(GrammarFlavor.LL1);
        assertNotNull(creator);
        assertEquals(LL1TableCreator.class, creator.getClass());
    }

    @Test
    public void testGetTableCreatorLR0() throws Exception {
        // TODO implement LR0TableCreator
//        TableCreator creator = TableCreator.getTableCreator(GrammarFlavor.LR0);
//        assertNotNull(creator);
//        assertEquals(LR0TableCreator.class, creator.getClass());
    }
}