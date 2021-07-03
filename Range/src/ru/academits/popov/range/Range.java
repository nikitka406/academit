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

    public boolean isInside(double number){
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range1, Range Range){
        if (range1.getTo() <= Range.getFrom() || Range.getTo() <= range1.getFrom()){
            return null;
        }

        double minTo = Math.min(range1.getTo(), Range.getTo());
        double maxFrom = Math.max(range1.getFrom(), Range.getFrom());

        return new Range(maxFrom, minTo);
    }

    public Range[] getMerger(Range range1, Range Range){
        Range[] ranges;
        if (range1.getTo() <= Range.getFrom() || Range.getTo() <= range1.getFrom()){
            ranges = new Range[2];
            ranges[0] = range1;
            ranges[1] = Range;
            return ranges;
        }

        double maxTo = Math.max(range1.getTo(), Range.getTo());
        double minFrom = Math.min(range1.getFrom(), Range.getFrom());

        ranges = new Range[1];
        ranges[0] = new Range(minFrom, maxTo);

        return ranges;
    }
}
