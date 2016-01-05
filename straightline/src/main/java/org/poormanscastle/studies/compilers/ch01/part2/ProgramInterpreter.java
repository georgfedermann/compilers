package org.poormanscastle.studies.compilers.ch01.part2;

import org.poormanscastle.studies.compilers.ch01.MemoryTable;
import org.poormanscastle.studies.compilers.ch01.LinkedListMemoryTable;
import org.poormanscastle.studies.compilers.ch01.Stm;

/**
 * Created by georg on 11.12.15.
 */
public class ProgramInterpreter {

    public void interp(Stm stm) {
        MemoryTable table = new LinkedListMemoryTable();
        stm.execute(table);
    }

}
