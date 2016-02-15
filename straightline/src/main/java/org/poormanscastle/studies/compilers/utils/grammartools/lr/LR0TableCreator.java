package org.poormanscastle.studies.compilers.utils.grammartools.lr;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.utils.grammartools.Grammar;
import org.poormanscastle.studies.compilers.utils.grammartools.TableCreator;

/**
 * tries to create a visual representation of an LR(0) parser table for the given grammar.
 * <p/>
 * Created by 02eex612 on 15.02.2016.
 */
public class LR0TableCreator implements TableCreator {

    /**
     * uses graphviz dot to craete a graph for the griven grammar's state machine.
     * <p/>
     * the states will be the nodes of the graph. the edges between states will be the edges of the graph.
     *
     * @param grammarArg
     * @return
     */
    @Override
    public String createTable(Grammar grammarArg) {
        LR0Grammar grammar = (LR0Grammar) grammarArg;

        StringBuilder output = new StringBuilder("digraph LR0StateMachine {\n");

        // create the graph nodes from the grammar's states.
        for (LRState state : grammar.getStates()) {
            output.append(createDotNodeFromLRState(state));
        }

        output.append("\nnode [shape = rectangle]\n");

        for (LREdge edge : grammar.getEdges()) {
            output.append(createDotEdgeFromLREdge(edge));
        }

        output.append("}\n");

        return output.toString();
    }

    private String createDotEdgeFromLREdge(LREdge edge) {
        return StringUtils.join("i", String.valueOf(edge.getSourceState().getId()), " -> i",
                String.valueOf(edge.getTargetState().getId()), " [ label=\"", edge.getSymbol().getId(), "\" ];\n");
    }

    String createDotNodeFromLRState(LRState state) {
        return StringUtils.join("i", state.getId(), " [label=\"", state.getStateAsText(), "\"];\n");
    }

}
