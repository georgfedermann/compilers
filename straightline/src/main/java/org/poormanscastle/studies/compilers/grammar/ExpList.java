package org.poormanscastle.studies.compilers.grammar;

/**
 * Created by georg on 11.12.15.
 */
public interface ExpList extends GrammarItem {

    /**
     * implements type specific evaluation logic.
     *
     * @return
     */
    ValuesAndTable evaluate(ValuesAndTable vats);

}
