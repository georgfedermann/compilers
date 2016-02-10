package org.poormanscastle.studies.compilers.utils.grammartools;

import static com.google.common.base.Preconditions.checkState;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Created by georg on 09.02.16.
 */
public class Grammar {

    private List<Production> productions = new LinkedList<>();

    private Map<String, Symbol> symbols = new HashMap<>();

    private Set<Symbol> terminalSymbols = new HashSet<>();

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

    public void identifyStartSymbols() {
        // take care of the terminal symbols:
        // for all terminal symbols s: first(s) <- {s}
        for (Symbol symbol : getTerminalSymbols()) {
            checkState(symbol.isTerminal());
            symbol.addToFirstSet(symbol);
        }

        boolean somethingHappened = false;


        do {
            somethingHappened = false;
            for (Production production : productions) {
                // go through rhs and do some set algebra
                // visible says if all rhs symbols up to now where nullable and the
                // current symbol is relevant for first(X) in X -> c1 ... ci ... ck
                boolean visibleFirstX = true;
                for (int i = 0; i < production.getRhs().size(); i++) {
                    // impact of symbol.firstSet on X.firstSet
                    Symbol symbol = production.getRhs().get(i);
                    if (visibleFirstX && !production.getLhs().getFirstSet().containsAll(symbol.getFirstSet())) {
                        production.getLhs().getFirstSet().addAll(symbol.getFirstSet());
                        somethingHappened = true;
                    }
                    visibleFirstX &= symbol.isNullable();
                    // impact of X.followSet on symbol.FollowSet
                    boolean visibleFollowX = true;
                    for (int j = i + 1; j < production.getRhs().size(); j++) {
                        if (!symbol.getFollowSet().containsAll(production.getRhs().get(j).getFirstSet())) {
                            symbol.getFollowSet().addAll(production.getRhs().get(j).getFirstSet());
                            somethingHappened = true;
                        }
                        if (!(visibleFollowX &= production.getRhs().get(j).isNullable())) {
                            break;
                        }
                    }
                    // if all symbols between si and the end of the production's rhs are nullable,
                    // add follow(X) to follow(si).
                    if (visibleFollowX && !symbol.getFollowSet().containsAll(production.getLhs().getFollowSet())) {
                        symbol.getFollowSet().addAll(production.getLhs().getFollowSet());
                        somethingHappened = true;
                    }
                }
            }


        } while (somethingHappened);
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
