package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.javacc.Token;

/**
 * Using the CodePosition, the parser and other compiler components can give enhanced error messages
 * when detecting problems within an AstItem.
 * <p/>
 * Created by georg on 14.01.16.
 */
public final class CodePosition {

    private final int beginLine;

    private final int beginColumn;

    private final int endLine;

    private final int endColumn;

    /**
     * convenience implementation retrieving all information from the given token; thus binding
     * the implementation a bit to JavaCC.
     *
     * @param token
     */
    public CodePosition(Token token) {
        beginLine = token.beginLine;
        beginColumn = token.beginColumn;
        endLine = token.endLine;
        endColumn = token.endColumn;
    }

    public static CodePosition createFromToken(Token token) {
        return new CodePosition(token);
    }

    public int getBeginLine() {
        return beginLine;
    }

    public int getBeginColumn() {
        return beginColumn;
    }

    public int getEndLine() {
        return endLine;
    }

    public int getEndColumn() {
        return endColumn;
    }

}
