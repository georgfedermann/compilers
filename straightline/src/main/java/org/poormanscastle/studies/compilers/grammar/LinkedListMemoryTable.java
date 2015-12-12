package org.poormanscastle.studies.compilers.grammar;

import static com.google.common.base.Preconditions.checkArgument;

import org.apache.commons.lang3.StringUtils;

/**
 * memory table representation, mapping identifiers to integer values. this really is a simple version of a linked list.
 *
 * Created by georg on 11.12.15.
 */
public class LinkedListMemoryTable implements MemoryTable {

    private LinkedListMemoryTableItem head;

    public LinkedListMemoryTable() {
    }

    @Override
    public void put(String id, int value) {
        checkArgument(id != null);
        head = new LinkedListMemoryTableItem(id, value, head);
    }

    @Override
    public int lookup(String id) {
        checkArgument(id != null);
        LinkedListMemoryTableItem probe = head;
        while (probe != null) {
            if (probe.id != null && probe.id.equals(id)) {
                return probe.value;
            }
            probe = probe.next;
        }
        throw new IllegalArgumentException(StringUtils.join("No variable ", id, " found in memory."));
    }

    private class LinkedListMemoryTableItem {

        /**
         * variable name
         */
        private String id;
        /**
         * variable value
         */
        private int value;
        /**
         * next item in the memory abstraction
         */
        private LinkedListMemoryTableItem next;

        public LinkedListMemoryTableItem(String id, int value, LinkedListMemoryTableItem next) {
            this.id = id;
            this.value = value;
            this.next = next;
        }
    }
}
