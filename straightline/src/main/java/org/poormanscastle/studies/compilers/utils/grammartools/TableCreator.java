package org.poormanscastle.studies.compilers.utils.grammartools;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.utils.grammartools.ll1.LL1TableCreator;
import org.poormanscastle.studies.compilers.utils.grammartools.lr.LR0TableCreator;

/**
 * takes a grammar and produces a visual representation of the parser table in form of a string which can than be
 * interpreted by some visual application. E.g. output is html, renderer is browser. output is a dot file, can be
 * converted to pdf or whatever. the implementation of TableCreator is free to define an output format as seems fit
 * for the respective table.
 * <p/>
 * Created by 02eex612 on 12.02.2016.
 */
public interface TableCreator {

    /**
     * this command serializes the grammar into a visual representation of a parser table.
     *
     * @param grammar
     * @return
     */
    String createTable(Grammar grammar);

    static TableCreator getTableCreator(GrammarFlavor flavor) {
        if (flavor == GrammarFlavor.LL1) {
            return new LL1TableCreator();
        } else if (flavor == GrammarFlavor.LR0) {
            return new LR0TableCreator();
        } else {
            throw new RuntimeException(StringUtils.join("Unsupported GrammarFlavor: ", flavor));
        }
    }

    static void main(String[] args) throws Exception {
        GrammarFlavor flavor = args.length == 0 ? GrammarFlavor.LL1 : GrammarFlavor.valueOf(args[0]);
        Grammar grammar = Grammar.createGrammar(flavor);
        TableCreator tableCreator = TableCreator.getTableCreator(flavor);
        System.out.print(tableCreator.createTable(
                new GrammarReader().readGrammar(System.in, grammar)));
    }
}
