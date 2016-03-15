package org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Binding;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Symbol;
import org.poormanscastle.studies.compilers.utils.grammartools.exceptions.SymbolAlreadyDefinedException;

/**
 * an environment is a collection of symbols that have been declared and are known
 * in the current scope. Environments can be nested und thus symbols can be
 * inherited to nested environments. Environments can also be referenced as is the
 * case with e.g. static class members: T.b the field b in class T. So T is a kind
 * of environment here.
 * <p/>
 * new environments are created when new scopes are added, like blocks of a conditional
 * statement. When a scope goes out of scope (end of a function, end of a block) the
 * whole environment gets removed.
 * <p/>
 * Created by 02eex612 on 29.02.2016.
 */
public final class Environment {

    private final Map<Symbol, Binding> bindings = new HashMap<>();

    public Binding addSymbol(String name, String type) {
        Symbol symbol = Symbol.getSymbol(name);
        if (bindings.containsKey(symbol)) {
            throw new SymbolAlreadyDefinedException(StringUtils.join("Symbol already in Symboltable:", symbol));
        }
        Binding binding = new Binding(type);
        bindings.put(symbol, binding);
        return binding;
    }

    public boolean contains(Symbol symbol) {
        return bindings.containsKey(symbol);
    }

    public Binding getSymbol(Symbol symbol) {
        return bindings.get(symbol);
    }

    public int getSize() {
        return bindings.size();
    }
}
