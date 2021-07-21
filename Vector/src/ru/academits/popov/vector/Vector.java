package ru.academits.popov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if(size <= 0){
            throw new IllegalArgumentException("Exception: n <= 0");
        }

        components = new double[size];
    }

    public Vector(double[] array) {
        if(array.length <= 0){
            throw new IllegalArgumentException("Exception: n <= 0");
        }

        components = new double[array.length];

        System.arraycopy(array, 0, components, 0, array.length);
    }

    public Vector(int size, double[] array) {
        if(size <= 0){
            throw new IllegalArgumentException("Exception: n <= 0");
        }

        components = new double[size];

        System.arraycopy(array, 0, components, 0, array.length);
    }

    public Vector(Vector vector) {
        int size = vector.components.length;
        components = new double[size];
        System.arraycopy(vector.components, 0, components, 0, size);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("{");

        for (int i = 0; i < getSize(); ++i) {
            string.append(components[i]);

            if (i != getSize() - 1) {
                string.append(", ");
            }
        }

        string.append("}");
        return string.toString();
    }

    public Vector getSum(Vector vector) {
        int length = Math.max(getSize(), vector.getSize());
        Vector result = new Vector(length);

        for (int i = 0; i < length; ++i) {
            if (getSize() == length){
                if (i < vector.getSize()) {
                    result.components[i] = components[i] + vector.components[i];
                } else {
                    result.components[i] = components[i] + 0;
                }
            }else {
                if (i < getSize()) {
                    result.components[i] = components[i] + vector.components[i];
                } else {
                    result.components[i] = 0 + vector.components[i];
                }
            }
        }

        return result;
    }

    public Vector getDifference(Vector vector) {
        int length = Math.max(getSize(), vector.getSize());
        Vector result = new Vector(length);

        for (int i = 0; i < length; ++i) {
            if (getSize() == length){
                if (i < vector.getSize()) {
                    result.components[i] = components[i] - vector.components[i];
                } else {
                    result.components[i] = components[i] - 0;
                }
            }else {
                if (i < getSize()) {
                    result.components[i] = components[i] - vector.components[i];
                } else {
                    result.components[i] = 0 - vector.components[i];
                }
            }
        }

        return result;
    }

    public Vector getScalarMultiplication(double number) {
        Vector result = new Vector(getSize());

        for (int i = 0; i < getSize(); ++i) {
            result.components[i] = components[i] * number;
        }

        return result;
    }

    public Vector getReversalVector() {
        Vector result = new Vector(getSize());

        for (int i = 0; i < getSize(); ++i) {
            result.components[i] = -components[i];
        }

        return result;
    }

    public double getLengthVector() {
        double length = 0;

        for (int i = 0; i < getSize(); ++i) {
            length += components[i] * components[i];
        }

        return Math.sqrt(length);
    }

    public double getVectorComponent(int index) {
        return components[index];
    }

    public void setVectorComponent(int index, double component) {
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

        if (getSize() != vector.getSize()) {
            return false;
        }

        for (int i = 0; i < getSize(); ++i) {
            if (components[i] != vector.components[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;

        for (int i = 0; i < getSize(); ++i) {
            hashCode += components[i] * 31;
        }

        return hashCode * getSize();
    }

    public static Vector getSum(Vector vector1, Vector vector2){
        return vector1.getSum(vector2);
    }

    public static Vector getDifference(Vector vector1, Vector vector2){
        return vector1.getDifference(vector2);
    }

    public static Vector getScalarMultiplication(Vector vector, double number){
        return vector.getScalarMultiplication(number);
    }
}
