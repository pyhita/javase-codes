package com.yangtao.java8.ch03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;

/**
 * @Author: kante_yang
 * @Date: 2023/9/25 14:25
 */
public class ExecuteAround {

    public static void main(String[] args) throws IOException {
        // 3 传递lamba
        // 读取一行
        processFile((BufferedReader r) -> r.readLine());
        // 读取两行
        processFile((BufferedReader r) -> r.readLine() + r.readLine());
    }


    // 1 创建函数式接口
    @FunctionalInterface
    interface BufferedReadProcess {
        String process(BufferedReader bufferedReader) throws IOException;
    }

    // 2 创建方法
    public static String processFile(BufferedReadProcess bufferedReadProcess) throws IOException {
        try (BufferedReader reader =
                new BufferedReader(new FileReader("data.txt"))) {
            return bufferedReadProcess.process(reader);
        }
    }
}
