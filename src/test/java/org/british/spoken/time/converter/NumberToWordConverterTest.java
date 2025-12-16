package org.british.spoken.time.converter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class NumberToWordConverterTest {

    @ParameterizedTest
    @CsvSource({
        "0, ''",
        "1, one",
        "5, five",
        "10, ten",
        "15, quarter",
        "20, twenty",
        "25, twenty five",
        "29, twenty nine"
    })
    void shouldConvertMinutesToWords(int minute, String expectedWord) {
        String result = NumberToWordConverter.minuteToWord(minute);
        
        assertThat(result).isEqualTo(expectedWord);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 30, 60})
    void shouldRejectInvalidMinutes(int invalidMinute) {
        assertThatThrownBy(() -> NumberToWordConverter.minuteToWord(invalidMinute))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Minute must be between 0 and");
    }

    @ParameterizedTest
    @CsvSource({
        "0, twelve",
        "1, one", 
        "6, six",
        "11, eleven"
    })
    void shouldConvertHoursToWords(int hour, String expectedWord) {
        String result = NumberToWordConverter.hourToWord(hour);
        
        assertThat(result).isEqualTo(expectedWord);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 12, 13})
    void shouldRejectInvalidHours(int invalidHour) {
        assertThatThrownBy(() -> NumberToWordConverter.hourToWord(invalidHour))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Hour must be between 0 and");
    }
}