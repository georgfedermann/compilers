package org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable;

import java.util.LinkedList;
import java.util.List;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.Binding;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Symbol;

import static com.google.common.base.Preconditions.checkState;

/**
 * 1st version of a symbol table for the still straightline language v0.1.
 * <p/>
 * even a simple sequence of declaration statements will create multiple environments.
 * Along with a sequence of declaration statements, different identifiers and their bindings will
 * become available.
 * <p/>
 * Created by 02eex612 on 19.02.2016.
 */
public class SymbolTable {

    private List<Environment> environments = new LinkedList<>();

    /**
     * initializies the SymbolTable and adds the sigma zero environment.
     */
    public SymbolTable() {
        checkState(environments.isEmpty());
        newScope();
    }

    /**
     * this simple single scope SymbolTable implementation will not accept mutliple symbols with the same name.
     *
     * @param name
     * @param type
     */
    public Binding addSymbol(String name, String type) {
        checkState(!environments.isEmpty());
        return environments.get(0).addSymbol(name, type);
    }

    public void newScope() {
        environments.add(0, new Environment());
    }

    public void endScope() {
        checkState(!environments.isEmpty());
        environments.remove(0);
    }

    public Binding getBinding(Symbol symbol) {
        for (Environment environment : environments) {
            if (environment.contains(symbol)) {
                return environment.getSymbol(symbol);
            }
        }
        return null;
    }

    public int getSize() {
        int result = 0;
        for (Environment environment : environments) {
            result += environment.getSize();
        }
        return result;
    }

}
