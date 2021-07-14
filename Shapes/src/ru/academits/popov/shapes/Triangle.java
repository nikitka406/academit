package ru.academits.popov.shapes;

public class Triangle implements Shape {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double x3;
    private final double y3;

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
        return Math.abs(area);
    }

    @Override
    public double getPerimeter() {
        double side1 = getSideLength(x1, y1, x2, y2);
        double side2 = getSideLength(x2, y2, x3, y3);
        double side3 = getSideLength(x1, y1, x3, y3);

        return side1 + side2 + side3;
    }

    public double getSideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;
        return x1 == triangle.x1 && y1 == triangle.y1 && x2 == triangle.x2 && y2 == triangle.y2
                && x3 == triangle.x3 && y3 == triangle.y3;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result += x1 + y1;
        result += x2 + y2 * 31;
        result += x3 + y3 * 31;
        return result;
    }

    @Override
    public String toString() {
        return "Triangle with (x1; y1) = " + "(" + x1 + "; " + y1 + ") " +
                "(x2; y2) = " + "(" + x2 + "; " + y2 + ") " +
                "(x3; y3) = " + "(" + x3 + "; " + y3 + ")";
    }
}
