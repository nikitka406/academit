package ru.academits.popov.hash_table_main;

import ru.academits.popov.hash_table.HashTable;

public class Main {
    public static void main(String[] args) {
        HashTable<String> myHashTable1 = new HashTable<>();
        myHashTable1.add("fdfd");
        myHashTable1.add("sfwefwf");
        myHashTable1.add("3qr3fsaea");

        System.out.println("Моя первая хэш таблица: " + myHashTable1);

        HashTable<Integer> myHashTable2 = new HashTable<>(5);
        myHashTable2.add(212);
        myHashTable2.add(12345);
        myHashTable2.add(8765);

        System.out.println("Моя вторая хэш таблица: " + myHashTable2);

        myHashTable2.remove(212);
        System.out.println("Таблица с удаленым элементом: " + myHashTable2);

        myHashTable1.clear();
        System.out.println("Отчищенная таблица: " + myHashTable1);
    }
}
