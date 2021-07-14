package ru.academits.popov.range;

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
        return number >= from && number <= to;
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
        if (to <= range.from || range.to <= from) {
            return new Range[]{new Range(from, to)};
        }

        double minTo = Math.min(to, range.to);
        double maxFrom = Math.max(from, range.from);

        if (maxFrom == from && minTo == to) {
            return new Range[]{};
        }

        if (maxFrom == from) {
            return new Range[]{new Range(minTo, to)};
        }

        if (minTo == to) {
            return new Range[]{new Range(from, maxFrom)};
        }

        return new Range[]{new Range(from, maxFrom), new Range(minTo, to)};
    }

    @Override
    public String toString() {
        return "(" + from + "; " + to + ")";
    }
}
