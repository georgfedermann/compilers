package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable.SymbolTable;

/**
 * a program is a Statement followed by a Semicolon (;) and an EOF.
 * <p/>
 * a statement can always be replaced by a list of statements.
 * <p/>
 * Created by 02eex612 on 17.02.2016.
 */
public interface Program extends AstItem {
}
