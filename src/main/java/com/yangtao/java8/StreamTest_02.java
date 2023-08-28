package com.yangtao.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Test Stream 终止操作
 */
public class StreamTest_02 {

    // 1 forEach 遍历每一个元素

    // 2 count：统计流中元素的数量
    // 假设有一个包含多个手机号字符串的列表，需要统计去重后的手机号数量，就可以使用count方法
    @Test
    public void count() {
        List<String> numbers = Arrays.asList("13378520000", "15138510000","13178520000", "15138510000");
        long count = numbers.stream().distinct().count();
        System.out.println(count);
    }

    // 3 reduce：将流中所有元素结合成一个结果
    // 假设有一个包含多个手机号字符串的List列表，需要在去重之后，再将列表所有字符串拼按照逗号间隔接成一个字符串返回，那么就可以通过reduce来实现
    @Test
    public void reduce() {
        List<String> numbers = Arrays.asList("13378520000", "15138510000","13178520000", "15138510000");
        Optional resStr = numbers.stream()
                .distinct()
                .reduce((a, b) -> a + "," + b);
        System.out.println(resStr.get());
    }

    // 4 collect：将流中的元素收集到一个容器中，并返回该容器。
    // 除里toList()之外，还提供了例如toSet，toMap等方法满足不同的场景

    // 5 min 和 max
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class People {
        private String name;
        private Integer age;

    }
    @Test
    public void minAndMax() {
        List<People> peopleList = Arrays.asList(
                new People("王二",20),
                new People("李二",30),
                new People("张四",31)
        );

        // 找出年龄最小的people
        People min = peopleList.stream().min(Comparator.comparing(People::getAge)).orElse(null);
        System.out.println(min);
    }

    // 6 anyMatch、allMatch 和 noneMatch：判断流中是否存在满足指定条件的元素。
    // anyMatch用于判断，如果流中至少有一个元素满足给定条件，那么返回true，反之返回false，即 true||false为true这类的判断。
    // 假设在一个手机号字符串的List列表当中，判断是否包含前缀为“153”的手机号，就可以使用anyMatch
    @Test
    public void anyMatch() {
        List<String> numbers = Arrays.asList("13378520000", "15138510000","13178520000", "15338510000");
        boolean res = numbers.stream().anyMatch(num -> num.startsWith("153"));
        System.out.println(res);
    }

    // allMatch用于判断，流中的所有元素是否都满足给定条件，满足返回true，反之false，即true&&false为false这类判断。
    //  假设在一个手机号字符串的List列表当中，判断手机号是否都满足前缀为“153”的手机号
    @Test
    public void allMatch() {
        List<String> numbers = Arrays.asList("13378520000", "15138510000","13178520000", "15338510000");
        boolean res = numbers.stream().allMatch(num -> num.startsWith("153"));
        System.out.println(res);
    }

    // noneMatch :noneMatch用于判断，如果流中没有任何元素满足给定的条件，返回true
    @Test
    public void noneMatch() {

    }


    // 7 findFirst 和 findAny

    


}
