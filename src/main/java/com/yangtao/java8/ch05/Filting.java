package com.yangtao.java8.ch05;

import static com.yangtao.java8.ch05.Dish.menu;
/**
 * @Author: kante_yang
 * @Date: 2023/9/25 16:48
 */


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 流的中间操作：筛选和切片
 */
public class Filting {

    public static void main(String[] args) {

        // 通过谓词过滤流
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());

        vegetarianMenu.forEach(System.out::println);
        System.out.println("-----------------------");

        // 去除重复元素，根据equals和hashCode方法进行判断
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
        System.out.println("-----------------------");

        // 截断流
        List<Dish> dishesLimit3 = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());

        dishesLimit3.forEach(System.out::println);
        System.out.println("-----------------------");

        // 跳过元素
        List<Dish> dishesSkip2 = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());

        dishesSkip2.forEach(System.out::println);
    }

}
