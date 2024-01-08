package com.yangtao.java_version.java8.java8_test.b_stream.a_middle;

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
 * @Date: 2024/1/8
 */
public class Sorting {


    List<Sorting.Person> persons = Collections.emptyList();

    @Before
    public void before() {
        persons = Arrays.asList(
            new Sorting.Person("a", 1),
            new Sorting.Person("a", 2),
            new Sorting.Person("b", 2),
            new Sorting.Person("b", 3),
            new Sorting.Person("c", 3),
            new Sorting.Person("c", 4),
            new Sorting.Person("d", 4),
            new Sorting.Person("d", 5),
            new Sorting.Person(null, 5),
            new Sorting.Person(null, null),
            new Sorting.Person("f", null),
            new Sorting.Person("g", null)
        );
    }

    @Test
    public void test1() {
        // 一级和二级都是正序
        Comparator<Sorting.Person> comparator = Comparator.comparing(Sorting.Person::getName, Comparator.nullsFirst(Comparator.naturalOrder()))
            .thenComparing(Sorting.Person::getAge, Comparator.nullsFirst(Comparator.naturalOrder()));

        persons = persons.stream()
            .sorted(comparator)
            .collect(Collectors.toList());
        persons.stream()
            .forEach(person -> System.out.println(person));
    }

    @Test
    public void test2() {
        // 一级是正序 二级是逆序
        Comparator<Sorting.Person> comparator = Comparator.comparing(Sorting.Person::getName)
            .thenComparing(Sorting.Person::getAge, Comparator.reverseOrder());
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
        Comparator<Sorting.Person> comparator = Comparator.comparing(Sorting.Person::getName)
            .reversed()
            .thenComparing(Sorting.Person::getAge);

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
        Comparator<Sorting.Person> comparator = Comparator.comparing(Sorting.Person::getName)
            .thenComparing(Sorting.Person::getAge)
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
