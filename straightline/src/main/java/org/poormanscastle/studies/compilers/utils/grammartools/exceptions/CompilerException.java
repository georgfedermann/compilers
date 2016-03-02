package org.poormanscastle.studies.compilers.utils.grammartools.exceptions;

/**
 * this exception shall be used to indicate errors that occurred during any of the phases of a compiler.
 * Created by 02eex612 on 01.03.2016.
 */
public class CompilerException extends RuntimeException {

    public CompilerException() {
    }

    public CompilerException(Throwable cause) {
        super(cause);
    }

    public CompilerException(String message) {
        super(message);
    }

    public CompilerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompilerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
