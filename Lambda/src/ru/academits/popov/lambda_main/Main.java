package ru.academits.popov.lambda_main;

import ru.academits.popov.person.Person;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("Иван", 42),
                new Person("Сергей", 12),
                new Person("Анатолий", 26),
                new Person("Иван", 35),
                new Person("Анна", 10),
                new Person("Игорь", 34),
                new Person("Степан", 25),
                new Person("Константин", 78),
                new Person("Алина", 62),
                new Person("Игорь", 43)
        ));

        List<String> distinctNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Список уникальных имен: " + distinctNames);

        String distinctNamesLine = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(distinctNamesLine);

        List<Person> personsUnder18 = persons.stream()
                .filter(p -> p.getAge() < 18)
                .collect(Collectors.toList());

        Double personsUnder18AverageAge = personsUnder18.stream()
                .collect(Collectors.averagingInt(Person::getAge));
        System.out.println("Средний возраст людей до 18 = " + personsUnder18AverageAge);

        Map<String, Double> ageByName = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println("Средний возраст на имя: " + ageByName);

        List<Person> personsByAge20To45 = persons.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted((p1, p2) -> (p2.getAge() - p1.getAge()))
                .collect(Collectors.toList());
        System.out.println(personsByAge20To45);
    }
}
