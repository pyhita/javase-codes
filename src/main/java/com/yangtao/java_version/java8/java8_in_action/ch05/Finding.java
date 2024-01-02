package com.yangtao.java_version.java8.java8_in_action.ch05;

/**
 * @Author: kante_yang
 * @Date: 2023/9/25 17:18
 */

import java.util.Optional;

import static com.yangtao.java_version.java8.java8_in_action.ch05.Dish.menu;

/**
 * 查找和匹配
 */
public class Finding {

    public static void main(String[] args) {
        // allMatch anyMatch noneMatch findFirst findAny

        boolean isVegetarianFriendlyMenu = menu.stream()
                .anyMatch(Dish::isVegetarian);

        boolean isHealthyMenu = menu.stream()
                .allMatch(d -> d.getCalories() < 1000);

        boolean isHealthyMenu2 = menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);

    }

    public static Optional<Dish> findVegetarianDish() {
        return menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
    }
}
