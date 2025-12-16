package org.british.spoken.time.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class TimeTest {

    @Test
    void shouldCreateValidTime() {
        Time time = new Time(14, 30);
        
        assertThat(time.hour()).isEqualTo(14);
        assertThat(time.minute()).isEqualTo(30);
    }

    @Test
    void shouldCreateTimeUsingFactoryMethod() {
        Time time = Time.of(9, 15);
        
        assertThat(time.hour()).isEqualTo(9);
        assertThat(time.minute()).isEqualTo(15);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 24, 25})
    void shouldRejectInvalidHours(int invalidHour) {
        assertThatThrownBy(() -> new Time(invalidHour, 0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Hour must be between 0 and 23");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 60, 61})
    void shouldRejectInvalidMinutes(int invalidMinute) {
        assertThatThrownBy(() -> new Time(12, invalidMinute))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Minute must be between 0 and 59");
    }

    @ParameterizedTest
    @CsvSource({
        "14:30, 14, 30",
        "09:15, 9, 15",
        "00:00, 0, 0",
        "23:59, 23, 59"
    })
    void shouldParseValidTimeStrings(String timeString, int expectedHour, int expectedMinute) {
        Time time = Time.parse(timeString);
        
        assertThat(time.hour()).isEqualTo(expectedHour);
        assertThat(time.minute()).isEqualTo(expectedMinute);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "12", "12:30:45", "abc:def", "12:abc", "25:00", "12:60"})
    void shouldRejectInvalidTimeStrings(String invalidTimeString) {
        assertThatThrownBy(() -> Time.parse(invalidTimeString))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldRejectNullTimeString() {
        assertThatThrownBy(() -> Time.parse(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Time string cannot be null or empty");
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0",
        "12, 0", 
        "13, 1",
        "23, 11"
    })
    void shouldConvertTo12HourFormat(int hour24, int expectedHour12) {
        Time time = new Time(hour24, 0);
        
        assertThat(time.getHourIn12Format()).isEqualTo(expectedHour12);
    }

    @ParameterizedTest
    @CsvSource({
        "0, 1",
        "11, 0",
        "12, 1",
        "23, 0"
    })
    void shouldCalculateNextHour(int currentHour, int expectedNextHour) {
        Time time = new Time(currentHour, 0);
        
        assertThat(time.getNextHourIn12Format()).isEqualTo(expectedNextHour);
    }

    @Test
    void shouldCalculateMinutesToNextHour() {
        Time time = new Time(14, 45);
        
        assertThat(time.getMinutesToNextHour()).isEqualTo(15);
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, true",
        "12, 0, false",
        "0, 1, false"
    })
    void shouldIdentifyMidnight(int hour, int minute, boolean expectedMidnight) {
        Time time = new Time(hour, minute);
        
        assertThat(time.isMidnight()).isEqualTo(expectedMidnight);
    }

    @ParameterizedTest
    @CsvSource({
        "12, 0, true",
        "0, 0, false",
        "12, 1, false"
    })
    void shouldIdentifyNoon(int hour, int minute, boolean expectedNoon) {
        Time time = new Time(hour, minute);
        
        assertThat(time.isNoon()).isEqualTo(expectedNoon);
    }

    @Test
    void shouldImplementEqualsAndHashCode() {
        Time time1 = new Time(14, 30);
        Time time2 = new Time(14, 30);
        Time time3 = new Time(15, 30);

        assertThat(time1).isEqualTo(time2);
        assertThat(time1).isNotEqualTo(time3);
        assertThat(time1.hashCode()).isEqualTo(time2.hashCode());
    }

    @Test
    void shouldImplementToString() {
        Time time = new Time(9, 5);
        
        assertThat(time.toString()).isEqualTo("09:05");
    }
}