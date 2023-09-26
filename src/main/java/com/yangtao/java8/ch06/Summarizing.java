package com.yangtao.java8.ch06;

import org.junit.Test;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

import static com.yangtao.java8.ch06.Dish.menu;
import static java.util.stream.Collectors.*;

/**
 * @Author: kante_yang
 * @Date: 2023/9/26
 */
public class Summarizing {

    public static void main(String[] args) {
        System.out.println(getShortMenu());
        System.out.println(findMostCaloricDish());
    }

    private static long howManyDishes() {
        return menu.stream().collect(counting());

//        return menu.stream().count();
    }

    private static long howManhyDished2() {
        // 通过reducing操作 来实现 counting
        return menu.stream().collect(reducing(0L, e -> 1L, Long::sum));
    }

    // 汇总相关的操作
    private static Dish findMostCaloricDish() {
        return menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
    }

    private static Dish findMostCaloricDishUsingComparator() {
        return menu.stream().collect(maxBy(Comparator.comparing(Dish::getCalories))).get();
    }

    private static int calculateTotalCalories() {
        return menu.stream().collect(summingInt(Dish::getCalories));
    }

    private static double calculateAverageCalories() {
        return menu.stream().collect(averagingInt(Dish::getCalories));
    }

    private static IntSummaryStatistics calculateMenuStatistics() {
        return menu.stream().collect(summarizingInt(Dish::getCalories));
    }

    // 连接字符串相关的操作
    private static String getShortMenu() {
        return menu.stream().map(Dish::getName).collect(joining(" , "));

        // 通过reducing 取代joining
//        return menu.stream().map(Dish::getName).collect(reducing((s1, s2) -> s1 + s2)).get();
//        return menu.stream().collect(reducing("", Dish::getName, (s1, s2) -> s1 + s2));
    }

}
