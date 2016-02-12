package org.poormanscastle.studies.compilers.utils.grammartools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.poormanscastle.studies.compilers.utils.grammartools.ll1.LL1Grammar;

/**
 * reads an input stream and tries to recognize grammars in the character patterns.
 * Created by 02eex612 on 12.02.2016.
 */
public class GrammarReader {

    private final int STATE_BEGIN = 0;
    private final int STATE_READ_TERMINAL_SYMBOLS = 1;
    private final int STATE_READ_TERMINAL_SYMBOLS_FINISHED = 2;
    private final int STATE_READ_PRODUCTIONS = 3;
    private final int STATE_READ_PRODUCTIONS_FINISHED = 4;

    private final String TERMINALS_START = "TERMINALS_START";
    private final String TERMINALS_END = "TERMINALS_END";
    private final String PRODUCTIONS_START = "PRODUCTIONS_START";
    private final String PRODUCTIONS_END = "PRODUCTIONS_END";

    public LL1Grammar readGrammar(InputStream inputStream) throws IOException {
        int state = STATE_BEGIN;
        boolean firstProduction = true;
        LL1Grammar grammar = new LL1Grammar();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            switch (state) {
                case STATE_BEGIN:
                    if (TERMINALS_START.equalsIgnoreCase(line)) {
                        state = STATE_READ_TERMINAL_SYMBOLS;
                    }
                    break;
                case STATE_READ_TERMINAL_SYMBOLS:
                    if (TERMINALS_END.equalsIgnoreCase(line)) {
                        state = STATE_READ_TERMINAL_SYMBOLS_FINISHED;
                    } else if (!StringUtils.isBlank(line)) {
                        grammar.addTerminalSymbol(new Symbol(line, true));
                    }
                    break;
                case STATE_READ_TERMINAL_SYMBOLS_FINISHED:
                    if (PRODUCTIONS_START.equalsIgnoreCase(line)) {
                        state = STATE_READ_PRODUCTIONS;
                    }
                    break;
                case STATE_READ_PRODUCTIONS:
                    if (PRODUCTIONS_END.equalsIgnoreCase(line)) {
                        state = STATE_READ_PRODUCTIONS_FINISHED;
                    } else if (!StringUtils.isBlank(line)) {
                        grammar.addProduction(new Production(line, firstProduction, grammar));
                    }
                    firstProduction = false;
                    break;
                case STATE_READ_PRODUCTIONS_FINISHED:
                    // here some checking could be done whether the grammar makes sense
                    // or something expected is missing.
                    break;
                default:
                    throw new RuntimeException("What the heck, what state is this machine in?");
            }
        }
        return grammar;
    }
}
