package org.poormanscastle.studies.compilers.utils.grammartools.ast;

import org.apache.commons.lang3.StringUtils;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * In a SymbolTable each variable name and formal-parameter name are bound to their types.
 * <p/>
 * Each method is bound to its parameters, its result type and its local variables.
 * <p/>
 * Each class name is bound to its variable declarations and its method declarations.
 * <p/>
 * The Binding type is capable of storing all of this information, probably using subclasses like
 * MethodBinding, ClassBinding and VariableBinding.
 * TODO factor this type to a type hierarchy as described above.
 * <p/>
 * Created by 02eex612 on 19.02.2016.
 */
public class Binding {

    /**
     * the symbol's type. Like Int, Double, String, Boolean, CustomType ...
     */
    private final String declaredType;

    /**
     * at this stage also store a value here, so that the symbol table can
     * intermediately also be used as an interpreter table.
     */
    private Object value;

    public Binding(String declaredType, Object value) {
        checkArgument(!StringUtils.isBlank(declaredType));
        this.declaredType = declaredType;
        this.value = value;
    }

    public Binding(String declaredType) {
        this(declaredType, null);
    }

    public String getDeclaredType() {
        return declaredType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
