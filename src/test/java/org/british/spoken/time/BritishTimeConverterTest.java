package org.british.spoken.time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BritishTimeConverterTest {

    private BritishTimeConverter converter;

    @BeforeEach
    void setUp() {
        converter = new BritishTimeConverter();
    }

    @ParameterizedTest
    @CsvSource({
            "00:00, midnight",
            "12:00, noon",
            "01:00, one o'clock",
            "13:00, one o'clock",
            "02:00, two o'clock",
            "23:00, eleven o'clock",
            "02:05, five past two",
            "03:10, ten past three",
            "04:15, quarter past four",
            "05:20, twenty past five",
            "06:25, twenty five past six",
            "07:30, half past seven",
            "00:30, half past twelve",
            "12:30, half past twelve",
            "07:35, twenty five to eight",
            "08:40, twenty to nine",
            "09:45, quarter to ten",
            "10:50, ten to eleven",
            "11:55, five to twelve",
            "07:31, twenty nine to eight",
            "07:59, one to eight",
            "14:05, five past two",
            "21:45, quarter to ten",
            "23:30, half past eleven",
            "00:01, one past twelve",
            "23:59, one to twelve",
            "11:29, twenty nine past eleven",
            "11:31, twenty nine to twelve"
    })
    void testValidTimeConversions(String input, String expected) {
        assertThat(converter.convertToSpoken(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "12, Invalid time format. Please use HH:MM format.",
            "12:30:45, Invalid time format. Please use HH:MM format.",
            "abc:def, Invalid time format. Please use HH:MM format.",
            "'', Invalid time format. Please use HH:MM format."
    })
    void testInvalidFormats(String input, String expected) {
        assertThat(converter.convertToSpoken(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "25:00, 'Invalid time. Hours must be 0-23, minutes must be 0-59.'",
            "12:60, 'Invalid time. Hours must be 0-23, minutes must be 0-59.'",
            "-1:30, 'Invalid time. Hours must be 0-23, minutes must be 0-59.'",
            "12:-5, 'Invalid time. Hours must be 0-23, minutes must be 0-59.'"
    })
    void testInvalidTimes(String input, String expected) {
        assertThat(converter.convertToSpoken(input)).isEqualTo(expected);
    }
}