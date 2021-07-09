package ru.academits.popov.shapes;

public class Triangle implements Shapes {

    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    @Override
    public double getArea() {
        double area = ((x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3)) / 2;
        return area < 0 ? area * (-1) : area;
    }

    @Override
    public double getPerimeter() {
        double xy12 = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        double xy13 = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));
        double xy23 = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));

        return xy12 + xy13 + xy23;
    }
}
