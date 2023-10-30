package com.yangtao.java_logic.ch5.ch5_4;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: kante_yang
 * @Date: 2023/10/16
 */
public class EnumTest {

    public static void main(String[] args) {
        Size size = Size.SMALL;
        System.out.println(size.name());
        System.out.println(size.toString());
        System.out.println(size.ordinal());

        // 默认通过ordinal的值进行比较
        Size size1 = Size.MEDIUM;
        System.out.println(size.compareTo(size1));

        // value of 方法：返回字符串对应的枚举值
        System.out.println(Size.SMALL == Size.valueOf("SMALL"));

        // values：返回所有的枚举值
        for (Size value : Size.values()) {
            System.out.println(value.name());
        }
    }

    private void onChose(Size size) {
        switch (size) {
            case SMALL:
                System.out.println("small size");
                break;
            case MEDIUM:
                System.out.println("medium size");
                break;
            case LARGE:
                System.out.println("large size");
                break;
        }
    }


    @Test
    public void test() {
        List<Integer> numbers1 = Arrays.asList(1, 2);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> collect = numbers1.stream()
                .flatMap(n1 -> numbers2.stream().map(n2 -> new int[]{n1, n2}))
                .collect(Collectors.toList());

        collect.forEach(n -> System.out.println(Arrays.toString(n)));
    }

}
