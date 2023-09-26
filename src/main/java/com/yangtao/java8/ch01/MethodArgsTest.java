package com.yangtao.java8.ch01;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.text.FieldPosition;

/**
 * @Author: kante_yang
 * @Date: 2023/9/25 10:49
 */
public class MethodArgsTest {

    // 方法参数对比， 语法 ::

    /**
     * 没有方法参数 之前
     */
    @Test
    public void test1() {
        // 列出所有的隐藏文件
        new File("c://").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });

        // lamba表达式简化：
        new File("c://").listFiles(file -> file.isHidden());

        // 方法表达式
        new File("c://").listFiles(File::isHidden);
    }
}
