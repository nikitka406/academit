package ru.academits.popov.shapes_main;

import ru.academits.popov.shapes.Shape;

import java.util.Comparator;

public class ShapeByAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape s1, Shape s2) {
        return Double.compare(s1.getArea(), s2.getArea());
    }
}
