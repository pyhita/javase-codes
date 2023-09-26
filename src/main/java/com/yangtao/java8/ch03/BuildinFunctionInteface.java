package com.yangtao.java8.ch03;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Author: kante_yang
 * @Date: 2023/9/25 14:50
 */
public class BuildinFunctionInteface {

    @Test
    public void test1() {
        String result = testPredicate("123353fdsfsd", (String str) -> str.startsWith("1"));
        System.out.println(result);

        List<String> list = Arrays.asList("123", "abc", "def");
        forEach(list, (String str) -> System.out.println(str));

        List<Integer> res = map(list, s -> s.length());
        System.out.println(res);
    }

    public String testPredicate(String exp, Predicate<String> predicate) {
        if (predicate.test(exp)) {
            return "accpet";
        }

        return "unaccept";
    }

    public static void forEach(List<String> list, Consumer<String> consumer) {
        for (String s : list) {
            consumer.accept(s);
        }
    }

    public static <T,R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }

        return result;
    }
}
