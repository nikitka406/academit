package ru.academits.popov.list_main;

import ru.academits.popov.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> integerSinglyLinkedList = new SinglyLinkedList<>();
        integerSinglyLinkedList.addFirst(1);
        integerSinglyLinkedList.addFirst(2);
        integerSinglyLinkedList.addFirst(3);
        integerSinglyLinkedList.addFirst(4);
        integerSinglyLinkedList.addFirst(5);
        integerSinglyLinkedList.addFirst(6);

        System.out.println("Односвязный список int = " + integerSinglyLinkedList);
        System.out.println("Размер списка = " + integerSinglyLinkedList.getCount());
        System.out.println("Первый элемент списка = " + integerSinglyLinkedList.getFirst());
        System.out.println("Элемент с индексом 5 = " + integerSinglyLinkedList.getDataByIndex(5));
        System.out.println("Устанавливаем элемент 10 по индексу 5 вместо: " + integerSinglyLinkedList.setDataByIndex(5, 10));
        System.out.println("Односвязный список int = " + integerSinglyLinkedList);
        System.out.println("Удаляем первый элемент: " + integerSinglyLinkedList.deleteFirst());
        System.out.println("Односвязный список int = " + integerSinglyLinkedList);
        System.out.println("Удаляем элемент по индексу 2: " + integerSinglyLinkedList.deleteByIndex(2));
        System.out.println("Односвязный список int = " + integerSinglyLinkedList);
        System.out.println("Удаляем элемент с значением 1: " + integerSinglyLinkedList.deleteByData(1));
        System.out.println("Односвязный список int = " + integerSinglyLinkedList);
        integerSinglyLinkedList.addByIndex(1, 2);
        System.out.println("Добавили элемент в список = " + integerSinglyLinkedList);
        integerSinglyLinkedList.reverse();
        System.out.println("Разворот списка: " + integerSinglyLinkedList);
    }
}
