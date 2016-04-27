package org.poormanscastle.studies.automata;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * This automaton accepts strings { x01y | x,y in {0,1}* }
 * <p>
 * Created by georg on 26.03.16.
 */
public class Accept01 {

    private List<Integer> acceptingStates = Arrays.asList(Integer.valueOf(3));

    public boolean accept(String probe) {
        int currentState = 1;
        for (int c = 0; c < probe.length(); c++) {
            currentState = delta(currentState, Integer.valueOf(probe.substring(c, c + 1)));
        }
        return acceptingStates.contains(currentState);
    }


    private int delta(int currentState, int input) {
        switch (currentState) {
            case 0:
                return 0;
            case 1:
                switch (input) {
                    case 0:
                        return 2;
                    case 1:
                        return 1;
                }
                break;
            case 2:
                switch (input) {
                    case 0:
                        return 2;
                    case 1:
                        return 3;
                }
                break;
            case 3:
                switch (input) {
                    case 0:
                    case 1:
                        return 3;
                }
        }
        throw new RuntimeException(StringUtils.join("Illegal Input: ", input));
    }

}
