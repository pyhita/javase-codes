package com.yangtao.java8.ch05;

/**
 * @Author: kante_yang
 * @Date: 2023/12/25
 */

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

/**
 * 测试流的过滤操作
 */
public class Distincting {



    /**
     * 对对象集合进行去重
     */
    @Test
    public void distinctListObjects() {
        List<Book> bookList = Arrays.asList(
            Book.builder().name("猫城记").price(22).build(),
            Book.builder().name("离婚").price(33).build(),
            Book.builder().name("骆驼祥子").price(44).build(),
            Book.builder().name("猫城记").price(22).build()
        );

        List<Book> distinctedList = bookList.stream()
            .distinct()
            .collect(Collectors.toList());

        System.out.println(distinctedList.size());
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Book {
        private String name;
        private Integer price;

        @Override
        public boolean equals(Object obj) {
            if (Objects.isNull(obj)) return false;

            Book book = (Book) obj;
            if (this == book) {
                return true;
            } else {
                return (Objects.equals(this.name, book.getName()))
                    && (Objects.equals(this.price, book.getPrice()));
            }
        }

        @Override
        public int hashCode() {
            int hashno = 7;
            hashno = 13 * hashno + (name == null ? 0 : name.hashCode());
            return hashno;
        }
    }

    /**
     * 通过对象的property 进行去重
     */

    @Test
    public void distinctListByObjectProperty() {
        List<Book> bookList = Arrays.asList(
            Book.builder().name("猫城记").price(22).build(),
            Book.builder().name("骆驼祥子").price(33).build(),
            Book.builder().name("骆驼祥子").price(33).build(),
            Book.builder().name("猫城记").price(22).build()
        );

        List<Book> books = bookList.stream()
            .filter(distinctByKey(Book::getName))
            .collect(Collectors.toList());

        for (Book book : books) {
            System.out.println(book);
        }
    }

    private  <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }



}
