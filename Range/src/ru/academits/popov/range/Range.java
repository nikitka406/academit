package ru.academits.popov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return this.from;
    }

    public double getTo() {
        return this.to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return Math.abs(this.to - this.from);
    }

    public boolean isInside(double numeric){
        return numeric >= this.from && numeric <= this.to;
    }
}
