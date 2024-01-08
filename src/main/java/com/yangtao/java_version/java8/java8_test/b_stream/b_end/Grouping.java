package com.yangtao.java_version.java8.java8_test.b_stream.b_end;

import com.yangtao.java_version.java8.java8_test.b_stream.b_end.Dish.Type;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * @Author: kante_yang
 * @Date: 2024/1/8
 */
public class Grouping {

    private List<Dish> dishes = Dish.menu;

    // 一级分组
    @Test
    public void test1() {
        Map<Type, List<Dish>> typeMaps = dishes.stream()
            .collect(Collectors.groupingBy(Dish::getType));

    }

    // 二级分组
    @Test
    public void test2() {
        Map<Type, Map<String, List<Dish>>> maps = dishes.stream()
            .collect(Collectors.groupingBy(Dish::getType,
                Collectors.groupingBy(Dish::getName)));

    }

    // collectingAndThen, 先进行收集 然后进行转换
    // 查找每个子组 热量最高的Dish
    @Test
    public void test3() {
        Map<Type, Dish> maxMap = dishes.stream()
            .collect(Collectors.groupingBy(Dish::getType,
                Collectors.collectingAndThen(
                    Collectors.maxBy(Comparator.comparing(Dish::getCalories)),
                    Optional::get)));
    }

    // mapping 先进行转换 然后对转换后的结果进行收集
    @Test
    public void test4() {
        Map<Type, Set<Integer>> typeSets = dishes.stream()
            .collect(Collectors.groupingBy(Dish::getType,
                Collectors.mapping(Dish::getCalories, Collectors.toSet())));

    }

}
