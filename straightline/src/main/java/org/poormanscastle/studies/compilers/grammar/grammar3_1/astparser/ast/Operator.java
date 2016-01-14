package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

/**
 * operators operate on Terms and Factors. Expression objects hold Term-Operators (+, -),
 * and Term objects hold Factor-Operators, which sound counter-intuitively at first, but
 * that's how it is.
 * <p>
 * Created by georg on 14.01.16.
 */
public interface Operator extends AstItem {

}
