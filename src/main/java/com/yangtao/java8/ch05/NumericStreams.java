package com.yangtao.java8.ch05;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.yangtao.java8.ch05.Dish.menu;

/**
 * @Author: kante_yang
 * @Date: 2023/9/26 09:22
 */
public class NumericStreams {

    public static void main(String[] args) {

        // 映射到数值流
        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(calories);

        // 转换成对象流
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();

        // 找到IntStream中的最大值，Stream 可能是空的，没有元素
        OptionalInt max = menu.stream().mapToInt(Dish::getCalories).max();
        // 没有最大值的情况 可以设置一个默认最大值
        int m = max.orElse(1);

        // 生成数值流
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0); // 1到100的偶数流
        System.out.println(evenNumbers.count());

        // 生成一个勾股流 a*a + b*b = c*c
        Stream<double[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(1, 100)
                                .mapToObj(b -> new double[]{a, b, a*a + b*b}))
                .filter(t -> t[2] % 1 == 0);

    }
}
