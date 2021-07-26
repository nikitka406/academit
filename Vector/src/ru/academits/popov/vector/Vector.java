package ru.academits.popov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер массива не может быть меньше или равен нуля, size = " + size);
        }

        components = new double[size];
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Размер массива не может быть равен нулю, size = " + array.length);
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер массива не может быть меньше или равен нуля, size = " + size);
        }

        components = Arrays.copyOf(array, size);
    }

    public Vector(Vector vector) {
        this(vector.components.length, vector.components);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("{");

        for (int i = 0; i < components.length; ++i) {
            string.append(components[i]);

            if (i != components.length - 1) {
                string.append(", ");
            }
        }

        string.append("}");
        return string.toString();
    }

    public void add(Vector vector) {
        int maxSize = Math.max(components.length, vector.components.length);

        if (maxSize != components.length) {
            components = Arrays.copyOf(components, maxSize);
        }

        for (int i = 0; i < components.length; ++i) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        int maxSize = Math.max(components.length, vector.components.length);

        if (maxSize != components.length) {
            components = Arrays.copyOf(components, maxSize);
        }

        for (int i = 0; i < maxSize; ++i) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(double number) {
        for (int i = 0; i < components.length; ++i) {
            components[i] *= number;
        }
    }

    public void revers() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double result = 0;

        for (double component : components) {
            result += component * component;
        }

        return Math.sqrt(result);
    }

    public double getComponent(int index) {
        return components[index];
    }

    public void setComponent(int index, double component) {
        components[index] = component;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hashCode = 1;

        hashCode += hashCode * prime + Arrays.hashCode(components);

        return hashCode;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.add(vector2);
        return result;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.subtract(vector2);
        return result;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double scalarComposition = 0;
        int maxLength = Math.max(vector1.components.length, vector2.components.length);
        int minLength = Math.min(vector1.components.length, vector2.components.length);

        for (int i = 0; i < maxLength; ++i) {
            if (i == minLength) {
                break;
            }

            scalarComposition += vector1.getComponent(i) * vector2.getComponent(i);
        }

        return scalarComposition;
    }
}