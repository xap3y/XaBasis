package eu.xap3y.xabasis.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static String getTimeWithoutDate() {
        // HH:mm:ss.S
        return LocalTime.now().toString().substring(0, 10);
    }

    public static String getTimeDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM"));
    }
}
