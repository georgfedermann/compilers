package org.poormanscastle.studies.compilers.programs.part1;

import org.poormanscastle.studies.compilers.grammar.AssignStm;
import org.poormanscastle.studies.compilers.grammar.CompoundStm;
import org.poormanscastle.studies.compilers.grammar.EseqExp;
import org.poormanscastle.studies.compilers.grammar.IdExp;
import org.poormanscastle.studies.compilers.grammar.LastExpList;
import org.poormanscastle.studies.compilers.grammar.NumExp;
import org.poormanscastle.studies.compilers.grammar.OpExp;
import org.poormanscastle.studies.compilers.grammar.PairExpList;
import org.poormanscastle.studies.compilers.grammar.PrintStm;
import org.poormanscastle.studies.compilers.grammar.Stm;

/**
 * Scans programs in the simple straight-line language for print Statements and decides how many
 * arguments each print statement has to provide the max number of arguments to a print statement
 * in the given program.
 *
 * Created by georg on 03.12.15.
 */
public class ProgramScanner {


    /**
     * calculates and returns the max number of arguments for any given
     * print statement contained inside the input parameter stm.
     *
     * @param stm
     * @return the maximum number of arguments to any print statement contained within
     * the program wrapped inside the stm input parameter.
     */
    public int maxArgs(Stm stm) {
        // look at stm
        // if stm is not a print stm, iterate over its children
        // if stm is a print stm iterate over its children wich are ExpList instances and count them for good.
        // Each ExpList implementation counts for 1 here, because PairExp will have one printable expression, and
        // another ExpList, which will either be another PairExpList or a LastExpList, which will have one printable
        // child expression. voila.

        return 0;
    }

}
