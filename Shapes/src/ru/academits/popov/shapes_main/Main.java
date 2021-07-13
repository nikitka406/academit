package ru.academits.popov.shapes_main;

import ru.academits.popov.shapes.*;

public class Main {
//    static Shapes getShapeMaxArea(Shapes[] shapes) {
//        for(int i =0; i< shapes.length; ++i){
//            shapes[i]
//        }
//    }

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
        Shapes[] shapes = new Shapes[]{new Circle(9), circle, square, new Rectangle(7, 3), rectangle, triangle, new Square(8)};
    }
}
