package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

/**
 * an expression can be evaluated to a value of the various types defined by the given grammar.
 * <p>
 * Created by 02eex612 on 17.02.2016.
 */
public interface Expression<T> extends AstItem {

    /**
     * can be used to query the state of the given expression.
     *
     * @return
     */
    ExpressionState getState();

    /**
     * @return the type of this expression's value. For the types BooleanExpression, NumExpression, TextExpression
     * and DecimalExpression, the return value is statically implemented in the expression. For IdExpressions, the
     * value must be looked up in the symbol table. For OperatorExpressions, the value is inferred from their sub
     * expressions.
     */
    Type getValueType();

    /**
     * when the interpreter crawls the ast it may buffer values on the operators for later reference.
     * e.g. in the ConditionalStatement, the interpreter will need to access the value of the condition
     * when deciding whether to follow the THEN branch or the ELSE branche (if any).
     * <p>
     * intended for the usage of AST interpreters. this is a side effect of the interpreter on the AST.
     * but it happens only in the interpreter use case. no compiler phase should want or need to
     * make use of the value field.
     * <p>
     * TODO this could also be achieved by use of a condition stack which gets pushed on entry to
     * a ConditionalStatement and popped when the statement is left again. Thus, also nested
     * ConditionalStatements could be managed. On the other hand, value management may make
     * Expressions more powerful a tool in the context of the interpreter.
     *
     * @return the value which the AST interpreter parked here before for later retrieval
     */
    T getValue();

    /**
     * when the interpreter crawls the ast it may buffer values on the operators for later reference.
     * e.g. in the ConditionalStatement, the interpreter will need to access the value of the condition
     * when deciding whether to follow the THEN branch or the ELSE branche (if any).
     *
     * @return
     */
    void setValue(T value);

}
