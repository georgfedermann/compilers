package org.poormanscastle.studies.compilers.programs.part2;

import org.poormanscastle.studies.compilers.grammar.Stm;
import org.poormanscastle.studies.compilers.grammar.Table;

/**
 * Created by georg on 11.12.15.
 */
public class ProgramInterpreter {

    public void interp(Stm stm) {
        Table table = new Table("zero", 0, null);
        stm.execute(table);
    }

}
