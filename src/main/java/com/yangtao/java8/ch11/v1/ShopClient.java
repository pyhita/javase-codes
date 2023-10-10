package com.yangtao.java8.ch11.v1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author: kante_yang
 * @Date: 2023/10/8
 */
public class ShopClient {

    public static void main(String[] args) {
        Shop shop = new Shop("Tao Shop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("ma po dou fu");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);

        System.out.println("Invocation returned after " + invocationTime
                + " msecs");

        doSthElse();

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
            long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
            System.out.println("Price returned after " + retrievalTime + " msecs");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void doSthElse() {
        System.out.println("do something else ...");
    }
}
