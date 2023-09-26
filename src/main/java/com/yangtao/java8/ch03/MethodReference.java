package com.yangtao.java8.ch03;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author: kante_yang
 * @Date: 2023/9/25 15:28
 */
public class MethodReference {

    public static void main(String[] args) {

        // 第一种：静态方法
        Function<String, Integer> function = Integer::parseInt;

        // 第二种：对象方法
        Function<String, Integer> function1 = String::length;

        // 第三种：其他对象的方法
        Person liam = new Person("liam", 23);
        Consumer<String> consumer = liam::hello;
        consumer.accept("world");

        // 第四种引用构造方法
            // 空参构造方法
        Supplier<Person> supplier = Person::new;
        Person person = supplier.get();
        System.out.println(person);

            // 所有参数构造方法
        BiFunction<String, Integer, Person> biFunction = Person::new;
        Person kante = biFunction.apply("kante", 22);
        System.out.println(kante);

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Person {
        private String name;
        private Integer age;

        public void hello(String msg) {
            System.out.println("hello " + msg);
        }
    }


}
