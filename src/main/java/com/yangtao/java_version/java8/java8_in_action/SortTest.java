package com.yangtao.java_version.java8.java8_in_action;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: kante_yang
 * @Date: 2023/12/8
 */
public class SortTest {

    List<Person> persons = Collections.emptyList();

    @Before
    public void before() {
        persons = Arrays.asList(
            new Person("a", 1),
            new Person("a", 2),
            new Person("b", 2),
            new Person("b", 3),
            new Person("c", 3),
            new Person("c", 4),
            new Person("d", 4),
            new Person("d", 5),
            new Person(null, 5),
            new Person(null, null),
            new Person("f", null),
            new Person("g", null)
        );
    }

    @Test
    public void test1() {
        // 一级和二级都是正序
        Comparator<Person> comparator = Comparator.comparing(Person::getName, Comparator.nullsFirst(Comparator.naturalOrder()))
            .thenComparing(Person::getAge, Comparator.nullsFirst(Comparator.naturalOrder()));

        persons = persons.stream()
            .sorted(comparator)
            .collect(Collectors.toList());
        persons.stream()
            .forEach(person -> System.out.println(person));
    }

    @Test
    public void test2() {
        // 一级是正序 二级是逆序
        Comparator<Person> comparator = Comparator.comparing(Person::getName)
            .thenComparing(Person::getAge, Comparator.reverseOrder());
        persons = persons.stream()
            .sorted(comparator)
            .collect(Collectors.toList());
        System.out.println(persons);
    }


    @Test
    public void test3() {
        // 一级是逆序，二级是正序
//        Comparator<Person> comparator = Comparator.comparing(Person::getName, Comparator.reverseOrder())
//            .thenComparing(Person::getAge);
        Comparator<Person> comparator = Comparator.comparing(Person::getName)
            .reversed()
            .thenComparing(Person::getAge);

        persons = persons.stream()
            .sorted(comparator)
            .collect(Collectors.toList());

        persons.forEach(person -> System.out.println(person));
    }

    @Test
    public void test4() {
        // 一级是逆序，二级是逆序
//        Comparator<Person> comparator = Comparator.comparing(Person::getName, Comparator.reverseOrder())
//            .thenComparing(Person::getAge);
        Comparator<Person> comparator = Comparator.comparing(Person::getName)
            .thenComparing(Person::getAge)
            .reversed();

        persons = persons.stream()
            .sorted(comparator)
            .collect(Collectors.toList());

        persons.forEach(person -> System.out.println(person));
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Person {
        String name;
        Integer age;
    }
}
