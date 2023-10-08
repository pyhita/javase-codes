package com.yangtao.java8.ch10;

import lombok.Data;
import org.junit.Test;

import java.util.Optional;

/**
 * @Author: kante_yang
 * @Date: 2023/10/7
 */
public class BuildingOptional {

    public static void main(String[] args) {


    }

    @Test
    public void test1() {
        // 1 创建一个空的Optional
        Optional<Object> empty = Optional.empty();
        // 2 创建一个非空的Optional对象
        Person person = new Person();
        Optional<Person> optionalPerson = Optional.of(person);
        // 3 创建一个可以为null 的Optional
        Person person2 = null;
        Optional<Person> personOptional = Optional.ofNullable(person2);
    }


    // 通过map 从optional 中提取值，如果对象为空 什么都不做
    @Test
    public void test2() {
        Person person = null;
        String name = null;
        if (person != null) {
            name = person.getName();
        }

        Optional<String> name2 = Optional.ofNullable(person).map(Person::getName);
    }

    @Data
    static class Person {
        private String name;
        private Integer age;
    }


}
