package org.poormanscastle.studies.compilers.utils.grammartools;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * symbols are part of grammar definitions.
 * symbols can be terminal or nonterminal. terminal symbols are tokens as provided
 * by the lexer, those tokens are abbreviations for the regular expressions used
 * to tokenize the input stream.
 * <p/>
 * nonterminal symbols are the left-hand-sides in the grammar production rules.
 * <p/>
 * Created by georg on 09.02.16.
 */
public final class Symbol {

    /**
     * special symbol for end of file. E.g. in LR(k) parsers, for EOF no new edge will be created, because the parser
     * will do no shift or reduce on this item, but accept or not accept.
     */
    public static final Symbol EOF = new Symbol("$", true);

    /**
     * is this a terminal or nonterminal symbol
     */
    private final boolean terminal;

    /**
     * the id of the symbol. In case of a terminal symbol this is the token type.
     */
    private final String id;

    /**
     * is this symbol nullable? terminal symbols cannot be nullable.
     */
    private boolean nullable;

    private Set<Symbol> firstSet = new HashSet<>();

    private Set<Symbol> followSet = new HashSet<>();

    private Symbol(String id, boolean terminal) {
        this.id = id;
        this.terminal = terminal;
    }

    public static Symbol createTerminalSymbol(String id) {
        if ("$".equals(id)) {
            return Symbol.EOF;
        } else {
            return new Symbol(id, true);
        }
    }

    /**
     * this can be used to create nonterminal grammar symbols.
     * <p/>
     * E.g. In "S -> id := E" the symobls S and E can be created using this constructor, id and := should be created
     * with a call to {@code new Symbol(":=", true)}.
     *
     * @param id
     */
    public static Symbol createNonterminalSymbol(String id) {
        return new Symbol(id, false);
    }

    public boolean addToFirstSet(Symbol terminalSymbol) {
        return firstSet.add(terminalSymbol);
    }

    public boolean addToFollowSet(Symbol terminalSymbol) {
        return followSet.add(terminalSymbol);
    }

    /**
     * the set of terminals that can begin strings derived by this symbol.
     *
     * @return a Set of symbols
     */
    public Set<Symbol> getFirstSet() {
        return firstSet;
    }

    /**
     * the set of terminals that can immediately follow this symbol.
     * t is in follow(x) if there is any derivation containing X. This
     * is also the case if there is any derivation XYZt where Y and Z are nullable.
     *
     * @return
     */
    public Set<Symbol> getFollowSet() {
        return followSet;
    }

    /**
     * the id or name of this symbol. Like P for the Program nonterminal,
     * or the token type of a terminal symbol, like num or bool.
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * whether this symbol can derive the empty string. terminal symbols cannot derive the empty string.
     *
     * @return for nonterminal symbols this method returns true if the symbol can derive the empty string,
     * false otherwise. terminal symbols never are nullable.
     */
    public boolean isNullable() {
        return nullable;
    }

    public String toString() {
        return ReflectionToStringBuilder.toStringExclude(this, "firstSet", "followSet");
    }

    /**
     * set the symbol's nullable property.
     * <p/>
     * invariant: terminal symbols cannot be nullable. trying to set a terminal symbol to nullable will raise an exception.
     *
     * @param nullable
     */
    public void setNullable(boolean nullable) {
        checkArgument(!(terminal && nullable), "Terminal symbols cannot be nullable.");
        this.nullable = nullable;
    }

    /**
     * a symbol is terminal if it is not a lhs symbol in any production of the given grammar.
     * <p/>
     * All lexer tokens are terminal. E.g. $, +, -, id, num, dec, etc.
     *
     * @return
     */
    public boolean isTerminal() {
        return terminal;
    }

}
