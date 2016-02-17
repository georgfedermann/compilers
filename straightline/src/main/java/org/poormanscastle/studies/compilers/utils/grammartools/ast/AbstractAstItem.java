package org.poormanscastle.studies.compilers.utils.grammartools.ast;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.AstItem;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.CodePosition;

/**
 * implements features and properties common to all AST items.
 * <p/>
 * Created by georg on 13.01.16.
 */
public abstract class AbstractAstItem implements AstItem {

    /**
     * to facilitate enhanced parser error messages during semantic checks etc. an AST items
     * knows the location of the tokens within the source code from which it was inferred.
     */
    private final CodePosition codePosition;

    public AbstractAstItem(CodePosition codePosition) {
        this.codePosition = codePosition;
    }

    @Override
    public CodePosition getCodePosition() {
        return codePosition;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
