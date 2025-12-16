package org.british.spoken.time.service;

import org.british.spoken.time.domain.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class BritishTimeConverterTest {

    private BritishTimeConverter converter;

    @BeforeEach
    void setUp() {
        converter = new BritishTimeConverter();
    }

    @Test
    void shouldRejectNullTime() {
        assertThatThrownBy(() -> converter.convertToSpoken(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Time cannot be null");
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, midnight",
        "12, 0, noon"
    })
    void shouldHandleSpecialTimes(int hour, int minute, String expected) {
        Time time = new Time(hour, minute);
        
        String result = converter.convertToSpoken(time);
        
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "1, 0, one o'clock",
        "13, 0, one o'clock",
        "23, 0, eleven o'clock"
    })
    void shouldHandleOnTheHour(int hour, int minute, String expected) {
        Time time = new Time(hour, minute);
        
        String result = converter.convertToSpoken(time);
        
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "9, 15, quarter past nine",
        "14, 15, quarter past two",
        "0, 15, quarter past twelve"
    })
    void shouldHandleQuarterPast(int hour, int minute, String expected) {
        Time time = new Time(hour, minute);
        
        String result = converter.convertToSpoken(time);
        
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "9, 30, half past nine",
        "14, 30, half past two",
        "0, 30, half past twelve"
    })
    void shouldHandleHalfPast(int hour, int minute, String expected) {
        Time time = new Time(hour, minute);
        
        String result = converter.convertToSpoken(time);
        
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "9, 45, quarter to ten",
        "14, 45, quarter to three",
        "23, 45, quarter to twelve"
    })
    void shouldHandleQuarterTo(int hour, int minute, String expected) {
        Time time = new Time(hour, minute);
        
        String result = converter.convertToSpoken(time);
        
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "9, 5, five past nine",
        "14, 20, twenty past two",
        "0, 10, ten past twelve"
    })
    void shouldHandleMinutesPast(int hour, int minute, String expected) {
        Time time = new Time(hour, minute);
        
        String result = converter.convertToSpoken(time);
        
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "9, 35, twenty five to ten",
        "14, 40, twenty to three",
        "23, 55, five to twelve"
    })
    void shouldHandleMinutesTo(int hour, int minute, String expected) {
        Time time = new Time(hour, minute);
        
        String result = converter.convertToSpoken(time);
        
        assertThat(result).isEqualTo(expected);
    }
}