package com.yangtao.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest_01 {

    // 1 过滤出符合条件的元素
    @Test
    public void filter() {
        List<String> numbers = Arrays.asList("13378520000","13278520000","13178520000","13358520000");
        // 1 通过stream 过滤出前缀是133 的元素
        List<String> filterNums = numbers.stream().filter(s -> s.startsWith("133")).collect(Collectors.toList());
        System.out.println(filterNums);

        // 2 通过stream 过滤出大于 100 的元素
        List<Integer> nums = Arrays.asList(11, 102, 500, 0, -1);
        List<Integer> filterNumbers = nums.stream().filter(num -> judeMoreThan100(num)).collect(Collectors.toList());
    }

    private boolean judeMoreThan100(Integer num) {
        return num > 100;
    }

    // 2 map 映射元素
    // 假设有一个手机号字符列表，需要根据前7位来确定手机号归属地，那么就需要获取所有手机号前7位子字符串，可以使用map()方法实现：
    @Test
    public void map1() {
        List<String> numbers = Arrays.asList("13378520000","13278520000","13178520000","13558520000");
        List<String> mapNumbers = numbers.stream().map(s -> s.substring(0, 7)).collect(Collectors.toList());
        System.out.println(mapNumbers);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class People {
        private String name;
        private String tel;
    }
    // 假设有一个用户对象列表，我们需要提取其中每个对象的手机号，可以使用map()方法实现：
    @Test
    public void map2() {
        List<People> peopleList = Arrays.asList(
                new People("王二","13378520000"),
                new People("李二","13278520000"),
                new People("张四","13178520000")
        );

        List<String> tels = peopleList.stream().map(People::getTel).collect(Collectors.toList());
        System.out.println(tels);
    }


    // 3 filterMap：多个流合并成一个流
    // 假设有两组余额列表A和B，需要将A组每个元素都与B组所有元素依次进行相加，可以使用flatMap实现该多对多的映射——
    @Test
    public void flatMap1() {
        List<Integer> listA = Arrays.asList(1, 2, 3);
        List<Integer> listB = Arrays.asList(4, 5, 6);
        List<Integer> resList = listA.stream().flatMap(a -> listB.stream().map(b -> a + b)).collect(Collectors.toList());
        System.out.println(resList);
    }

    // 假设有一个包含多个手机号字符串列表的列表，现在需要合并所有手机号字符串成为一个列表，可以使用flatMap()方法实现：
    @Test
    public void flatMap2() {
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("13378520000", "13278520000"),
                Arrays.asList("13178520000", "13558520000"),
                Arrays.asList("15138510000", "15228310000")
        );

        List<String> resList = listOfLists.stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(resList);
    }


    // 4 去除重复元素
    // 假设有一个包含重复手机号字符串的列表，可以使用distinct()去重操作
    @Test
    public void distinct() {
        List<String> numbers = Arrays.asList("13378520000", "15138510000","13178520000", "15138510000");
        List<String> distinctNums = numbers.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctNums);
    }

    // 5 排序元素
    // default：升序排序
    @Test
    public void sort1() {
        List<People> peopleList = Arrays.asList(
                new People("王二","20"),
                new People("李二","30"),
                new People("张四","31")
        );

        List<People> sortedList = peopleList.stream().
                sorted(Comparator.comparing(people -> Integer.parseInt(people.tel))).collect(Collectors.toList());
        System.out.println(sortedList);
    }

    // 降序排序
    @Test
    public void sort2() {
        List<People> peopleList = Arrays.asList(
                new People("王二","20"),
                new People("李二","30"),
                new People("张四","31")
        );

        List<People> sortedList = peopleList.stream().
                sorted(Comparator.comparing(People::getTel).reversed()).collect(Collectors.toList());
        System.out.println(sortedList);
    }

    // 6 peek 查看每个元素的信息，但是不修改流中元素的状态 可以在流中的任何阶段使用，不会影响到流的操作，也不会终止流的操作。
    @Test
    public void peek() {
        List<String> telList = Arrays.asList("13378520000","13278520000","13178520000","13558520000");
        List<String> resList = telList.stream().peek(t -> System.out.println(t))
                .map(t -> t.substring(0, 3))
                .peek(t -> System.out.println(t))
                .collect(Collectors.toList());

    }

    // 7 limit 和 skip
    /**
     * limit()和skip()都是用于截取Stream流中部分元素的方法，
     * 两者区别在于，limit()返回一个包含前n个元素的新流，skip()则返回一个丢弃前n个元素后剩余元素组成的新流。
     */
    @Test
    public void limitAndSkip() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Arrays.stream(arr).limit(5).forEach(t -> System.out.println(t));
        Arrays.stream(arr).skip(5).forEach(t -> System.out.println(t));
    }


}
