package com.yangtao.java8.ch03;

/**
 * @Author: kante_yang
 * @Date: 2023/9/25 15:54
 */

import org.junit.Test;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * lamba 表达式进行复合，组成更加复杂的逻辑
 */
public class ComplexLamba {

    public static void main(String[] args) {

    }

    // 谓词复合
    @Test
    public void test1() {
        Predicate<Apple> redPredicate = apple -> "green".equals(apple.getColor());

        // 要么是绿色的，要么是红色的并且重的
        Predicate<Apple> predicate = redPredicate.and(a -> a.getWeight() > 150)
                .or(a -> "green".equals(a.getColor()));
    }

    // Function 的复合
    @Test
    public void test2() {
        // f : x -> x + 1
        Function<Integer, Integer> f  = x -> x + 1;
        // g: x -> x * 2
        Function<Integer, Integer> g = x -> x * 2;

        // andThen: g(f(x))
        Function<Integer, Integer> andThen = f.andThen(g);

        // compose: f(g(x))
        Function<Integer, Integer> compose = f.compose(g);

    }

    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}
