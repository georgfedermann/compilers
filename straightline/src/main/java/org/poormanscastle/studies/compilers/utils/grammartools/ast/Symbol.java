package org.poormanscastle.studies.compilers.utils.grammartools.ast;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * this is a language symbol, that is managed in a symbol table.
 * <p/>
 * a symbol is any identifier, like the names of user defined types, names of functions or names of variables.
 * <p/>
 * requirements into the symbol implementation: comparing symbols for equality has to be fast.
 * Calculating an int hash key for a symbol has to be fast. Comparing 2 symbols for "greater than"
 * has to be fast.
 * <p/>
 * Created by 02eex612 on 19.02.2016.
 */
public class Symbol {
    /**
     * the symbol's name (identifier).
     */
    private final String name;

    private final static Map<String, Symbol> symbols = new HashMap<>();

    private Symbol(String name) {
        this.name = name.intern();
    }

    /**
     * delivers the symbol for the given id. If there exists no such symbol yet, a new one is created for the
     * given id.
     *
     * @param name
     * @return
     */
    public static Symbol getSymbol(String name) {
        String u = name.intern();
        Symbol result = symbols.get(u);
        if (result == null) {
            result = new Symbol(u);
            symbols.put(u, result);
        }
        return result;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
