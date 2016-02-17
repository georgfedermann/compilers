package org.poormanscastle.studies.compilers.utils.grammartools;

import java.io.IOException;

/**
 * GrammarTools is meant to be used as cmd line tool to work with grammars.
 * <p/>
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
                printVersion();
            }
            if ("h".equals(arg)) {
                printHelp();
            }
            if ("c".equals(arg)) {
                if (!(counter < (args.length))) {
                    System.out.println("Please define what command has to be executed.");
                    printHelp();
                    return;
                }
                System.out.print(createTableVisualization(args[counter++]));
            }
        } while (counter < args.length);
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
        System.out.println("      LL1: create LL(0) parser tree visualization.");
        System.out.println("      LR0: create LR(0) parser tree state engine visualization.");
    }

    private static void printVersion() {
        System.out.println("GrammarTools v0.1 of 2016-02-17, by Poor Man's Castle.");
    }


}
