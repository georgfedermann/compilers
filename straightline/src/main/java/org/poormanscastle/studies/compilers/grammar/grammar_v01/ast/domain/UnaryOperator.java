package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public enum UnaryOperator {

    NOT("!");

    private String label;

    UnaryOperator(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
