package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

/**
 * what's written below is wrong: Terms and Factors are only needed to create an
 * unambiguous grammar for parsing. They won't make it into the AST altogether, where
 * all needed semantic information is conveyed by node types and tree structure.
 * Operators are no AstItems at all but are used as classifiers for OpExp (Operator
 * Expressions which have Operand children onto which the operator will be applied).
 * <p>
 * operators operate on Terms and Factors. Expression objects hold Term-Operators (+, -),
 * and Term objects hold Factor-Operators, which sound counter-intuitively at first, but
 * that's how it is.
 * <p>
 * Created by georg on 14.01.16.
 */
public enum Operator {

    PLUS, MINUS, TIMES, DIV;

}
