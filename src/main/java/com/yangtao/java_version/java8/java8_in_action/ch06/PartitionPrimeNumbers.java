package com.yangtao.java_version.java8.java8_in_action.ch06;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * @Author: kante_yang
 * @Date: 2023/9/26
 */
public class PartitionPrimeNumbers {

    public static void main(String[] args) {

        System.out.println(partitionPrimes(9));
    }

    /**
     * 判断candidate 是否是素数
     * @param candidate
     * @return
     */
    private static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);

        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }


    // 将前n个数字分成素数和非素数
    private static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n)
                .boxed()
                .collect(partitioningBy(PartitionPrimeNumbers::isPrime));
    }




}
