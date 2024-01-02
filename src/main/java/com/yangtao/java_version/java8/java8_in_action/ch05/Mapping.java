package com.yangtao.java_version.java8.java8_in_action.ch05;

/**
 * @Author: kante_yang
 * @Date: 2023/9/25 17:02
 */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.yangtao.java_version.java8.java8_in_action.ch05.Dish.menu;

/**
 * 映射操作
 */
public class Mapping {

    public static void main(String[] args) {

        // map 普通映射操作
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        dishNames.forEach(System.out::println);
        System.out.println("----------------------");

        // flatMap：多个流合并成一个流
        List<String> words = Arrays.asList("Hello", "World");
        List<String> uniqueCharacters = words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());

        // 确定两个数字列表，返回所有的数对
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);

        List<int[]> pairs = numbers1.stream()
                .flatMap(i ->
                        numbers2.stream()
                                .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        pairs.forEach(pair -> System.out.println(Arrays.toString(pair)));
    }
}
