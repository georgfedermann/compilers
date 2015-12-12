package org.poormanscastle.studies.compilers.programs.part1;

import org.poormanscastle.studies.compilers.grammar.Stm;

/**
 * Scans programs in the simple straight-line language for print Statements and decides how many
 * arguments each print statement has to provide the max number of arguments to a print statement
 * in the given program.
 * <p>
 * Created by georg on 03.12.15.
 */
public class ProgramScanner {

    /**
     * counts and returns the max number of arguments for any given print statement contained within
     * the given stm.
     *
     * @param stm
     * @return the maximum number of arguments to any print statement contained within
     * the program wrapped inside the stm input parameter.
     */
    public int maxArgs(Stm stm) {
        PrintStmArgCounterVisitor visitor = new PrintStmArgCounterVisitor();
        if (stm.handleProceedWith(visitor)) {
            stm.accept(visitor);
        }
        int maxArgNumber = 0;
        for (PrintContext printContext : visitor.getPrintContexts()) {
            maxArgNumber = Math.max(maxArgNumber, printContext.getNumberOfArguments());
        }
        return maxArgNumber;
    }

}
