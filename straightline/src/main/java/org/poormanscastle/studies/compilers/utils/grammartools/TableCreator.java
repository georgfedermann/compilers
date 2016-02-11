package org.poormanscastle.studies.compilers.utils.grammartools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * Created by georg on 09.02.16.
 */
public class TableCreator {

    private final int STATE_BEGIN = 0;
    private final int STATE_READ_TERMINAL_SYMBOLS = 1;
    private final int STATE_READ_TERMINAL_SYMBOLS_FINISHED = 2;
    private final int STATE_READ_PRODUCTIONS = 3;
    private final int STATE_READ_PRODUCTIONS_FINISHED = 4;

    private final String TERMINALS_START = "TERMINALS_START";
    private final String TERMINALS_END = "TERMINALS_END";
    private final String PRODUCTIONS_START = "PRODUCTIONS_START";
    private final String PRODUCTIONS_END = "PRODUCTIONS_END";

    public Grammar readGrammar(InputStream inputStream) throws IOException {
        int state = STATE_BEGIN;
        boolean firstProduction = true;
        Grammar grammar = new Grammar();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;

        while ((line = reader.readLine()) != null) {
            switch (state) {
                case STATE_BEGIN:
                    if (TERMINALS_START.equalsIgnoreCase(line)) {
                        state = STATE_READ_TERMINAL_SYMBOLS;
                    }
                    break;
                case STATE_READ_TERMINAL_SYMBOLS:
                    if (TERMINALS_END.equalsIgnoreCase(line)) {
                        state = STATE_READ_TERMINAL_SYMBOLS_FINISHED;
                    } else if (!StringUtils.isBlank(line)) {
                        grammar.addTerminalSymbol(new Symbol(line, true));
                    }
                    break;
                case STATE_READ_TERMINAL_SYMBOLS_FINISHED:
                    if (PRODUCTIONS_START.equalsIgnoreCase(line)) {
                        state = STATE_READ_PRODUCTIONS;
                    }
                    break;
                case STATE_READ_PRODUCTIONS:
                    if (PRODUCTIONS_END.equalsIgnoreCase(line)) {
                        state = STATE_READ_PRODUCTIONS_FINISHED;
                    } else if (!StringUtils.isBlank(line)) {
                        grammar.addProduction(new Production(line, firstProduction, grammar));
                    }
                    firstProduction = false;
                    break;
                case STATE_READ_PRODUCTIONS_FINISHED:
                    // here some checking could be done whether the grammar makes sense
                    // or something expected is missing.
                    break;
                default:
                    throw new RuntimeException("What the heck, what state is this machine in?");
            }
        }

        return grammar;
    }

    public Grammar proceed(InputStream inputStream) throws Exception {
        Grammar grammar = readGrammar(inputStream);
        grammar.identifyNullableSymbols();
        grammar.calculateStartAndFollowSets();
        return grammar;
    }

    public String createTable(Grammar grammar) {
        Velocity.setProperty(RuntimeConstants.OUTPUT_ENCODING, "UTF-8");
        Velocity.setProperty(RuntimeConstants.INPUT_ENCODING, "UTF-8");
        Velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        Velocity.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        Velocity.init();
        VelocityContext context = new VelocityContext();
        context.put("grammar", grammar);

        // create list of nonterminal symbols for usage in the table
        List<Symbol> nonterminalSymbols = new ArrayList<>();
        for (Production production : grammar.getProductions()) {
            if (!nonterminalSymbols.contains(production.getLhs())) {
                nonterminalSymbols.add(production.getLhs());
            }
        }
        context.put("nonterminalSymbols", nonterminalSymbols);

        Template template = Velocity.getTemplate("/grammartools/PredictiveParsingTable.velo");
        StringWriter stringWriter = new StringWriter();
        template.merge(context, stringWriter);
        return stringWriter.toString().replaceAll("\n", "");
    }

    public static void main(String[] args) throws Exception {
        TableCreator creator = new TableCreator();
        Grammar grammar = creator.proceed(System.in);
        System.out.print(creator.createTable(grammar));
    }

}
