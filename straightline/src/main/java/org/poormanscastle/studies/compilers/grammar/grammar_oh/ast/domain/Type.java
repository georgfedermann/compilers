package org.poormanscastle.studies.compilers.grammar.grammar_oh.ast.domain;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.utils.grammartools.exceptions.CompilerException;

/**
 * Created by 02eex612 on 18.02.2016.
 */
public enum Type {

    BOOLEAN(1), INT(2), DOUBLE(4), TEXT(8), UNDEFINED(16);

    private int id;

    private final static List<Integer> compatibiltyChart = Arrays.asList(1, 2, 4, 6, 8, 9, 10, 12);

    Type(int id) {
        this.id = id;
    }

    public static boolean areTypesCompatible(Type lhsType, Type rhsType) {
        return compatibiltyChart.contains(new Integer(lhsType.id | rhsType.id));
    }

    /**
     * this method helps to find out, whether the assignment in an AssignmentStatement in a DeclarationStatement
     * is valid for the rhs expression and the type of the lhs identifier.
     *
     * @param lhsType the type of the lhs identifier
     * @param rhsType the type of the value of the rhs expression.
     * @return true if you are clear to go ahead with the assignment, false otherwise.
     */
    public static boolean isRhsAssignableToLhs(Type lhsType, Type rhsType) {
        int state = queryAssignabilityMatrix(1, lhsType);
        state = queryAssignabilityMatrix(state, rhsType);
        return queryAssignabilityResolver(state);
    }

    private static boolean queryAssignabilityResolver(int state) {
        return Arrays.asList(3, 5, 7, 8, 10).contains(state);
    }

    /**
     * queries the type transition matrix for the next state. the matrix holds the information
     *
     * @param state
     * @param type
     * @return
     */
    private static int queryAssignabilityMatrix(int state, Type type) {
        switch (state) {
            case 0:
                return 0;
            case 1:
                switch (type) {
                    case BOOLEAN:
                        return 2;
                    case INT:
                        return 4;
                    case DOUBLE:
                        return 6;
                    case TEXT:
                        return 9;
                    default:
                        return 0;
                }
            case 2:
                switch (type) {
                    case BOOLEAN:
                        return 3;
                    default:
                        return 0;
                }
            case 4:
                switch (type) {
                    case INT:
                        return 5;
                    default:
                        return 0;
                }
            case 6:
                switch (type) {
                    case INT:
                        return 7;
                    case DOUBLE:
                        return 8;
                    default:
                        return 0;
                }
            case 9:
                switch (type) {
                    case TEXT:
                        return 10;
                    default:
                        return 0;
                }
            default:
                return 0;
        }
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
                throw new CompilerException(StringUtils.join("The two types ", lhsType, " and ", rhsType, " are incompatible."));
        }
    }

}
