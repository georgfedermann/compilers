package org.poormanscastle.studies.compilers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain.Program;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.parser.javacc.OhAstParser;
import org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.semantic.SymbolTableCreatorVisitor;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable.SymbolTable;
import org.poormanscastle.studies.compilers.utils.grammartools.exceptions.CompilerException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by georg on 21.12.15.
 */
public class TestUtils {

    public static InputStream getTestdataAsInputStream(String path) throws Exception {
        return new ByteArrayInputStream(IOUtils.toByteArray(TestUtils.class.getResourceAsStream(path)));
    }

    /**
     * programs are expected to reside in files in resources/grammar_v01/
     *
     * @param fileName
     * @return
     */
    public static Program loadProgram(String fileName) throws Exception {
        Program program = new OhAstParser(TestUtils.getTestdataAsInputStream(StringUtils.join("/grammar_v01/", fileName))).P();
        checkNotNull(program);
        return program;
    }

    public static SymbolTable getSymbolTableForProgram(Program program) {
        SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
        if (program.handleProceedWith(symbolTableCreator)) {
            program.accept(symbolTableCreator);
        } else {
            throw new CompilerException("SymbolTableCreatorVisitor was not accepted by AST.");
        }
        SymbolTable symbolTable = symbolTableCreator.getSymbolTable();
        checkNotNull(symbolTable);
        return symbolTable;
    }

}
