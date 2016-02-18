package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

/**
 * Created by 02eex612 on 17.02.2016.
 */
public enum BinaryOperator {
    PLUS("+"), MINUS("-"), TIMES("*"), DIV("/"), XOR("xor"), AND("AND"), OR("OR"),
    LT("<"), LTE("<="), GT(">"), GTE(">="), EQ("=="), NEQ("!=");

    private String label;

    BinaryOperator(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
