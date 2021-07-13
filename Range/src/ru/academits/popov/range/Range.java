package ru.academits.popov.range;

import java.util.Objects;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number > from && number < to;
    }

    public Range getIntersection(Range range) {
        if (to <= range.from || range.to <= from) {
            return null;
        }

        double minTo = Math.min(to, range.to);
        double maxFrom = Math.max(from, range.from);

        return new Range(maxFrom, minTo);
    }

    public Range[] getUnion(Range range) {
        if (to < range.from || range.to < from) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        double maxTo = Math.max(to, range.to);
        double minFrom = Math.min(from, range.from);

        return new Range[]{new Range(minFrom, maxTo)};
    }

    public Range[] getDifference(Range range) {
        Range rangeIntersection = this.getIntersection(range);

        if (rangeIntersection == null) {
            return new Range[]{new Range(from, to)};
        }

        if (rangeIntersection.equals(this)) {
            return new Range[]{};
        }

        if (rangeIntersection.from == from) {
            return new Range[]{new Range(rangeIntersection.to, to)};
        }

        if (rangeIntersection.to == to) {
            return new Range[]{new Range(from, rangeIntersection.from)};
        }

        return new Range[]{new Range(from, rangeIntersection.from), new Range(rangeIntersection.to, to)};
    }

    @Override
    public String toString() {
        return "(" + from + "; " + to + ")";
    }

    @Override
    public boolean equals(Object o) {
        Range range = (Range) o;
        if (from == range.from && to == range.to) {
            return true;
        }

        return false;
    }
}
