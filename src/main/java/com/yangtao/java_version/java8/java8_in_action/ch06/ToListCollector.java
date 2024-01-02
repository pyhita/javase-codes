package com.yangtao.java_version.java8.java8_in_action.ch06;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @Author: kante_yang
 * @Date: 2023/9/26
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {


    /**
     * 产生一个空的累加器
     * @return
     */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    /**
     * 将元素添加到结果容器
     * @return
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {

        return List::add;
    }

    /**
     * 合并两个结果容器
     * @return
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * 对结果容器进行最终转换
     * @return
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
