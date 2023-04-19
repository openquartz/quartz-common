package com.openquartz.common.util.lang;

import java.util.Objects;

/**
 * Range
 *
 * @author svnee
 **/
public class Range<K> {

    private K start;
    private K end;

    public Range(K start, K end) {

        if (Objects.isNull(start) && Objects.isNull(end)) {
            throw new IllegalArgumentException("Invalid range!");
        }

        this.start = start;
        this.end = end;
    }

    public K getStart() {
        return start;
    }

    public K getEnd() {
        return end;
    }

    public boolean startIfAbsent(K start) {

        if (getStart() == null) {
            this.start = start;
            return true;
        }
        return false;
    }

    public void start(K start) {

        this.start = start;
    }

    public void end(K end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Range<?> range = (Range<?>) o;
        return Objects.equals(start, range.start) && Objects.equals(end, range.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
