package com.yangtao.java_version.java8.java8_in_action.ch07;

/**
 * @Author: kante_yang
 * @Date: 2023/9/26
 */

import java.util.stream.Stream;

/**
 * 并行流处理
 */
public class ParallelStreams {

    public static void main(String[] args) {

    }

    private static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }
}
