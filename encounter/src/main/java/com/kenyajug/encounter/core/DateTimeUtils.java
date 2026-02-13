package com.kenyajug.encounter.core;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public interface DateTimeUtils {
    String DATE_FORMAT = "yyyy-MM-dd";
    DateTimeFormatter formater = DateTimeFormatter.ofPattern(DATE_FORMAT);
    static String localDateToString(LocalDate localDate){
        return formater.format(localDate);
    }
    static LocalDate stringToLocalDate(String dateText){
        return LocalDate.parse(dateText);
    }
}
