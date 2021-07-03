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

    public void print() {
        System.out.printf("[%f;%f]\n", from, to);
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        if (to <= range.getFrom() || range.getTo() <= from) {
            return null;
        }

        double minTo = Math.min(to, range.getTo());
        double maxFrom = Math.max(from, range.getFrom());

        return new Range(maxFrom, minTo);
    }

    public Range[] getMerger(Range range) {
        Range[] ranges;
        if (to <= range.getFrom() || range.getTo() <= from) {
            ranges = new Range[2];
            ranges[0] = new Range(from, to);
            ranges[1] = range;
            return ranges;
        }

        double maxTo = Math.max(to, range.getTo());
        double minFrom = Math.min(from, range.getFrom());

        ranges = new Range[1];
        ranges[0] = new Range(minFrom, maxTo);

        return ranges;
    }

    public Range[] getDifference(Range range) {
        Range[] ranges;
        if (to <= range.getFrom() || range.getTo() <= from) {
            ranges = new Range[1];
            ranges[0] = null;

            return ranges;
        }

        if (from < range.getFrom()) {
            if (range.getTo() < to) {
                ranges = new Range[2];
                ranges[0] = new Range(from, range.getFrom());
                ranges[1] = new Range(range.getTo(), to);
            } else {
                ranges = new Range[1];
                ranges[0] = new Range(range.getFrom(), to);
            }
        } else {
            ranges = new Range[1];
            ranges[0] = new Range(from, range.getTo());
        }

        return ranges;
    }
}
