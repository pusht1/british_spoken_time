package org.british.spoken.time.service;

import org.british.spoken.time.domain.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TimeServiceTest {

    private TimeService timeService;

    @BeforeEach
    void setUp() {
        TimeConverter timeConverter = new BritishTimeConverter();
        timeService = new TimeService(timeConverter);
    }

    @Test
    void shouldParseAndConvertValidTimeString() {
        String result = timeService.parseAndConvert("09:15");
        
        assertThat(result).isEqualTo("quarter past nine");
    }

    @Test
    void shouldThrowExceptionForInvalidTimeString() {
        assertThatThrownBy(() -> timeService.parseAndConvert("invalid"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Invalid time:");
    }

    @Test
    void shouldConvertTimeObject() {
        Time time = new Time(14, 30);
        
        String result = timeService.convert(time);
        
        assertThat(result).isEqualTo("half past two");
    }

    @Test
    void shouldHandleEdgeCases() {
        assertThat(timeService.parseAndConvert("00:00")).isEqualTo("midnight");
        assertThat(timeService.parseAndConvert("12:00")).isEqualTo("noon");
        assertThat(timeService.parseAndConvert("23:59")).isEqualTo("one to twelve");
    }
}