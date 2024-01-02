package com.yangtao.java_version.java8.java8_in_action.ch06;

import java.util.Comparator;
import java.util.Optional;

import static com.yangtao.java_version.java8.java8_in_action.ch06.Dish.menu;
import static java.util.stream.Collectors.*;
/**
 * @Author: kante_yang
 * @Date: 2023/9/26
 */

/**
 * 对流进行规约和汇总
 */
public class Reducing {

    public static void main(String[] args) {

        Optional<Dish> maxDish = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(maxDish.get().getCalories());

        System.out.println(max().getCalories());

    }

    // 拿到热量最大的菜品
    public static Dish max() {

        return menu.stream().collect(maxBy(Comparator.comparing(Dish::getCalories))).get();
    }

    // 拿到热量最小的菜品
    public static Dish min() {
        return menu.stream().collect(minBy(Comparator.comparing(Dish::getCalories))).get();
    }

    public static int calculateTotalCalories() {
        return menu.stream().collect(summingInt(Dish::getCalories));
    }

    public static int calculateTotalCaloriesWithMethodReference() {
        return menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
    }

    public static int calculateTotalCaloriesWithoutCollectors() {
        return menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
    }

    public static int calculateTotalCaloriesUsingSum() {
        return menu.stream().mapToInt(Dish::getCalories).sum();
    }



}
