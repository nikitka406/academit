package ru.academits.popov.vector_main;

import ru.academits.popov.vector.Vector;

public class Main {
    static public Vector getSum(Vector vector1, Vector vector2) {
        int length = Math.max(vector1.getSize(), vector2.getSize());
        Vector result = new Vector(length);

        for (int i = 0; i < length; ++i) {
            if (vector1.getSize() == length) {
                if (i < vector2.getSize()) {
                    result.components[i] = vector1.components[i] + vector2.components[i];
                } else {
                    result.components[i] = vector1.components[i] + 0;
                }
            } else {
                if (i < vector1.getSize()) {
                    result.components[i] = vector1.components[i] + vector2.components[i];
                } else {
                    result.components[i] = 0 + vector2.components[i];
                }
            }
        }

        return result;
    }

    static public Vector getDifference(Vector vector1, Vector vector2) {
        int length = Math.max(vector1.getSize(), vector2.getSize());
        Vector result = new Vector(length);

        for (int i = 0; i < length; ++i) {
            if (vector1.getSize() == length) {
                if (i < vector2.getSize()) {
                    result.components[i] = vector1.components[i] - vector2.components[i];
                } else {
                    result.components[i] = vector1.components[i] - 0;
                }
            } else {
                if (i < vector1.getSize()) {
                    result.components[i] = vector1.components[i] - vector2.components[i];
                } else {
                    result.components[i] = 0 - vector2.components[i];
                }
            }
        }

        return result;
    }

    static public Vector getScalarMultiplication(Vector vector, double number) {
        Vector result = new Vector(vector.getSize());

        for (int i = 0; i < vector.getSize(); ++i) {
            result.components[i] = vector.components[i] * number;
        }

        return result;
    }

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
        System.out.println("Длина вектора4 = " + vector4.getLengthVector());
        System.out.println("Разворот вектора4 = " + vector4.getReversalVector());
        System.out.println("Длина вектора4 = " + vector4.hashCode());
        System.out.println("Равенство векторов 3 и 4" + vector4.equals(vector3));
        System.out.println("Равенство векторов 4 и 4" + vector4.equals(vector4));
        System.out.println("Сумма векторов 4 и 3" + vector4.getSum(vector3));
        System.out.println("Сумма векторов 3 и 4" + vector3.getSum(vector4));
        System.out.println("Разница векторов 4 и 3" + vector4.getDifference(vector3));
        System.out.println("Разница векторов 3 и 4" + vector3.getDifference(vector4));
        System.out.println("Статическая сумма векторов 4 и 3" + getSum(vector4, vector3));
        System.out.println("Статическая сумма векторов 3 и 4" + getSum(vector3, vector4));
        System.out.println("Статическая разница векторов 4 и 3" + getDifference(vector4, vector3));
        System.out.println("Статическая разница векторов 3 и 4" + getDifference(vector3, vector4));
        System.out.println("Умножение вектора 4 на 5" + vector4.getScalarMultiplication(5));
        System.out.println("Статическое умножение вектора 4 на 5" + getScalarMultiplication(vector4, 5));
    }
}
