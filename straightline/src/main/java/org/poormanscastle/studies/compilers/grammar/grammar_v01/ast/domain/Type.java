package org.poormanscastle.studies.compilers.grammar.grammar_v01.ast.domain;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by 02eex612 on 18.02.2016.
 */
public enum Type {

    BOOLEAN(1), INT(2), DOUBLE(4), TEXT(8), UNDEFINED(16);

    private int id;

    private final static List<Integer> compatibiltyChart = Arrays.asList(1, 2, 4, 6, 8, 9, 10, 12);

    private Type(int id) {
        this.id = id;
    }

    public static boolean areTypesCompatible(Type lhsType, Type rhsType) {
        return compatibiltyChart.contains(new Integer(lhsType.id | rhsType.id));
    }

    /**
     * when two differently typed operands occur within an expression, this method helps to determine what the
     * overall type of the parent expression has to be.
     *
     * @param lhsType
     * @param rhsType
     * @return
     */
    public static Type getInferredType(Type lhsType, Type rhsType) {
        switch (lhsType.id | rhsType.id) {
            case 1:
                return Type.BOOLEAN;
            case 2:
                return Type.INT;
            case 4:
            case 6:
                return Type.DOUBLE;
            case 12:
            case 10:
            case 9:
            case 8:
                return Type.TEXT;
            default:
                throw new RuntimeException(StringUtils.join("The two types ", lhsType, " and ", rhsType, " are incompatible."));
        }
    }

}
