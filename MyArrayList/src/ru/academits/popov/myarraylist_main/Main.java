package ru.academits.popov.myarraylist_main;

import ru.academits.popov.myarraylist.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> myArrayList = new MyArrayList<>(6);

        System.out.println("Пустой лист: " + myArrayList);
        System.out.println("Пустой лист?: " + myArrayList.isEmpty());

        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        myArrayList.add(1);
        myArrayList.add(7);

        System.out.println("Заполненный лист: " + myArrayList);

        myArrayList.remove(1);
        myArrayList.remove((Integer) 7);

        System.out.println("Лист после удаления: " + myArrayList);

        ArrayList<Integer> tempArrayList = new ArrayList<>(Arrays.asList(5, 6, 7, 8));
        myArrayList.addAll(1, tempArrayList);

        System.out.println("Добавление целого списка: " + myArrayList);
        System.out.println("Есть ли элемент 5 в списке: " + myArrayList.contains(5));
        System.out.println("Есть ли элементы " + tempArrayList + " в списке: " + myArrayList.containsAll(tempArrayList));

        myArrayList.removeAll(tempArrayList);

        System.out.println("Удаляем список из списка: " + myArrayList);

        myArrayList.addAll(1, tempArrayList);
        myArrayList.retainAll(tempArrayList);
        System.out.println("Удаляем элементы не из списка: " + myArrayList);
    }
}
