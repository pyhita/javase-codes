package com.yangtao.java8.ch11.v1;

import com.yangtao.java8.ch11.v2.ExchangeService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: kante_yang
 * @Date: 2023/10/8
 */
public class BestPriceFinder {

    private final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));

    private final Executor executor = Executors.newFixedThreadPool(shops.size(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    public List<String> findPricesSequential(String product) {
        return shops.stream()
                .map(shop -> shop.getName() + " price is " + shop.getPrice(product))
                .collect(Collectors.toList());
    }

    public List<String> findPriceParallel(String product) {
        return shops.parallelStream()
                .map(shop -> shop.getName() + " price is " + shop.getPrice(product))
                .collect(Collectors.toList());
    }

    public List<String> findPricesFuture(String product) {
        // 异步计算所有商品的价格
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product),
                        executor))
                .collect(Collectors.toList());

        // 等待所有异步操作结束
        return priceFutures.stream()
                .map(CompletableFuture::join) // join相当于Future get 将结果进行聚合
                .collect(Collectors.toList());
    }

    public List<String> findPricesInUSD(String product) {
        Stream<CompletableFuture<String>> priceFuture = shops.stream()
                .map(shop -> CompletableFuture
                        .supplyAsync(() -> shop.getPrice(product))
                        .thenCombine(CompletableFuture.supplyAsync(() -> ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD)),
                                (price, rate) -> price * rate)
                        .thenApply(price -> shop.getName() + " price is " + price));

        return priceFuture.map(CompletableFuture::join).collect(Collectors.toList());
    }






}
