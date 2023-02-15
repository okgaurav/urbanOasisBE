package com.mongo.backend.model.utils;


import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.mongo.backend.model.ModelConstants.DATE_FORMAT;
import static com.mongo.backend.model.ModelConstants.DATE_TIME_FORMAT;
import static com.mongo.backend.model.ModelConstants.IND_TIME_ZONE;
import static com.mongo.backend.model.ModelConstants.TIME_FORMAT;

@Slf4j
public class Time {
    public static String getCurrentDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        LocalDateTime now = LocalDateTime.now(ZoneId.of(IND_TIME_ZONE));
        return dtf.format(now);
    }
    public static String getDateTimeDelay(Long secounds){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        LocalDateTime now = LocalDateTime.now(ZoneId.of(IND_TIME_ZONE)).plusSeconds(secounds);
        return dtf.format(now);
    }
    public static String getDate(String dateTime){
        return dateTime.substring(0,dateTime.indexOf(" ")+1);
    }
    public static String getTime(String dateTime){
        return dateTime.substring(dateTime.indexOf(" ")+1);
    }
    @Deprecated
    public static Long getDateDifference(String xdate, String ydate){
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
            Date dateBefore = new Date();
            Date dateAfter = new Date();
            try {
                dateBefore = sdf.parse(xdate);
                dateAfter = sdf.parse(ydate);
            }catch (Exception e){
                log.error(e.getMessage());
            }
            long timeDiff = Math.abs(dateAfter.getTime() - dateBefore.getTime());
            long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
            return daysDiff;
    }
    @Deprecated
    public static Long getTimeDifference(String xtime, String ytime){
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT, Locale.ENGLISH);
        Date dateBefore = new Date();
        Date dateAfter = new Date();
        try {
            dateBefore = sdf.parse(xtime);
            dateAfter = sdf.parse(ytime);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        long timeDiff = Math.abs(dateAfter.getTime() - dateBefore.getTime());
        long tDiff = TimeUnit.SECONDS.convert(timeDiff, TimeUnit.MILLISECONDS);
        return tDiff;
    }
    @Deprecated
    public static Long getDateTimeDifference(String xdateTime, String ydateTime){
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.ENGLISH);
        Date dateBefore = new Date();
        Date dateAfter = new Date();
        try {
            dateBefore = sdf.parse(xdateTime);
            dateAfter = sdf.parse(ydateTime);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        long timeDiff = Math.abs(dateAfter.getTime() - dateBefore.getTime());
        long tDiff = TimeUnit.SECONDS.convert(timeDiff, TimeUnit.MILLISECONDS);
        return tDiff;
    }
    public static long getCommonDifference(String x, String y, String format, TimeUnit timeUnit){
        /*
        * timeUnit : Instance of TimeUnit (Enum)
        * Options : TimeUnit.SECONDS, TimeUnit.MINUTES, TimeUnit.DAYS
        *
        */

        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        Date dateBefore = new Date();
        Date dateAfter = new Date();
        try {
            dateBefore = sdf.parse(x);
            dateAfter = sdf.parse(y);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        long timeDiff = Math.abs(dateAfter.getTime() - dateBefore.getTime());
        long tDiff = timeUnit.convert(timeDiff, TimeUnit.MILLISECONDS);

        return tDiff;
    }

    public static long fromHrs(int hrs){
        return hrs*60*60;
    }
    public static long fromDays(int days){
        return days*fromHrs(24);
    }

    public static void main(String[] args) throws ParseException {
        getDateDifference("15/02/2023","17/02/2023");
        getTimeDifference("23:41:24","23:43:24");
        System.out.println(getCommonDifference(getCurrentDateTime(),getDateTimeDelay(fromHrs(24)),DATE_TIME_FORMAT,TimeUnit.SECONDS));
    }
}
