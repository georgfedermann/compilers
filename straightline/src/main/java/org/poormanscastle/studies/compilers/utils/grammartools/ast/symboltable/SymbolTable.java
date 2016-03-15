package org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Function;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Parameter;
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

    private Map<String, FunctionDeclaration> functionTable = new HashMap<>();

    /**
     * initializies the SymbolTable and adds the sigma zero environment.
     */
    public SymbolTable() {
        checkState(environments.isEmpty());
        newScope();
    }

    /**
     * called by the symbol table creator visitor when a function is found in the AST.
     *
     * @param function
     */
    public void addFunctionDeclaration(Function function, List<Parameter> parameters) {
        List<String> parameterNames = new LinkedList<>();
        Map<Symbol, Binding> parameterBindings = new HashMap<>();
        for (Parameter parameter : parameters) {
            parameterNames.add(parameter.getId());
            parameterBindings.put(Symbol.getSymbol(parameter.getId()), new Binding(parameter.getType().name()));
        }
        functionTable.put(function.getId(), new FunctionDeclaration(function.getId(),
                function.getValueType().name(), parameterNames, parameterBindings));
    }

    public FunctionDeclaration lookupFunctionDeclaration(String functionId) {
        return functionTable.get(functionId);
    }

    public boolean isFunctionDeclared(String functionId) {
        return functionTable.containsKey(functionId);
    }

    /**
     * this simple single scope SymbolTable implementation will not accept mutliple symbols with the same name.
     *
     * @param name
     * @param type
     * @return {@code true} if the new symbol was registered successfully registered with the SymbolTable,
     * {@code false} if there already was a symbol with that name in the current scope or other errors occurred.
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
        // Environment sigma0 is always there:
        checkState(!environments.isEmpty());
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
