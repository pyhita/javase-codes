package com.yangtao.java_version.java8.java8_in_action;

/**
 * @Author: kante_yang
 * @Date: 2023/9/5 12:13
 */

import org.junit.Test;

import java.util.Optional;

/**
 * 测试Optional
 */
public class StreamTest_04 {

    // Test ofNullable 和 isPresent 方法
    @Test
    public void test1() {
        String str = null;
        // 将一个可能为null的对象包装成 Optional对象
        Optional<String> optStr = Optional.ofNullable(str);

        if (optStr.isPresent()) {
            System.out.println("不为空");
        } else {
            System.out.println("为空");
        }
    }

    // 测试get方法
    @Test
    public void test2() {
        String str = null;
        // 将一个可能为null的对象包装成 Optional对象
        Optional<String> optStr = Optional.ofNullable(str);

        if (optStr.isPresent()) {
            System.out.println("不为空");
        } else {
            System.out.println("为空");
            System.out.println(optStr.get());
        }
    }
}

