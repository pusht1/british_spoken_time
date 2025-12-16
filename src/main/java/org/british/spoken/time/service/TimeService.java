package org.british.spoken.time.service;

import org.british.spoken.time.domain.Time;
import org.springframework.stereotype.Service;

/**
 * Service class that orchestrates time parsing and conversion.
 */
@Service
public class TimeService {
    
    private final TimeConverter timeConverter;

    public TimeService(TimeConverter timeConverter) {
        this.timeConverter = timeConverter;
    }

    /**
     * Parses a time string and converts it to spoken format.
     *
     * @param timeString the time string in HH:MM format
     * @return the spoken representation of the time
     * @throws IllegalArgumentException if the time string is invalid
     */
    public String parseAndConvert(String timeString) {
        try {
            Time time = Time.parse(timeString);
            return timeConverter.convertToSpoken(time);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid time: " + e.getMessage(), e);
        }
    }

    /**
     * Converts a Time object to spoken format.
     *
     * @param time the time to convert
     * @return the spoken representation of the time
     */
    public String convert(Time time) {
        return timeConverter.convertToSpoken(time);
    }
}