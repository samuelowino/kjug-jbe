package com.kenyajug.encounter.core;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public interface DateTimeUtils {
    static String localDateToString(LocalDate localDate){
        var formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formater.format(localDate);
    }
}
