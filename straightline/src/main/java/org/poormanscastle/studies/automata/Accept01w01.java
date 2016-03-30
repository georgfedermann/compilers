package org.poormanscastle.studies.automata;

import java.util.Arrays;
import java.util.List;

/**
 * This automaton accepts Strings w in { 01x, x01 | x in {0,1}* }.
 * <p/>
 * This is a stateful automaton. After accept was called the automaton
 * can be queried why the string was accepted: startsWith01() endsWith01(),
 * startsAndEndsWith01().
 * <p/>
 * Created by 02eex612 on 30.03.2016.
 */
public class Accept01w01 {

    private List<Integer> acceptingStates = Arrays.asList(3, 6, 7, 8, 9);

    private int currentState = 1;

    public boolean accept(String inputString) {
        currentState = 1;
        for (int c = 0; c < inputString.length(); c++) {
            currentState = delta(currentState, Integer.valueOf(inputString.substring(c, c + 1)));
        }
        return acceptingStates.contains(currentState);
    }

    public boolean startsWith01() {
        return currentState == 3 || currentState == 7 || currentState == 8 || currentState == 9;
    }

    public boolean endsWith01() {
        return currentState == 3 || currentState == 6 || currentState == 9;
    }

    public boolean startsAndEndsWith01() {
        return currentState == 3 || currentState == 9;
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
                        return 5;
                    case 3:
                        return 7;
                    case 4:
                        return 5;
                    case 5:
                        return 5;
                    case 6:
                        return 5;
                    case 7:
                        return 7;
                    case 8:
                        return 7;
                    case 9:
                        return 7;
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
                        return 8;
                    case 4:
                        return 4;
                    case 5:
                        return 6;
                    case 6:
                        return 4;
                    case 7:
                        return 9;
                    case 8:
                        return 8;
                    case 9:
                        return 8;
                }
            default:
                return 0;
        }
    }

}
