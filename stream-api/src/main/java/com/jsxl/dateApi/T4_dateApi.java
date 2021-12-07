package com.jsxl.dateApi;

        import lombok.extern.log4j.Log4j;
        import lombok.extern.log4j.Log4j2;
        import org.junit.Test;
        import org.springframework.format.annotation.DateTimeFormat;

        import java.time.DayOfWeek;
        import java.time.Duration;
        import java.time.Instant;
        import java.time.LocalDate;
        import java.time.LocalDateTime;
        import java.time.Period;
        import java.time.ZoneOffset;
        import java.time.format.DateTimeFormatter;
        import java.time.temporal.TemporalAdjuster;
        import java.time.temporal.TemporalAdjusters;

@Log4j2
public class T4_dateApi {
    //1.LocalDate LocalTime LocalDateTime
    @Test
    public void test1(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        LocalDateTime ldt2 = LocalDateTime.of(2021, 11, 23, 10, 37, 59);
        System.out.println(ldt2);
        log.info(ldt.plusYears(2));
        log.info(ldt.minusMonths(2));
        log.info(ldt.getYear());
        log.info(ldt.getMonth());
        log.info(ldt.getMonthValue());
        log.info(ldt.getDayOfMonth());
        log.info(ldt.getHour());
    }

    //Instant : 时间戳（以Unix 元年：1970年1月1日00：00：00到某个时间之间的毫秒值）
    @Test
    public void test2(){
        Instant ins1 = Instant.now();//默认获取UTC时区
        log.debug(ins1);
        log.debug(ins1.atOffset(ZoneOffset.ofHours(8)));
        log.debug(ins1.toEpochMilli());
        log.debug(Instant.ofEpochSecond(60));
//        10:48:59.644 [main] DEBUG com.jsxl.dateApi.T4_dateApi - 2021-11-23T02:48:59.632Z
//        10:48:59.648 [main] DEBUG com.jsxl.dateApi.T4_dateApi - 2021-11-23T10:48:59.632+08:00
//        10:48:59.648 [main] DEBUG com.jsxl.dateApi.T4_dateApi - 1637635739632
//        10:48:59.648 [main] DEBUG com.jsxl.dateApi.T4_dateApi - 1970-01-01T00:01:00Z
    }
    //Duration:计算两个"时间"之间的间隔
    @Test
    public void test3(){
        Instant ins1 = Instant.now();

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
        }
        Instant ins2 = Instant.now();
        log.error(Duration.between(ins1,ins2).toMillis());
    }
    //Period:计算两个"日期"之间的间隔
    @Test
    public void test4(){
        LocalDate last = LocalDate.of(2019, 12, 30);
        LocalDate now = LocalDate.now();
        Period period = Period.between(last, now);
        log.warn(period);
        log.warn(period.getYears());
        log.warn(period.getMonths());
        log.warn(period.getDays());
//        10:58:28.312 [main] WARN com.jsxl.dateApi.T4_dateApi - P1Y10M24D
//        10:58:28.316 [main] WARN com.jsxl.dateApi.T4_dateApi - 1
//        10:58:28.316 [main] WARN com.jsxl.dateApi.T4_dateApi - 10
//        10:58:28.316 [main] WARN com.jsxl.dateApi.T4_dateApi - 24
    }
    //TemporalAdjuster:时间矫正器
    @Test
    public void test5(){
        LocalDateTime now = LocalDateTime.now();
        log.fatal(now);
        log.fatal(now.withDayOfMonth(10));
        log.fatal(now.with(TemporalAdjusters.next(DayOfWeek.FRIDAY.FRIDAY)));
//        11:03:37.047 [main] ERROR com.jsxl.dateApi.T4_dateApi - 2021-11-23T11:03:37.043
//        11:03:37.051 [main] ERROR com.jsxl.dateApi.T4_dateApi - 2021-11-10T11:03:37.043
//        11:03:37.053 [main] ERROR com.jsxl.dateApi.T4_dateApi - 2021-11-26T11:03:37.043
        //自定义下一个工作日
        LocalDateTime nextWork =now.with( l -> {
          LocalDateTime ldt = (LocalDateTime) l ;
            DayOfWeek dow = ldt.getDayOfWeek();
            if(dow.equals(DayOfWeek.FRIDAY)){
                return ldt.plusDays(3);
            }else if(dow.equals(DayOfWeek.THURSDAY)) {
                return ldt.plusDays(4);
            }else if(dow.equals(DayOfWeek.WEDNESDAY)) {
                return ldt.plusDays(5);
            }else if(dow.equals( DayOfWeek.TUESDAY)) {
                return ldt.plusDays(6);
            }else {
                return ldt.plusDays(7);
            }
        });
        log.fatal(nextWork);
    }

    //DateTimeFormatter:格式化时间日期
    @Test
    public void test6(){
        DateTimeFormatter isoDate = DateTimeFormatter.ISO_DATE;
        LocalDateTime now = LocalDateTime.now();
        log.info(now.format(isoDate));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String format = dtf.format(now);
        log.info(format);

        log.info(now.parse(format,dtf));
//        11:17:05.131 [main] INFO com.jsxl.dateApi.T4_dateApi - 2021-11-23
//        11:17:05.134 [main] INFO com.jsxl.dateApi.T4_dateApi - 2021年11月23日 11:17:05
//        11:17:05.135 [main] INFO com.jsxl.dateApi.T4_dateApi - 2021-11-23T11:17:05
    }

}
