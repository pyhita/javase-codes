package com.yangtao.java_version.java8.java8_in_action.ch11.v2;

import static com.yangtao.java_version.java8.java8_in_action.ch11.v2.Util.delay;
import static com.yangtao.java_version.java8.java8_in_action.ch11.v2.Util.format;

/**
 * @Author: kante_yang
 * @Date: 2023/10/9
 */
public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }
    private static double apply(double price, Code code) {
        delay();
        return format(price * (100 - code.percentage) / 100);
    }
}
