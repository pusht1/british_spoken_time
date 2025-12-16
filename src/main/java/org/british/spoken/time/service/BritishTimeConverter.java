package org.british.spoken.time.service;

import org.british.spoken.time.converter.NumberToWordConverter;
import org.british.spoken.time.domain.Time;
import org.springframework.stereotype.Service;

/**
 * Implementation of TimeConverter for British English spoken time format
 */
@Service
public class BritishTimeConverter implements TimeConverter {

    @Override
    public String convertToSpoken(Time time) {
        if (time == null) {
            throw new IllegalArgumentException("Time cannot be null");
        }

        if (time.isMidnight()) {
            return "midnight";
        }

        if (time.isNoon()) {
            return "noon";
        }

        if (time.isOnTheHour()) {
            return NumberToWordConverter.hourToWord(time.getHourIn12Format()) + " o'clock";
        }

        if (time.isQuarterPast()) {
            return "quarter past " + NumberToWordConverter.hourToWord(time.getHourIn12Format());
        }

        if (time.isHalfPast()) {
            return "half past " + NumberToWordConverter.hourToWord(time.getHourIn12Format());
        }

        if (time.isQuarterTo()) {
            return "quarter to " + NumberToWordConverter.hourToWord(time.getNextHourIn12Format());
        }

        if (time.isPastHalfHour()) {
            return NumberToWordConverter.minuteToWord(time.getMinutesToNextHour()) + 
                   " to " + NumberToWordConverter.hourToWord(time.getNextHourIn12Format());
        }

        return NumberToWordConverter.minuteToWord(time.minute()) +
               " past " + NumberToWordConverter.hourToWord(time.getHourIn12Format());
    }
}