package org.british.spoken.time.service;

import org.british.spoken.time.domain.Time;

/**
 * Interface for converting Time objects to spoken format.
 */
public interface TimeConverter {
    
    /**
     * Converts a Time object to its spoken representation.
     *
     * @param time the time to convert
     * @return the spoken representation of the time
     * @throws IllegalArgumentException if time is null
     */
    String convertToSpoken(Time time);
}