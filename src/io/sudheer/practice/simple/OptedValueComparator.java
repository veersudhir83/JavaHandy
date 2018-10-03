package io.sudheer.practice.simple;

import java.util.Comparator;

public class OptedValueComparator implements Comparator<OptedValueDTO> {
    @Override
    public int compare(OptedValueDTO t1, OptedValueDTO t2) {
        return t1.getRank() - t2.getRank();
    }
}
