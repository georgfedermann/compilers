package org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable;

import java.util.List;
import java.util.Map;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.Binding;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.Symbol;

/**
 * could be named FunctionDeclaration or FunctionDeclaration. this type is created and managed by the Symbol Table
 * creator and used for expression validation to check function calls against function definitions.
 * <p/>
 * holds key facts about a function declaration:
 * <ul>
 * <li>function name</li>
 * <li>declared return value type identifier</li>
 * <li>a list of the parameters names in correct sequence</li>
 * <li>a table with the parameter bindings, mapping the function's parameters to their respective types</li>
 * <li>locals</li>
 * </ul>
 * <p/>
 * Created by 02eex612 on 15.03.2016.
 */
public final class FunctionDeclaration {

    /**
     * the function's name
     */
    private final String id;

    /**
     * the declared return value type identifier
     */
    private final String valueType;

    /**
     * the function's parameters in correct sequence
     */
    private final List<String> parameterNames;

    /**
     * the mapping from parameters to their respective declared types
     */
    private final Map<Symbol, Binding> parameterBindings;

    public FunctionDeclaration(String id, String valueType, List<String> parameterNames, Map<Symbol, Binding> parameterBindings) {
        this.id = id;
        this.valueType = valueType;
        this.parameterNames = parameterNames;
        this.parameterBindings = parameterBindings;
    }

    public String getId() {
        return id;
    }

    public Map<Symbol, Binding> getParameterBindings() {
        return parameterBindings;
    }

    public List<String> getParameterNames() {
        return parameterNames;
    }

    public String getValueType() {
        return valueType;
    }
}
