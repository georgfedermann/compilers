package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

/**
 * Created by georg on 13.01.16.
 */
public abstract class AbstractStatement extends AbstractAstItem implements Statement {

    /**
     * to facilitate enhanced parser error messages during semantic checks etc. an AST items
     * knows the location of the tokens within the source code from which it was inferred.
     */
    private CodePosition codePosition;

    public AbstractStatement(CodePosition codePosition) {
        this.codePosition = codePosition;
    }

}
