package edu.dhs.bookmanagementsystem.common.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @program: book-management-system
 * @description:
 * @author: Zhenlong Yang
 * @create: 2023-04-05 23:07
 **/
public class DateUtils {
    public static LocalDate parseDateTime(String dateStr) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateStr);
        return zonedDateTime.withZoneSameInstant(ZoneId.of("Australia/Darwin")).toLocalDate();
    }
}
