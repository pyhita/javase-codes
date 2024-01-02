package com.yangtao.java_version.java8.java8_in_action.ch05;

/**
 * @Author: kante_yang
 * @Date: 2023/9/25 17:27
 */

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 流的规约操作
 */
public class Reducing {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(3,4,5,1,2);
        // 元素求和
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        int sum2 = numbers.stream().reduce(0, Integer::sum);

        // 最大值
        int max = numbers.stream().reduce(0 , (a, b) -> Integer.max(a, b));
        Optional<Integer> min = numbers.stream().reduce(Integer::min);


    }


}
