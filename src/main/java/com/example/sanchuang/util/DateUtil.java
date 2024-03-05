package com.example.sanchuang.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    public static Date wrapDate(String date, String time) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        // 解析日期字符串为LocalDate
        LocalDate localDate = LocalDate.parse(date, dateFormatter);
        // 解析时间字符串为LocalTime
        LocalTime localTime = LocalTime.parse(time, timeFormatter);
        // 将LocalDate和LocalTime合并成LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        // 转换LocalDateTime为Date
        // 注意：这将会转换为UTC时区的Date对象，因为Date类不包含时区信息
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
