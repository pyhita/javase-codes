package com.yangtao.java_version.java8.java8_test.b_stream.a_middle;

/**
 * @Author: kante_yang
 * @Date: 2024/1/8
 */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * 测试流的映射操作
 */
public class Mapping {


    // flatMap 将多个流合并成一个流
    // ["Hello","World"] -> ["H","e","l", "o","W","r","d"]
    @Test
    public void test1() {
        String[] words = new String[]{"Hello","World"};

        List<String> result = Arrays.stream(words)
            .map(s -> s.split(""))
            .flatMap(Arrays::stream)
            .collect(Collectors.toList());

        System.out.println(result);
    }

    //         List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
    //        List<Integer> numbers2 = Arrays.asList(6,7,8);
    @Test
    public void test2() {
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);

        List<int[]> pairs = numbers1.stream()
            .flatMap(n1 -> numbers2.stream()
                .map(n2 -> new int[]{n1, n2}))
            .collect(Collectors.toList());

        for (int[] pair : pairs) {
            System.out.println(Arrays.toString(pair));
        }

    }


}
