package org.poormanscastle.studies.compilers.programs.benchmark;

import edu.princeton.cs.algs4.StdIn;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by georg on 14.12.15.
 */
public class SmallSymbolTableTestClient {

    public static void main(String[] args) {
        // replace Map with the Symboltable which shall be tested.
        Map<String, Integer> symbolTable;
        symbolTable = new HashMap<>();

        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            int value = i;
            symbolTable.put(key, value);
        }

        for (String s : symbolTable.keySet()) {
            System.out.println(StringUtils.join(s, " ", symbolTable.get(s)));
        }
    }

}
