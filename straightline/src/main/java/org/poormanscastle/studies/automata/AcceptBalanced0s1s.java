package org.poormanscastle.studies.automata;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 02eex612 on 29.03.2016.
 */
public class AcceptBalanced0s1s {

    private List<Integer> acceptingStates = Arrays.asList(Integer.valueOf(1));

    public boolean accept(String probe) {
        int currentState = 1;
        for (int c = 0; c < probe.length(); c++) {
            currentState = delta(currentState, Integer.valueOf(probe.substring(c, c + 1)));
        }
        return acceptingStates.contains(currentState);
    }

    private int delta(int state, int input) {
        switch (input) {
            case 0:
                switch (state) {
                    case 0:
                        return 0;
                    case 1:
                        return 2;
                    case 2:
                        return 1;
                    case 3:
                        return 4;
                    case 4:
                        return 3;
                }
            case 1:
                switch (state) {
                    case 0:
                        return 0;
                    case 1:
                        return 4;
                    case 2:
                        return 3;
                    case 3:
                        return 2;
                    case 4:
                        return 1;
                }
            default:
                return 0;
        }
    }

}
