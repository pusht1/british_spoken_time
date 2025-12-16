package org.british.spoken.time.converter;

/**
 * Utility class for converting numbers to their word representations.
 */
public final class NumberToWordConverter {
    
    private static final String[] NUMBERS = {
        "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen", "quarter", "sixteen", "seventeen",
        "eighteen", "nineteen", "twenty", "twenty one", "twenty two", "twenty three",
        "twenty four", "twenty five", "twenty six", "twenty seven", "twenty eight", "twenty nine"
    };

    private static final String[] HOURS = {
        "twelve", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
        "ten", "eleven"
    };

    private NumberToWordConverter() {
        // Utility class - prevent instantiation
    }

    /**
     * Converts a minute value to its word representation.
     *
     * @param minute the minute value (0-59)
     * @return the word representation of the minute
     * @throws IllegalArgumentException if minute is out of range
     */
    public static String minuteToWord(int minute) {
        validateMinute(minute);
        return NUMBERS[minute];
    }

    /**
     * Converts an hour value (0-11) to its word representation.
     *
     * @param hour the hour value in 12-hour format (0-11)
     * @return the word representation of the hour
     * @throws IllegalArgumentException if hour is out of range
     */
    public static String hourToWord(int hour) {
        validateHour(hour);
        return HOURS[hour];
    }

    private static void validateMinute(int minute) {
        if (minute < 0 || minute >= NUMBERS.length) {
            throw new IllegalArgumentException("Minute must be between 0 and " + (NUMBERS.length - 1));
        }
    }

    private static void validateHour(int hour) {
        if (hour < 0 || hour >= HOURS.length) {
            throw new IllegalArgumentException("Hour must be between 0 and " + (HOURS.length - 1));
        }
    }
}