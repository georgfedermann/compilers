package org.poormanscastle.studies.compilers.programs.part2;

import org.poormanscastle.studies.compilers.grammar.MemoryTable;
import org.poormanscastle.studies.compilers.grammar.LinkedListMemoryTable;
import org.poormanscastle.studies.compilers.grammar.Stm;

/**
 * Created by georg on 11.12.15.
 */
public class ProgramInterpreter {

    public void interp(Stm stm) {
        MemoryTable table = new LinkedListMemoryTable();
        stm.execute(table);
    }

}
