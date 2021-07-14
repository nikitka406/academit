package ru.academits.popov.vector;

public class Vector {
    public double[] components;

    public Vector(int n) {
        components = new double[n];

        for (int i = 0; i < n; ++i) {
            components[i] = 0;
        }
    }

    public Vector(double[] array) {
        int size = array.length;
        components = new double[size];

        System.arraycopy(array, 0, components, 0, size);
    }

    public Vector(int n, double[] array) {
        int size = array.length;
        components = new double[n];

        for (int i = 0; i < n; ++i) {
            if (i < size) {
                components[i] = array[i];
            } else {
                components[i] = 0;
            }
        }
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
        String string = "{";

        for (int i = 0; i < getSize(); ++i) {
            string += components[i];
            if (i != getSize() - 1) {
                string += ", ";
            }
        }

        string += "}";

        return string;
    }

    public Vector getSum(Vector vector) {
        if (getSize() != vector.getSize()) {
            return null;
        }

        Vector result = new Vector(getSize());

        for (int i = 0; i < getSize(); ++i) {
            result.components[i] = components[i] + vector.components[i];
        }

        return result;
    }

    public Vector getDifference(Vector vector) {
        if (getSize() != vector.getSize()) {
            return null;
        }

        Vector result = new Vector(getSize());

        for (int i = 0; i < getSize(); ++i) {
            result.components[i] = components[i] - vector.components[i];
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

        if (getSize() == vector.getSize()) {
            for (int i = 0; i < getSize(); ++i) {
                if (components[i] != vector.components[i]) {
                    return false;
                }
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

}
