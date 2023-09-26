package com.yangtao.java8.ch03;

import com.yangtao.java8.ch02.FilteringApples;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: kante_yang
 * @Date: 2023/9/25 15:42
 */
public class Sorting {

    public static void main(String[] args) {
        List<FilteringApples.Apple> inventory = Arrays.asList(
                new FilteringApples.Apple(80,"green"),
                new FilteringApples.Apple(155, "green"),
                new FilteringApples.Apple(120, "red"));

        // 综合lamba  和  方法引用 最简化写法
        inventory.sort(Comparator.comparing(FilteringApples.Apple::getWeight));

        // 1 比较器复合
            // 1.1 逆序排序
        inventory.sort(Comparator.comparing(FilteringApples.Apple::getWeight).reversed());
            // 1.2 两个苹果一样重时，根据国家进行排序
        inventory.sort(Comparator.comparing(FilteringApples.Apple::getWeight)
                .reversed()
                .thenComparing(FilteringApples.Apple::getColor)
        );

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
