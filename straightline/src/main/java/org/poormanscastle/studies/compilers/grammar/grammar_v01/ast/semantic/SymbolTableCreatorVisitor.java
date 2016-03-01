package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.semantic;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.AstItemVisitorAdapter;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.Block;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.DeclarationStatement;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.LastStatementList;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.PairStatementList;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.ProgramImpl;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable.SymbolTable;
import org.poormanscastle.studies.compilers.utils.grammartools.exceptions.CompilerException;

/**
 * the statement below is actually not right:
 * Each new identifier declaration (variable, function name, what ever) creates a new environment.
 * A variable can only be used after it's been declared. Thus, also within a block or even within a dialect
 * that supports no blocks at all, there are multiple environments or else the semantic analysis could not
 * clearly decide if a variable is used before it was declared.
 * <p/>
 * this visitor has to create all entries for the symbol table. therefore it needs to visit
 * all nodes which can create new identifiers, which are declaration statements in language v0.1
 * <p/>
 * The checking of AssignmentStatements and OperatorExpressions etc. for correctness is done
 * by the next visitor, which uses the symbol table created by this visitor.
 * <p/>
 * Created by 02eex612 on 19.02.2016.
 */
public class SymbolTableCreatorVisitor extends AstItemVisitorAdapter {

    private SymbolTable symbolTable = new SymbolTable();

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    @Override
    public void visitDeclarationStatement(DeclarationStatement declarationStatement) {
        try {
            symbolTable.addSymbol(declarationStatement.getId(), declarationStatement.getType().name());
        } catch (CompilerException e) {
            System.err.print(StringUtils.join("Error at ", declarationStatement.getCodePosition(),
                    ": variable ", declarationStatement.getId(), " was already declared in this scope.\n"));
            invalidateAst();
        }
    }

    @Override
    public boolean proceedWithDeclarationStatement(DeclarationStatement declarationStatement) {
        return true;
    }

    @Override
    public boolean proceedWithProgramImpl(ProgramImpl program) {
        return true;
    }

    @Override
    public boolean proceedWithPairStatementList(PairStatementList pairStatementList) {
        return true;
    }


    @Override
    public boolean proceedWithLastStatementList(LastStatementList lastStatementList) {
        return true;
    }

    @Override
    public boolean proceedWithBlock(Block block) {
        return true;
    }

    @Override
    public void leaveBlock(Block block) {
        symbolTable.endScope();
    }

    @Override
    public void visitBlock(Block block) {
        symbolTable.newScope();
    }
}
