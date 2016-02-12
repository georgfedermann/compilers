package org.poormanscastle.studies.compilers.utils.grammartools;

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

    /**
     * here the table creator can preprocess the grammar in whatever way is necessary to accumulate informations
     * needed for defining the parser table. E.g. for an LL(1) parser table or predictive parser table or
     * recursive descent parser table, the table creator needs to calculate nullabilty, first sets and follow sets
     * for symbols and productions prior to writing the parser table.
     *
     * @param grammar
     * @return
     */
    Grammar preprocess(Grammar grammar);

}
