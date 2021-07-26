package ru.academits.popov.vector_main;

import ru.academits.popov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(5);
        System.out.println(vector1);

        Vector vector2 = new Vector(new double[]{1, 3, 4, 6});
        System.out.println(vector2);

        Vector vector3 = new Vector(vector2);
        System.out.println(vector3);

        Vector vector4 = new Vector(10, new double[]{3, 4, 5, 6, 7, 8});
        System.out.println(vector4);

        System.out.println("Размерность вектора4 = " + vector4.getSize());
        System.out.println("Длина вектора4 = " + vector4.getLength());
        vector4.revers();
        System.out.println("Разворот вектора4 = " + vector4);
        System.out.println("Длина вектора4 = " + vector4.hashCode());
        System.out.println("Равенство векторов 3 и 4 " + vector4.equals(vector3));
        System.out.println("Равенство векторов 4 и 4 " + vector4.equals(vector4));
        vector4.add(vector3);
        System.out.println("Сумма векторов 4 и 3 " + vector4);
        vector3.add(vector4);
        System.out.println("Сумма векторов 3 и 4 " + vector3);
        vector4.subtract(vector3);
        System.out.println("Разница векторов 4 и 3 " + vector4);
        vector3.subtract(vector4);
        System.out.println("Разница векторов 3 и 4 " + vector3);
        System.out.println("Статическая сумма векторов 4 и 3 " + Vector.getSum(vector4, vector3));
        System.out.println("Статическая сумма векторов 3 и 4 " + Vector.getSum(vector3, vector4));
        System.out.println("Статическая разница векторов 4 и 3 " + Vector.getDifference(vector4, vector3));
        System.out.println("Статическая разница векторов 3 и 4 " + Vector.getDifference(vector3, vector4));
        vector4.multiplyByScalar(5);
        System.out.println("Умножение вектора 4 на 5 " + vector4);
        System.out.println("Статическое умножение вектора 4 на вектор 3 " + Vector.getScalarProduct(vector4, vector3));
    }
}
