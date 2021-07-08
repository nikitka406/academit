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
        System.out.printf("(%f; %f) ", from, to);
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
            // Не пересекаются
            return new Range[]{new Range(from, to)};
        } else if (to < range.to) {
            // Правый больше левого
            return new Range[]{};
        } else if (from < range.from) {
            if (to != range.to){
                // Вложенный
                return new Range[]{new Range(from, range.from), new Range(range.to, to)};
            }

            // Вложенный с верхом
            return new Range[]{new Range(from, range.from)};
        }

        // Оставшиеся
        return new Range[]{new Range(range.to, to)};
    }

    public String toString(){
        return "(" + from +", " + to + ")";
    }
}
