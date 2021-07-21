package ru.academits.popov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Exception: n <= 0");
        }

        components = new double[size];
    }

    public Vector(double[] array) {
        if (array.length <= 0) {
            throw new IllegalArgumentException("Exception: n <= 0");
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Exception: n <= 0");
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

    public void getSum(Vector vector) {
        int size = Math.max(components.length, vector.components.length);

        if (size != components.length) {
            components = Arrays.copyOf(components, size);
        }

        for (int i = 0; i < Math.min(components.length, vector.components.length); ++i) {
            components[i] += vector.components[i];
        }
    }

    public void getDifference(Vector vector) {
        int size = Math.max(components.length, vector.components.length);

        if (size != components.length) {
            components = Arrays.copyOf(components, size);
        }

        for (int i = 0; i < size; ++i) {
            components[i] -= vector.components[i];
        }
    }

    public void getCompositionOnScalar(double number) {
        for (int i = 0; i < components.length; ++i) {
            components[i] *= number;
        }
    }

    public void getReversalVector() {
        getCompositionOnScalar(-1);
    }

    public double getLength() {
        double lengthSquared = 0;

        for (int i = 0; i < components.length; ++i) {
            lengthSquared += components[i] * components[i];
        }

        return Math.sqrt(lengthSquared);
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

        return components.length == vector.components.length && Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hashCode = 1;

        for (double component : components) {
            hashCode += hashCode * prime + Double.hashCode(component);
        }

        return hashCode;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        vector1.getSum(vector2);
        return vector1;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        vector1.getDifference(vector2);
        return vector1;
    }

    public static double getScalarComposition(Vector vector1, Vector vector2) {
        double scalarComposition = 0;

        for (int i = 0; i < Math.max(vector1.components.length, vector2.components.length); ++i) {
            if (i == Math.min(vector1.components.length, vector2.components.length)) {
                break;
            }

            scalarComposition += vector1.getComponent(i) * vector2.getComponent(i);
        }

        return scalarComposition;
    }
}