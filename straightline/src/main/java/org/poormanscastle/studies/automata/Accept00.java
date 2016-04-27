package org.poormanscastle.studies.automata;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * This automaton accepts Strings ending in 00. i.e. s in { x00 | x in {0,1}* }
 * Created by georg on 28.03.16.
 */
public class Accept00 {

    private List<Integer> acceptingStates = Arrays.asList(3);

    public boolean accept(String probe) {
        int currentState = 1;
        for (int c = 0; c < probe.length(); c++) {
            currentState = delta(currentState, Integer.valueOf(probe.substring(c, c + 1)));
        }
        return acceptingStates.contains(currentState);
    }

    private int delta(int state, int input) {
        switch (state) {
            case 0:
                return 0;
            case 1:
                switch (input) {
                    case 0:
                        return 2;
                    case 1:
                        return 1;
                }
            case 2:
                switch (input) {
                    case 0:
                        return 3;
                    case 1:
                        return 1;
                }
            case 3:
                switch (input) {
                    case 0:
                        return 3;
                    case 1:
                        return 1;
                }
        }
        throw new RuntimeException(StringUtils.join("Illegal Input: ", input));
    }

}
