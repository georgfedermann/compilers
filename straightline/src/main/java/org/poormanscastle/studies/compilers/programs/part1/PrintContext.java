package org.poormanscastle.studies.compilers.programs.part1;

/**
 * represents print statements and can be used to hold information about them in various contexts.
 * Created by georg on 03.12.15.
 */
public class PrintContext {

    /**
     * gets incremented for each argument found for the given print statement.
     */
    private int numberOfArguments;

    public int getNumberOfArguments() {
        return numberOfArguments;
    }

    public void incrementNumberOfArguments() {
        numberOfArguments++;
    }

    /**
     * tracks how far below the given print statements args the scanner is wandering right now. this field gets
     * incremented for each hierarchy below any of the print statement's expLists arguments.
     */
    private int sourceLvl = 0;

    public int getSourceLvl() {
        return sourceLvl;
    }

    public void incrementSourceLvl() {
        sourceLvl++;
    }

    public void decrementSourceLvl() {
        sourceLvl--;
    }

    public boolean amIdirectlyBelowPrintStm() {
        return sourceLvl == 0;
    }
}
