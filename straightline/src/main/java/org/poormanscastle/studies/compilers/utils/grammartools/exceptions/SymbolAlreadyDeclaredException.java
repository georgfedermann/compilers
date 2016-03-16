package org.poormanscastle.studies.compilers.utils.grammartools.exceptions;

/**
 * can be used to indicate that a symbol was already declared in a given scope and that the
 * current action cannot complete.
 * <p/>
 * Created by 02eex612 on 15.03.2016.
 */
public class SymbolAlreadyDeclaredException extends CompilerException {

    public SymbolAlreadyDeclaredException() {
    }

    public SymbolAlreadyDeclaredException(Throwable cause) {
        super(cause);
    }

    public SymbolAlreadyDeclaredException(String message) {
        super(message);
    }

    public SymbolAlreadyDeclaredException(String message, Throwable cause) {
        super(message, cause);
    }

    public SymbolAlreadyDeclaredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
