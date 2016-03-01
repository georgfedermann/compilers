package org.poormanscastle.studies.compilers.utils.grammartools;


import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.utils.grammartools.exceptions.CompilerException;
import org.poormanscastle.studies.compilers.utils.grammartools.ll1.LL1Grammar;
import org.poormanscastle.studies.compilers.utils.grammartools.lr.LR0Grammar;

/**
 * a grammar defines a language.
 * <p>
 * a grammar is defined by a set of productions consisting of symbols of the grammar's alphabet.
 * <p>
 * Created by georg on 13.02.16.
 */
public interface Grammar {
    void addTerminalSymbol(Symbol symbol);

    boolean addProduction(Production production);

    Symbol addSymbol(Symbol symbol);

    Symbol getSymbolForToken(String token);

    Set<Symbol> getTerminalSymbols();

    List<Production> getProductions();

    Collection<Symbol> getSymbols();

    /**
     * after a grammar's been loaded from an input stream (non terminal symbols and productions are identified),
     * the grammar needs to initialized itself prior to subsequent use. Like an LL(1) grammar will have to
     * calculate its first and follow sets, an LR(0) grammar will have to calculate its states, and so on.
     * <p>
     * Before a grammar can be utilized, this method must be called. This method will e.g. be called by the
     * GrammarReader implementation. If a grammar was loaded using the GrammarReader, subsequent calls to this method
     * will be not so necessary.
     *
     * @return the given grammar for method call chaining
     */
    Grammar initialize();

    /**
     * delivers a list of all left-hand-side symbols of this grammar's productions. Or in other words the non-terminal
     * symbols.
     *
     * @return a List<Symbol> containing all non-terminal symbols of this grammar.
     */
    List<Symbol> getLhsSymbols();

    static Grammar createGrammar(GrammarFlavor flavor) {
        if (flavor == GrammarFlavor.LL1) {
            return new LL1Grammar();
        } else if (flavor == GrammarFlavor.LR0) {
            return new LR0Grammar();
        } else {
            throw new CompilerException(StringUtils.join("No implementation for grammar flavor available: ", flavor));
        }
    }
}
