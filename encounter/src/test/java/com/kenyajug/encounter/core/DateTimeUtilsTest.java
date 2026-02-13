package com.kenyajug.encounter.core;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
class DateTimeUtilsTest {
    @Test
    void localDateToString() {
        var date = LocalDate.of(1900,12,24);
        var expectedString = "1900-12-24";
        var actualString = DateTimeUtils.localDateToString(date);
        assertThat(actualString).isEqualTo(expectedString);
    }
    @Test
    void textToLocalDateTest(){
        var text = "1900-12-24";
        var expectedDate = LocalDate.of(1900,12,24);
        var actualDate = DateTimeUtils.stringToLocalDate(text);
        assertThat(actualDate).isNotNull();
        assertThat(actualDate.isEqual(expectedDate)).isTrue();
    }
}