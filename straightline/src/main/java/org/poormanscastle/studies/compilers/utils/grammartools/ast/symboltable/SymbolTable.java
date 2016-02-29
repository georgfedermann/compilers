package org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Binding;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Symbol;

/**
 * 1st version of a symbol table for the still straightline language v0.1.
 * <p/>
 * even a simple sequence of declaration statements will create multiple environments.
 * Along with a sequence of declaration statements, different identifiers and their bindings will
 * become available.
 *
 * Created by 02eex612 on 19.02.2016.
 */
public class SymbolTable {

    private Map<Symbol, Binding> bindings = new HashMap<>();

    /**
     * this simple single scope SymbolTable implementation will not accept mutliple symbols with the same name.
     *
     * @param name
     * @param type
     */
    public void addSymbol(String name, String type) {
        Symbol symbol = Symbol.getSymbol(name);
        if (bindings.containsKey(symbol)) {
            throw new RuntimeException(StringUtils.join("Symbol already in symbol table:", symbol));
        }
        bindings.put(symbol, new Binding(type));
    }

    public Binding getBinding(Symbol symbol){
        return bindings.get(symbol);
    }

    public int getSize(){
        return bindings.size();
    }

}
