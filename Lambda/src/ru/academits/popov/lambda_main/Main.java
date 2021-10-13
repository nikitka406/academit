package ru.academits.popov.lambda_main;

import ru.academits.popov.person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("Иван", 42));
        persons.add(new Person("Сергей", 12));
        persons.add(new Person("Анатолий", 26));
        persons.add(new Person("Иван", 35));
        persons.add(new Person("Анна", 10));
        persons.add(new Person("Игорь", 34));
        persons.add(new Person("Степан", 25));
        persons.add(new Person("Константин", 78));
        persons.add(new Person("Алина", 62));
        persons.add(new Person("Игорь", 43));

        List<String> distinctNames = persons.stream().map(Person::getName).distinct().collect(Collectors.toList());
        System.out.println("Список уникальных имен: " + distinctNames);

        String distinctNameLine = persons.stream().map(Person::getName).distinct().collect(Collectors.joining(", ", "Имена: ", ""));
        System.out.println(distinctNameLine);

        List<Person> personUnder18 = persons.stream().filter(x -> x.getAge() < 18).collect(Collectors.toList());

        Double personUnder18AverageAge = personUnder18.stream().collect(Collectors.averagingInt(Person::getAge));
        System.out.println("Средний возраст людей до 18 = " + personUnder18AverageAge);

        Map<String, Double> ageByName = persons.stream().collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println("Средний возраст на имя: " + ageByName);

        List<Person> personByAge20To45 = persons.stream().filter(x -> x.getAge() > 20 && x.getAge() < 45).sorted((x, y) -> x.getAge() - y.getAge()).collect(Collectors.toList());
        System.out.println(personByAge20To45);
    }
}
