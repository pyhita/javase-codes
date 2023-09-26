package com.yangtao.java8.ch02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: kante_yang
 * @Date: 2023/9/25 13:44
 */
public class FilteringApples {

    public static void main(String[] args) {

        List<Apple> inventory = Arrays.asList(
                new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples2 = filterApples(inventory, new AppleGrennPredicate());
        System.out.println(greenApples2);

        // [Apple{color='green', weight=155}]
        List<Apple> heavyApples = filterApples(inventory, new AppleWeightPredicate());
        System.out.println(heavyApples);

        // 使用匿名类来简化啰嗦的代码
        List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });
        System.out.println(redApples);

        // 使用lamba表达式进行简化
        List<Apple> redApples2 = filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));
        System.out.println(redApples2);

        // 方法参数化 实现sort
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight());
        System.out.println(inventory);
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate applePredicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (applePredicate.test(apple)) {
                result.add(apple);
            }
        }

        return result;
    }

    // 选择绿色苹果
    public static class AppleGrennPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return "green".equals(apple.getColor());
        }
    }

    // 选择较重的苹果
    public static class AppleWeightPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    interface ApplePredicate {
        boolean test(Apple apple);
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
