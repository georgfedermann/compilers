package org.poormanscastle.studies.compilers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Program;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.parser.javacc.OhAstParser;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.semantic.SymbolTableCreatorVisitor;
import org.poormanscastle.studies.compilers.utils.grammartools.ast.symboltable.SymbolTable;
import org.poormanscastle.studies.compilers.utils.grammartools.exceptions.CompilerException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

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
    public static Program loadTestProgram(String fileName, boolean validate) throws Exception {
        return loadProgram("/grammar_v01/", fileName, validate);
    }

    /**
     * programs are expected to reside in files in resources/grammar_v01/
     *
     * @param fileName
     * @return
     */
    public static Program loadProgram(String path, String fileName, boolean validate) throws Exception {
        checkNotNull(path);
        checkArgument(!StringUtils.isEmpty(fileName));
        Program program = new OhAstParser(TestUtils.getTestdataAsInputStream(StringUtils.join(
                path.endsWith("/") ? path : StringUtils.join(path, "/"), fileName))).P();
        checkNotNull(program);
        if (validate) {
            SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
            program.accept(symbolTableCreator);
            if (!symbolTableCreator.isAstValid()) {
                throw new RuntimeException();
            }
        }
        return program;
    }


    public static Program loadOhProgram(String fileName) throws Exception {
        return TestUtils.loadProgram("/oh/", fileName.endsWith(".oh") ? fileName : StringUtils.join(fileName, ".oh"), true);
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
        checkState(symbolTableCreator.isAstValid());
        return symbolTable;
    }

}
