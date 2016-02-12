package org.poormanscastle.studies.compilers.utils.grammartools;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * Created by georg on 09.02.16.
 */
public class LL1TableCreator implements TableCreator {

    @Override
    public Grammar preprocess(Grammar grammar) {
        grammar.identifyNullableSymbols();
        grammar.calculateStartAndFollowSets();
        return grammar;
    }

    @Override
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
        LL1TableCreator creator = new LL1TableCreator();
        Grammar grammar = new GrammarReader().readGrammar(System.in);
        creator.preprocess(grammar);
        System.out.print(creator.createTable(grammar));
    }
}
