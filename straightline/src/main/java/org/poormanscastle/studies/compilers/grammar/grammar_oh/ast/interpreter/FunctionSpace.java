package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.interpreter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Function;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 02eex612 on 09.03.2016.
 */
public final class FunctionSpace {

    private Map<String, Function> functionSpace;

    public FunctionSpace() {
        functionSpace = new HashMap<>();
    }

    public void addFunction(String name, Function function) {
        checkArgument(!StringUtils.isBlank(name));
        checkNotNull(function);
        functionSpace.put(name, function);
    }

    public Function getFunction(String name) {
        checkArgument(!StringUtils.isBlank(name));
        return functionSpace.get(name);
    }
}
