package ru.academits.popov.vector_main;

import ru.academits.popov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(5);
        System.out.println(vector1);

        Vector vector2 = new Vector(new double[]{1,3,4,6});
        System.out.println(vector2);

        Vector vector3 = new Vector(vector2);
        System.out.println(vector3);

        Vector vector4 = new Vector(10, new double[]{3,4,5,6,7,8});
        System.out.println(vector4);
    }
}
