package ru.academits.popov.arraylisthome;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("ArrayListHome/wholeNumber.txt"))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                arrayList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла: " + e.getMessage());
        }

        try {
            for (int i = 0; i < arrayList.size(); ++i) {
                if (Integer.parseInt(arrayList.get(i)) % 2 == 0) {
                    arrayList.remove(arrayList.get(i));
                }
            }

            System.out.println("Список без четных чисел = " + arrayList);
        } catch (NumberFormatException e) {
            System.out.println("Неудается выполнить удаление четных чисел, потому что в файле находятся символы");
        }

        ArrayList<String> uniqueList = new ArrayList<>();
        for (String s : arrayList) {
            if (!uniqueList.contains(s)) {
                uniqueList.add(s);
            }
        }

        System.out.println("Список с уникальными значениями = " + uniqueList);
    }
}
