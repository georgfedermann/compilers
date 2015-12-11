package org.poormanscastle.studies.compilers.grammar;

/**
 * Created by georg on 11.12.15.
 */
public interface Exp extends GrammarItem {

    /**
     * implements type specific evaluation logic.
     *
     * @return
     */
    ValueAndTable evaluate(Table table);

}
