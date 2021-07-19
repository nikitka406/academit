package ru.academits.popov.shapes_main;

import ru.academits.popov.shapes.*;

import java.util.Arrays;

public class Main {
    public static Shape getMaxAreaShape(Shape[] shapes) {
        if (shapes.length <= 0) {
            return null;
        }

        if (shapes.length == 1) {
            return shapes[0];
        }

        Arrays.sort(shapes, new ShapeByAreaComparator());

        return shapes[shapes.length - 1];
    }

    public static Shape getSecondPerimeterShape(Shape[] shapes) {
        if (shapes.length <= 1) {
            return null;
        }

        Arrays.sort(shapes, new ShapeByPerimeterComparator());

        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        // part 1
        Square square = new Square(5);
        System.out.println("Ширина квадрата = " + square.getWidth());
        System.out.println("Высота квадрата = " + square.getHeight());
        System.out.println("Площадь квадрата = " + square.getArea());
        System.out.println("Периметр квадрата = " + square.getPerimeter());

        Triangle triangle = new Triangle(1, 3, 2, -5, -8, 4);
        System.out.println("Ширина триугольника = " + triangle.getWidth());
        System.out.println("Высота триугольника = " + triangle.getHeight());
        System.out.println("Площадь триугольника = " + triangle.getArea());
        System.out.println("Периметр триугольника = " + triangle.getPerimeter());

        Rectangle rectangle = new Rectangle(4, 5);
        System.out.println("Ширина прямоугольника = " + rectangle.getWidth());
        System.out.println("Высота прямоугольника = " + rectangle.getHeight());
        System.out.println("Площадь прямоугольника = " + rectangle.getArea());
        System.out.println("Периметр прямоугольника = " + rectangle.getPerimeter());

        Circle circle = new Circle(4);
        System.out.println("Ширина круга = " + circle.getWidth());
        System.out.println("Высота круга = " + circle.getHeight());
        System.out.println("Площадь круга = " + circle.getArea());
        System.out.println("Периметр круга = " + circle.getPerimeter());

        // part 2
        Shape[] shapes = {
                new Circle(9),
                circle,
                square,
                new Rectangle(7, 3),
                rectangle,
                triangle,
                new Square(8)
        };

        Shape maxAreaShape = getMaxAreaShape(shapes);
        if (maxAreaShape == null) {
            System.out.println("Невозможно найти максимальную площадь");
        } else {
            System.out.println("Фигура " + maxAreaShape.getClass() + " с максимальной площадью имеет: ");
            System.out.println("Ширину = " + maxAreaShape.getWidth());
            System.out.println("Высоту = " + maxAreaShape.getHeight());
            System.out.println("Площадь = " + maxAreaShape.getArea());
            System.out.println("Периметр = " + maxAreaShape.getPerimeter());
        }

        Shape secondPerimeterShape = getSecondPerimeterShape(shapes);
        if (secondPerimeterShape == null) {
            System.out.println("Невозможно найти фигуру с вторым периметрм");
        } else {
            System.out.println("Фигура " + secondPerimeterShape.getClass() + " со вторым по величине периметром: ");
            System.out.println("Ширину = " + secondPerimeterShape.getWidth());
            System.out.println("Высоту = " + secondPerimeterShape.getHeight());
            System.out.println("Площадь = " + secondPerimeterShape.getArea());
            System.out.println("Периметр = " + secondPerimeterShape.getPerimeter());
        }

        // part3
        System.out.println(square.equals(square));
        System.out.println(square.hashCode());
        System.out.println(triangle.hashCode());
        System.out.println(circle.hashCode());
        System.out.println(rectangle.hashCode());

        System.out.println(square);
        System.out.println(triangle);
        System.out.println(circle);
        System.out.println(rectangle);
    }
}
