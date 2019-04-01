package ru.yusdm.javacore.lesson17java8.lesson.part2;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;



public class DateTime {

    public static void main(String[] args) {
        //demoInstant();
        //demoLocalDateAndLocalDateTime();
       // demoZonedDateTime();
        demoFormatting();
    }

    private static void demoInstant() {

        Instant start = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant end = Instant.now();

        Duration duration = Duration.between(start, end);

        System.out.println(duration.toMillis());
    }

    private static void demoLocalDateAndLocalDateTime() {
        LocalDate now = LocalDate.now();
        System.out.println(now);
        System.out.println("----Demo LocalDate.of----------");
        LocalDate newDate_1 = LocalDate.of(2019, 12, 30);
        LocalDate newDate_2 = LocalDate.of(2019, Month.APRIL, 30);
        System.out.println(newDate_1);
        System.out.println(newDate_2);

        System.out.println("Compare result " + newDate_1.compareTo(newDate_2));

        System.out.println("-----Demo mutation--------");
        System.out.println(newDate_1);
        System.out.println(newDate_1.plusYears(100));
        System.out.println(newDate_1.plus(10, ChronoUnit.CENTURIES));
        System.out.println(newDate_1);

        System.out.println("-----Demo until--------");
        newDate_1 = LocalDate.of(2029, 12, 30);
        LocalDate now1 = LocalDate.now();

        long until = now1.until(newDate_1, ChronoUnit.DAYS);
        System.out.println("newDate_1 " + newDate_1);
        System.out.println("now1 " + now1);
        System.out.println("now1.until(newDate_1, ChronoUnit.DAYS) =  " + until);
        until = newDate_1.until(now1, ChronoUnit.DAYS);
        System.out.println("newDate_1.until(now1, ChronoUnit.DAYS) =  " + until);


        System.out.println("------Demo LocalDateTime-------");
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime.of(2000, 11, 1, 13, 20);
    }

    //Vasya Vladivistok - 15-00 (GMT)
    //Dmitry Spb- 3-00 (GMT)

    private static void demoZonedDateTime(){
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(now, ZoneId.of("Europe/Moscow"));
        System.out.println(zonedDateTime);
    }

    private static void demoFormatting() {
        try {
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("dd.MMM.yyyy HH:mm:ss z", new Locale("ru", "RU"));
            ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Europe/Moscow"));
            String dateAsStr = zdt.format(dateTimeFormatter);
            System.out.println(dateAsStr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
