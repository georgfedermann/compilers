package org.poormanscastle.studies.compilers.utils.grammartools.ast;

import org.apache.commons.lang3.StringUtils;

/**
 * tracks the position of the current token represented by the current AST item within the source code.
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
     * create a CodePosition object for a symbol found in source code.
     *
     * @param beginLine
     * @param beginColumn
     * @param endLine
     * @param endColumn
     */
    private CodePosition(int beginLine, int beginColumn, int endLine, int endColumn) {
        this.beginLine = beginLine;
        this.beginColumn = beginColumn;
        this.endLine = endLine;
        this.endColumn = endColumn;
    }

    /**
     * create a CodePosition object representing the very start of a source code file.
     */
    private CodePosition() {
        beginLine = 0;
        beginColumn = 0;
        endLine = 0;
        endColumn = 0;
    }

    @Override
    public String toString() {
        return StringUtils.join("begin line/column ", beginLine, "/", beginColumn,
                "; end line/column ", endLine, "/", endColumn);
    }

    /**
     * inputData is expected to be a Token type automatically generated by JavaCC
     * when a JavaCC grammar is evaluated into a lexer and a parser.
     *
     * @param token
     */
    public static CodePosition createFromToken(Object token) {
        try {
            int beginLine = (Integer) token.getClass().getDeclaredField("beginLine").get(token);
            int beginColumn = (Integer) token.getClass().getDeclaredField("beginColumn").get(token);
            int endLine = (Integer) token.getClass().getDeclaredField("endLine").get(token);
            int endColumn = (Integer) token.getClass().getDeclaredField("endColumn").get(token);
            return new CodePosition(beginLine, beginColumn, endLine, endColumn);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * create a CodePosition representing the very start of a source code file.
     *
     * @return
     */
    public static CodePosition createZeroPosition() {
        return new CodePosition();
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
