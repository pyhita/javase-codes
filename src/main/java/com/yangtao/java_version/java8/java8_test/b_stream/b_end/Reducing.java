package com.yangtao.java_version.java8.java8_test.b_stream.b_end;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * @Author: kante_yang
 * @Date: 2024/1/8
 */

public class Reducing {

    private List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
    private List<Dish> dishes = Dish.menu;

    @Test
    public void sum() {
        Integer sum = nums.stream()
            .collect(Collectors.reducing(0, Integer::sum));

        System.out.println(sum);
    }

    @Test
    public void counting() {
        Long count = nums.stream()
            .collect(Collectors.reducing(0L, a -> 1L, Long::sum));
        System.out.println(count);
    }

    // max calories
    @Test
    public void testMaxCalories() {
        Integer maxCalories = dishes.stream()
            .collect(Collectors.reducing(null, Dish::getCalories, (c1, c2) -> {
                return c1 - c2 > 0 ? c1 : c2;
            }));

        System.out.println(maxCalories);
    }
}
