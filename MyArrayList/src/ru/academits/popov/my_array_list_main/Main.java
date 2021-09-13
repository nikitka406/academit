package ru.academits.popov.my_array_list_main;

import ru.academits.popov.my_array_list.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> myArrayList1 = new MyArrayList<>(6);

        System.out.println("Пустой лист: " + myArrayList1);
        System.out.println("Пустой лист?: " + myArrayList1.isEmpty());

        myArrayList1.add(1);
        myArrayList1.add(2);
        myArrayList1.add(2);
        myArrayList1.add(3);
        myArrayList1.add(4);
        myArrayList1.add(5);
        myArrayList1.add(1);
        myArrayList1.add(7);

        System.out.println("Заполненный лист: " + myArrayList1);

        myArrayList1.remove(1);
        myArrayList1.remove((Integer) 7);

        System.out.println("Лист после удаления: " + myArrayList1);

        ArrayList<Integer> myArrayList2 = new ArrayList<>(Arrays.asList(5, 6, 7, 8));
        myArrayList1.addAll(1, myArrayList2);

        System.out.println("Добавление целого списка: " + myArrayList1);
        System.out.println("Есть ли элемент 5 в списке: " + myArrayList1.contains(5));
        System.out.println("Есть ли элементы " + myArrayList2 + " в списке: " + myArrayList1.containsAll(myArrayList2));

        myArrayList1.removeAll(myArrayList2);

        System.out.println("Удаляем список из списка: " + myArrayList1);

        myArrayList1.addAll(1, myArrayList2);
        myArrayList1.retainAll(myArrayList2);
        System.out.println("Удаляем элементы не из списка: " + myArrayList1);
    }
}
