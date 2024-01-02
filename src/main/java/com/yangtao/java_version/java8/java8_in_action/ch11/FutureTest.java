package com.yangtao.java_version.java8.java8_in_action.ch11;

import java.util.concurrent.*;

/**
 * @Author: kante_yang
 * @Date: 2023/10/8
 */
public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<Double> future = executor.submit(() -> {
            // 将比较耗时操作提交给线程池处理，避免了main thread 被阻塞
            int i = 0;
            while (i < 1000000) {
                i++;
            }
            return 0.5;
        });

        // 主线程继续处理其他事情
        System.out.println("hello ...");

        // 阻塞等待计算结果，最多等待一秒
        Double result = future.get(1, TimeUnit.SECONDS);
        System.out.println(result);
        executor.shutdown();
    }
}
