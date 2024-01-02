package com.yangtao.java_version.java8.java8_in_action;

/**
 * @Author: kante_yang
 * @Date: 2023/9/5 12:08
 */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 测试Stream 并行流
 */
public class StreamTest_03 {

    public static void main(String[] args) {
        List<String> numbers = Arrays.asList("13378360000","13278240000","13178590000","13558120000");
        // 创建并行流
        List<String> filNums = numbers.stream().parallel().map(s -> s.substring(0, 7)).collect(Collectors.toList());
        System.out.println(filNums);
    }
}


