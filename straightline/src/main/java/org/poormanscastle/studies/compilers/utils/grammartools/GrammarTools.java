package org.poormanscastle.studies.compilers.utils.grammartools;

import java.io.IOException;

import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain.Program;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.interpreter.SmallTimeInterpreter;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.parser.javacc.OhAstParser;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.parser.javacc.ParseException;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.prettyprint.PrettyPrintVisitor;
import org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.semantic.SymbolTableCreatorVisitor;

/**
 * GrammarTools is meant to be used as cmd line tool to work with grammars.
 * <p>
 * Created by 02eex612 on 17.02.2016.
 */
public class GrammarTools {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            printHelp();
            return;
        }

        int counter = 0;
        do {
            String arg = args[counter++];
            if ("v".equals(arg)) {
                GrammarTools.printVersion();
            }
            if ("h".equals(arg)) {
                GrammarTools.printHelp();
            }
            if ("c".equals(arg)) {
                if (!(counter < (args.length))) {
                    System.out.println("Please define what command has to be executed.");
                    GrammarTools.printHelp();
                    return;
                }
                System.out.print(createTableVisualization(args[counter++]));
            }
            if ("a".equals(arg)) {
                String visualization = GrammarTools.createAstVisualization();
                System.out.println(visualization);
            }
            if ("e".equals(arg)) {
                GrammarTools.executeProgram();
            }
        } while (counter < args.length);
    }

    private static void executeProgram() throws IOException, ParseException {
        Program program = new OhAstParser(System.in).P();
        SymbolTableCreatorVisitor symbolTableCreator = new SymbolTableCreatorVisitor();
        program.accept(symbolTableCreator);
        if (symbolTableCreator.isAstValid()) {
            program.accept(new SmallTimeInterpreter());
        }
        System.out.println();
    }

    private static String createAstVisualization() throws IOException, ParseException {
        Program program = new OhAstParser(System.in).P();
        PrettyPrintVisitor prettyPrinter = new PrettyPrintVisitor();
        program.accept(prettyPrinter);
        return prettyPrinter.serialize();
    }

    private static String createTableVisualization(String grammarFlavor) throws IOException {
        GrammarFlavor flavor = GrammarFlavor.valueOf(grammarFlavor);
        return TableCreator.getTableCreator(flavor).createTable(GrammarReader.readGrammar(
                System.in, Grammar.createGrammar(flavor)));
    }

    private static void printHelp() {
        printVersion();
        System.out.println("Usage: grammarTools ");
        System.out.println("  Default action is to read data from standard input stream, process it, and write data to standard output stream.");
        System.out.println("  Data can be written to stdin and stdout can be redirected as seems fit.");
        System.out.println();
        System.out.println("  -h  print this help.");
        System.out.println("  -v  print version information.");
        System.out.println("  -c  create parser table visualization. One of the following options is required:");
        System.out.println("      LL1: create LL(X) parser tree visualization.");
        System.out.println("      LR0: create LR(0) parser tree state engine visualization.");
        System.out.println("  -a  create AST diagram for Oh program in dot format. Program data is read from the std input.");
        System.out.println("  -e  execute Oh program.");
    }

    private static void printVersion() {
        System.out.println("GrammarTools v0.1 of 2016-02-17, by Poor Man's Castle.");
    }


}
