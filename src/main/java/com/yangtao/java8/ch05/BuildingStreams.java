package com.yangtao.java8.ch05;

/**
 * @Author: kante_yang
 * @Date: 2023/9/26 09:50
 */

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 创建流的各种方法
 */
public class BuildingStreams {

    public static void main(String[] args) {

        // Stream.of 通过值创建流
        Stream<String> stream = Stream.of("Java 8", "Lamdas", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        // empty得到一个空流
        Stream<String> empty = Stream.empty();

        // 通过数组创建流
        int[] numbers = new int[]{1, 2, 3, 4, 5};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);

        // 通过文件生成流

    }
}
