package org.british.spoken.time.integration;

import org.british.spoken.time.service.BritishTimeConverter;
import org.british.spoken.time.service.TimeConverter;
import org.british.spoken.time.service.TimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests that verify the complete flow from input to output.
 */
class ApplicationIntegrationTest {

    private TimeService timeService;

    @BeforeEach
    void setUp() {
        TimeConverter timeConverter = new BritishTimeConverter();
        timeService = new TimeService(timeConverter);
    }

    @ParameterizedTest
    @CsvSource({
        "00:00, midnight",
        "12:00, noon",
        "09:00, nine o'clock",
        "09:15, quarter past nine",
        "09:30, half past nine",
        "09:45, quarter to ten",
        "14:20, twenty past two",
        "16:40, twenty to five",
        "23:59, one to twelve"
    })
    void shouldConvertTimeEndToEnd(String input, String expected) {
        String result = timeService.parseAndConvert(input);
        
        assertThat(result).isEqualTo(expected);
    }
}