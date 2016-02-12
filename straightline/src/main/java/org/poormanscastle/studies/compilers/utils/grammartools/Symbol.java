package org.poormanscastle.studies.compilers.utils.grammartools;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

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
public class Symbol {

    /**
     * is this a terminal or nonterminal symbol
     */
    private boolean terminal;

    /**
     * the id of the symbol. In case of a terminal symbol this is the token type.
     */
    private String id;

    /**
     * is this symbol nullable? terminal symbols cannot be nullable.
     */
    private boolean nullable;

    private Set<Symbol> firstSet = new HashSet<>();

    private Set<Symbol> followSet = new HashSet<>();

    public Symbol(String id, boolean terminal) {
        this.id = id;
        this.terminal = terminal;
    }

    /**
     * this constructur can be used to create nonterminal grammar symbols.
     * <p/>
     * E.g. In "S -> id := E" the symobls S and E can be created using this constructor, id and := should be created
     * with a call to {@code new Symbol(":=", true)}.
     *
     * @param id
     */
    public Symbol(String id) {
        this(id, false);
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
     * whether this symbol can derive the empty string.
     *
     * @return true if the symbol can be replaced by the empty string, false otherwise.
     */
    public boolean isNullable() {
        return nullable;
    }

    public String toString() {
        return ReflectionToStringBuilder.toStringExclude(this, "firstSet", "followSet");
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isTerminal() {
        return terminal;
    }


}
