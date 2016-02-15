package org.poormanscastle.studies.compilers.utils.grammartools.lr;

import java.io.File;
import java.io.FileOutputStream;

import org.junit.Test;
import org.poormanscastle.studies.compilers.TestUtils;
import org.poormanscastle.studies.compilers.utils.grammartools.Grammar;
import org.poormanscastle.studies.compilers.utils.grammartools.GrammarFlavor;
import org.poormanscastle.studies.compilers.utils.grammartools.GrammarReader;
import org.poormanscastle.studies.compilers.utils.grammartools.TableCreator;

/**
 * Created by 02eex612 on 15.02.2016.
 */
public class LR0TableCreatorTest {

    @Test
    public void testCreateDotNodeFromLRState() throws Exception {
        Grammar grammar = Grammar.createGrammar(GrammarFlavor.LR0);
        new GrammarReader().readGrammar(TestUtils.getTestdataAsInputStream("/grammartools/grammar3.20.gr"), grammar);
        TableCreator creator = TableCreator.getTableCreator(GrammarFlavor.LR0);
        String stateMachine = creator.createTable(grammar);

        FileOutputStream outputStream = new FileOutputStream(new File("grammartools/lr0GrammarStateMachine.dot"));
        outputStream.write(stateMachine.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
}