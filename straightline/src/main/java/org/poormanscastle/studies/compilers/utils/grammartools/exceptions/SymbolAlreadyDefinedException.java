package org.poormanscastle.studies.compilers.utils.grammartools.exceptions;

/**
 * can be used to indicate that a symbol was already defined in a given scope and that the
 * current action cannot complete.
 * <p/>
 * Created by 02eex612 on 15.03.2016.
 */
public class SymbolAlreadyDefinedException extends CompilerException {

    public SymbolAlreadyDefinedException() {
    }

    public SymbolAlreadyDefinedException(Throwable cause) {
        super(cause);
    }

    public SymbolAlreadyDefinedException(String message) {
        super(message);
    }

    public SymbolAlreadyDefinedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SymbolAlreadyDefinedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
