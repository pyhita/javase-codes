package com.yangtao.java8.ch06;

import java.awt.font.OpenType;
import java.util.*;

import static com.yangtao.java8.ch06.Dish.menu;
import static java.util.stream.Collectors.*;

/**
 * @Author: kante_yang
 * @Date: 2023/9/26
 */
// 分组操作
public class Grouping {

    public static void main(String[] args) {

        System.out.println(groupDishedByTypeAndCaloricLevel());
        System.out.println(caloricLevelsByType());
    }

    enum CaloricLevel { DIET, NORMAL, FAT };

    private static Map<Dish.Type, List<Dish>> groupDishesByType() {
        return menu.stream().collect(groupingBy(Dish::getType));
    }

    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
        // 根据热量级别进行分组
        return menu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }));
    }

    // 多级分组
    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
        return menu.stream().collect(groupingBy(Dish::getType,
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                })));
    }

    private static Map<Dish.Type, Long> countDishesInGroups() {
        return menu.stream().collect(groupingBy(Dish::getType, counting()));
    }

    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        return menu.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparing(Dish::getCalories))));
    }


    // 通过收集器：collectingAndThen 去除Optional，将结果转换成另一种类型
    private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOprionals() {
        return menu.stream().collect(groupingBy(Dish::getType,
                collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)),
                        Optional::get)));
    }

    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
        return menu.stream().collect(groupingBy(Dish::getType,
                mapping(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }, toCollection(HashSet::new))));
    }


}
