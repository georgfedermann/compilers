package org.poormanscastle.studies.compilers.utils.grammartools.ll1;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.poormanscastle.studies.compilers.utils.grammartools.Grammar;
import org.poormanscastle.studies.compilers.utils.grammartools.TableCreator;

/**
 * tries to create an LL(1) parser table (recursive descent parser table, predictive parser table) for the given grammar.
 *
 * Created by georg on 09.02.16.
 */
public class LL1TableCreator implements TableCreator {

    @Override
    public String createTable(Grammar grammar) {
        Velocity.setProperty(RuntimeConstants.OUTPUT_ENCODING, "UTF-8");
        Velocity.setProperty(RuntimeConstants.INPUT_ENCODING, "UTF-8");
        Velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        Velocity.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        Velocity.init();
        VelocityContext context = new VelocityContext();
        context.put("grammar", grammar);

        context.put("nonterminalSymbols", grammar.getLhsSymbols());

        Template template = Velocity.getTemplate("/grammartools/PredictiveParsingTable.velo");
        StringWriter stringWriter = new StringWriter();
        template.merge(context, stringWriter);
        return stringWriter.toString().replaceAll("\n", "");
    }
}
