package org.poormanscastle.studies.compilers.utils.grammartools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.utils.grammartools.exceptions.CompilerException;

/**
 * Created by georg on 13.02.16.
 */
public abstract class AbstractGrammar implements Grammar {

    protected final List<Production> productions = new LinkedList<>();

    protected final Map<String, Symbol> symbols = new HashMap<>();

    protected final Set<Symbol> terminalSymbols = new HashSet<>();

    private final Set<Symbol> nonterminalSymbols = new HashSet<>();

    public boolean addProduction(Production production) {
        return productions.add(production);
    }

    @Override
    public List<Symbol> getLhsSymbols() {
        synchronized (nonterminalSymbols) {
            if (nonterminalSymbols.isEmpty()) {
                for (Production production : productions) {
                    nonterminalSymbols.add(production.getLhs());
                }
            }
        }
        return Collections.unmodifiableList(new ArrayList(nonterminalSymbols));
    }

    @Override
    public Set<Symbol> getTerminalSymbols() {
        return Collections.unmodifiableSet(terminalSymbols);
    }

    @Override
    public Collection<Symbol> getSymbols() {
        return Collections.unmodifiableCollection(symbols.values());
    }

    @Override
    public List<Production> getProductions() {
        return Collections.unmodifiableList(productions);
    }

    public Symbol addSymbol(Symbol symbol) {
        if (symbols.containsKey(symbol.getId())) {
            throw new CompilerException(StringUtils.join("Symbol already defined: ", symbol));
        }
        return symbols.put(symbol.getId(), symbol);
    }

    public void addTerminalSymbol(Symbol terminalSymbol) {
        if (terminalSymbols.contains(terminalSymbol)) {
            throw new CompilerException(StringUtils.join("Terminal symbol already defined: ", terminalSymbol));
        }
        if (symbols.containsKey(terminalSymbol.getId())) {
            throw new CompilerException(StringUtils.join("Symbol already defined: ", terminalSymbol));
        }
        terminalSymbols.add(terminalSymbol);
        addSymbol(terminalSymbol);
    }

    @Override
    public Symbol getSymbolForToken(String token) {
        return symbols.get(token);
    }

}
