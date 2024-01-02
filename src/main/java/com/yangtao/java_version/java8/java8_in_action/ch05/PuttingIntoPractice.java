package com.yangtao.java_version.java8.java8_in_action.ch05;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: kante_yang
 * @Date: 2023/9/25 17:41
 */
public class PuttingIntoPractice {

    private List<Transaction> transactions;

    @Before
    public void before () {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

       transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

    }


    @Test
    public void test1() {
        System.out.println(transactions);
        // ex1
        List<Transaction> result = transactions.stream()
                .filter(transaction -> 2011 == transaction.getYear())
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        System.out.println(result);
    }

    @Test
    public void test2() {
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(cities);
    }

    @Test
    public void test3() {
        List<Trader> result = transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .distinct()
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        System.out.println(result);
    }

    @Test
    public void test4() {
        String tradeNames = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);

        System.out.println(tradeNames);
    }

    @Test
    public void test5() {
        boolean result = transactions.stream()
                .anyMatch(transaction -> "Milan".equals(transaction.getTrader().getCity()));
        System.out.println(result);
    }

    @Test
    public void test6() {
        transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(transaction -> transaction.getValue())
                .forEach(System.out::println);
    }

    @Test
    public void test7() {
        Integer max = transactions.stream()
                .map(transaction -> transaction.getValue())
                .reduce(0, Integer::max);
        System.out.println(max);
    }

    @Test
    public void test8() {
        Optional<Transaction> transaction = transactions.stream()
                .sorted(Comparator.comparing(Transaction::getValue))
                .findFirst();

        Optional<Transaction> min = transaction.stream()
                        .reduce((t1, t2) ->
                                t1.getValue() < t2.getValue() ? t1 : t2);

        System.out.println(min.get());
        System.out.println(transaction.get());
    }

}
