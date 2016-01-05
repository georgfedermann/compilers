package org.poormanscastle.studies.compilers.ch01;

import org.apache.commons.lang3.StringUtils;

/**
 * a print statement prints all of the arguemnts that are given in a comma separated list.
 * Created by georg on 02.12.15.
 */
public class PrintStm extends AbstractStm {
    public ExpList exps;

    public PrintStm(ExpList e) {
        exps = e;
    }

    @Override
    public void accept(GrammarItemVisitor visitor) {
        visitor.visitPrintStm(this);
        if (exps.handleProceedWith(visitor)) {
            exps.accept(visitor);
        }
        visitor.leavePrintStm(this);
    }

    @Override
    public boolean handleProceedWith(GrammarItemVisitor visitor) {
        return visitor.proceedWithPrintStm(this);
    }

    @Override
    public void execute(MemoryTable memoryTable) {
        Values vats = new Values();
        exps.evaluate(vats, memoryTable);
        for (Integer integer : vats.getValues()) {
            System.out.print(StringUtils.join(integer, " "));
        }
    }
}
