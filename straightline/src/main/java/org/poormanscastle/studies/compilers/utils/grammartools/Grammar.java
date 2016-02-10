package org.poormanscastle.studies.compilers.utils.grammartools;

import static com.google.common.base.Preconditions.checkState;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by georg on 09.02.16.
 */
public class Grammar {

    private final List<Production> productions = new LinkedList<>();

    private final Map<String, Symbol> symbols = new HashMap<>();

    private final Set<Symbol> terminalSymbols = new HashSet<>();

    public boolean addProduction(Production production) {
        return productions.add(production);
    }

    public Symbol addSymbol(Symbol symbol) {
        return symbols.put(symbol.getId(), symbol);
    }

    public void addTerminalSymbol(Symbol terminalSymbol) {
        if (terminalSymbols.contains(terminalSymbol)) {
            throw new RuntimeException(StringUtils.join("TerminalSymbol already defined: ", terminalSymbol));
        }
        if (symbols.containsKey(terminalSymbol.getId())) {
            throw new RuntimeException(StringUtils.join("Symbol already defined: ", terminalSymbol));
        }
        terminalSymbols.add(terminalSymbol);
        addSymbol(terminalSymbol);
    }

    public List<Production> getProductions() {
        return productions;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    public Set<Symbol> getTerminalSymbols() {
        return terminalSymbols;
    }

    /**
     * creates the first sets and follow sets for all symbols and productions.
     */
    public void calculateStartAndFollowSets() {
        // take care of the terminal symbols:
        // for all terminal symbols s: first(s) <- {s}
        for (Symbol symbol : getTerminalSymbols()) {
            checkState(symbol.isTerminal());
            symbol.addToFirstSet(symbol);
        }
        // the algorithm is repeated until the tables do not change for a whole iteration.
        boolean tablesWereUpdated = false;
        do {
            tablesWereUpdated = false;
            for (Production production : productions) {
                // go through rhs and do some set algebra
                // visible says if all rhs symbols up to now where nullable and the
                // current symbol is relevant for first(X) in X -> c1 ... ci ... ck
                boolean visibleFirstX = true;
                for (int i = 0; i < production.getRhs().size(); i++) {
                    // impact of symbol.firstSet on X.firstSet.
                    // also, production.firstSet gets updated
                    Symbol symbol = production.getRhs().get(i);
                    if (visibleFirstX // the next two checks are to avoid tablesWereUpdated=true when all is aleady set.
                            && ( !production.getLhs().getFirstSet().containsAll(symbol.getFirstSet())
                            || !production.getFirstSet().containsAll(symbol.getFirstSet()))) {
                        production.getLhs().getFirstSet().addAll(symbol.getFirstSet());
                        production.getFirstSet().addAll(symbol.getFirstSet());
                        tablesWereUpdated = true;
                    }
                    visibleFirstX &= symbol.isNullable();
                    // impact of X.followSet on symbol.FollowSet
                    boolean visibleFollowX = true;
                    for (int j = i + 1; j < production.getRhs().size(); j++) {
                        if (!symbol.getFollowSet().containsAll(production.getRhs().get(j).getFirstSet())) {
                            symbol.getFollowSet().addAll(production.getRhs().get(j).getFirstSet());
                            tablesWereUpdated = true;
                        }
                        if (!(visibleFollowX &= production.getRhs().get(j).isNullable())) {
                            break;
                        }
                    }
                    // if all symbols between si and the end of the production's rhs are nullable,
                    // add follow(X) to follow(si).
                    if (visibleFollowX && !symbol.getFollowSet().containsAll(production.getLhs().getFollowSet())) {
                        symbol.getFollowSet().addAll(production.getLhs().getFollowSet());
                        tablesWereUpdated = true;
                    }
                }
            }
        } while (tablesWereUpdated);
    }

    public void identifyNullableSymbols() {
        boolean somethingHappened = false;
        do {
            somethingHappened = false;
            for (Production production : productions) {
                if (production.getRhs().isEmpty()) {
                    // if rhs is empty, than the lhs symbol is nullable.
                    if (!production.getLhs().isNullable()) {
                        production.getLhs().setNullable(true);
                        somethingHappened = true;
                    }
                } else {
                    // if all rhs symbols are nullable, then so is the lhs symbol.
                    boolean allRhsNullable = true;
                    for (Symbol symbol : production.getRhs()) {
                        if (!(allRhsNullable &= symbol.isNullable())) {
                            break;
                        }
                    }
                    if (allRhsNullable) {
                        System.out.println(StringUtils.join("there really were all rhs nullable!: ", production));
                        if (!production.getLhs().isNullable()) {
                            production.getLhs().setNullable(true);
                            somethingHappened = true;
                        }
                    }
                }
            }
        } while (somethingHappened);
    }
}
