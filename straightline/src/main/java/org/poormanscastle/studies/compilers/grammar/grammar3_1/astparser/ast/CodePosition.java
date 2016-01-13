package org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.ast;

import org.poormanscastle.studies.compilers.grammar.grammar3_1.astparser.javacc.Token;

/**
 * tracks the position of the current token represented by the current AST item within the source code.
 * Created by georg on 14.01.16.
 */
public class CodePosition {

    private int beginLine;

    private int beginColumn;

    private int endLine;

    private int endColumn;

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
