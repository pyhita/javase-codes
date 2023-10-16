package com.yangtao.java8.ch12;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: kante_yang
 * @Date: 2023/10/10
 */
public class DateTimeExamples {

    private static final ThreadLocal<DateFormat> formatters = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("dd-MMM-yyyy");
        }
    };

    public static void main(String[] args) {


    }

    @Test
    public void userOldDate() {
        Date date = new Date(114, 2, 18);
        System.out.println(date);

        System.out.println(formatters.get().format(date));

        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, Calendar.FEBRUARY, 18);
        System.out.println(calendar);
    }

    @Test
    public void useLocalDate() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear();  // 2014
        Month month = date.getMonth(); // MARCH
        int dayOfMonth = date.getDayOfMonth(); // 18
        DayOfWeek dayOfWeek = date.getDayOfWeek(); // TUESDAY
        int len = date.lengthOfMonth(); // 31
        boolean leap = date.isLeapYear(); // false not a leap year
        System.out.println(date);
        LocalDate now = LocalDate.now();
        System.out.println(now); // 2023-10-10
        // 传递一个TemporalField参数给get方法拿到同样的信息。
        int y = date.get(ChronoField.YEAR);
        int m = date.get(ChronoField.MONTH_OF_YEAR);
        int d = date.get(ChronoField.DAY_OF_MONTH);

        System.out.println(y);
    }

    @Test
    public void useLocalTime() {
        LocalTime time = LocalTime.of(13, 24, 20); // 13:45:20
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.println(time);
    }

    @Test
    public void userLocalDateTime() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        LocalDateTime dt1 = LocalDateTime.of(2023, 10, 10, 10, 7, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(time);
        LocalDateTime dt4 = time.atDate(date);
        System.out.println(dt1);

        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();
    }

    @Test
    public void useInstant() {
        Instant instant = Instant.ofEpochSecond(44 * 364 * 86400);
        Instant now = Instant.now();

        System.out.println(now);
        System.out.println(now.getEpochSecond());
    }

    @Test
    public void useDuration() {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2014, 9, 11);
        LocalTime time1 = LocalTime.now();
        LocalTime time2 = LocalTime.of(14, 22, 30);

        // 使用Duration无法比较LocalDate
//        Duration d1 = Duration.between(date1, date2);
        Duration d2 = Duration.between(time1, time2);
        System.out.println(d2.getSeconds());

        Instant instant = Instant.ofEpochSecond(44 * 365 * 86400);
        Instant now = Instant.now();
        Duration d3 = Duration.between(instant, now);
        System.out.println(d3.getSeconds());

        // 可以使用Period进行比较
        Period tenDays = Period.between(LocalDate.of(2023, 10, 1),
                LocalDate.of(2023, 10, 11));
        System.out.println(tenDays.getDays());

        // 通过工具类，直接创建对应的实例
        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);
        Period tenDays2 = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
    }

    @Test
    public void updateDate() {
        LocalDate date = LocalDate.now();
        LocalDate date1 = date.withYear(2011);
        LocalDate date2 = date.withMonth(9);
        LocalDate date3 = date.with(ChronoField.YEAR, 1);

        // 通过相对的方式修改LocalDate对象的属性
        LocalDate plusWeeks = date.plusWeeks(1);
        LocalDate plusMonths = date.plusMonths(1);
        LocalDate minusYears = date.minusYears(3);
        LocalDate plus = date.plus(1, ChronoUnit.YEARS);
    }

    @Test
    public void useTemporalAdjuster() {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)); // 2014 3 23
        LocalDate date3 = date1.with(TemporalAdjusters.lastDayOfMonth()); // 2014 3 31
    }

    @Test
    public void useLocalDateTimeFormatter() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        String s2 = date.format(DateTimeFormatter.BASIC_ISO_DATE); // 20140318
        String s1 = date.format(DateTimeFormatter.ISO_LOCAL_DATE); // 2014-03-18
        System.out.println(s1 + " " + s2);

        LocalDate date1 = LocalDate.parse("20140318",
                DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2014-03-18",
                DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Test
    public void useLocalDateTimeFormatter2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate now = LocalDate.now();
        System.out.println(now.format(formatter));

        LocalDate parse = LocalDate.parse("10/11/2023", formatter);
        System.out.println(parse);
    }

    @Test
    public void userZoneId() {
        ZoneId romeZone = ZoneId.of("Europe/Rome");

        LocalDate now = LocalDate.now();
        ZonedDateTime zdt1 = now.atStartOfDay(romeZone);

        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.of(now, time);
        ZonedDateTime ztd2 = dateTime.atZone(romeZone);

        Instant instant = Instant.now();
        ZonedDateTime ztd3 = instant.atZone(romeZone);

        // 通过ZoneId 还可以实现LocalDateTime和Instant的相互转换
        LocalDateTime localTime = LocalDateTime.ofInstant(instant, romeZone);
        Instant instant1 = localTime.toInstant((ZoneOffset) romeZone);

    }
}
