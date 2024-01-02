package com.yangtao.java_version.java8.java8_in_action.ch06;

/**
 * @Author: kante_yang
 * @Date: 2023/9/26
 */

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.yangtao.java_version.java8.java8_in_action.ch06.Dish.menu;
import static java.util.stream.Collectors.*;

/**
 * 分区操作
 */
public class Partitioning {

    public static void main(String[] args) {
//        System.out.println(partitionByVegeterian());
        System.out.println(mostCaloricPartitionedByVegetarian());
    }


    private static Map<Boolean, List<Dish>> partitionByVegeterian() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }

    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian,
                groupingBy(Dish::getType)));
    }

    private static Object mostCaloricPartitionedByVegetarian() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian,
               collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)),
                       Optional::get)));
    }


}
