package org.poormanscastle.studies.compilers.programs.benchmark;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * testing a symbol table in a way such that searches and inserts appear
 * intermixed and frequently.
 * <p>
 * Created by georg on 14.12.15.
 */
public class FrequencyCounter {

    public static void main(String[] args) throws Exception {
        int minLength = Integer.parseInt(args[0]);
        // replace Map with the SymbolTable that stands to test
        Map<String, Integer> symbolTable = new HashMap<>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minLength) {
                continue;
            }
            if (!symbolTable.containsKey(word)) {
                symbolTable.put(word, 1);
            } else {
                symbolTable.put(word, symbolTable.get(word) + 1);
            }
        }
        // find word with highest frequency count
        String max = "";
        symbolTable.put(max, 0);
        for (String word : symbolTable.keySet()) {
            if (symbolTable.get(word) > symbolTable.get(max)) {
                max = word;
            }
        }
        StdOut.println(StringUtils.join(max, ": ", symbolTable.get(max)));
    }
}
