package com.jsxl.dateApi;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

public class T5_DateApi {

    @Test
    public void test1(){
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }

    @Test
    public void test2(){
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Europe/Tallinn"));
        System.out.println(ldt);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("Europe/Tallinn"));
        System.out.println(zdt);
        ZonedDateTime zdt1 = ldt.atZone(ZoneId.of("Europe/Isle_of_Man"));
        System.out.println(zdt1);
//        2021-11-23T05:23:40.243
//        2021-11-23T05:23:40.243+02:00[Europe/Tallinn]
//        2021-11-23T05:23:40.243Z[Europe/Isle_of_Man]
    }

}
