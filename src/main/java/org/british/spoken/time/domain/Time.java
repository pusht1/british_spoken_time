package org.british.spoken.time.domain;

/**
 * Immutable value object representing a time in 24-hour format.
 * Ensures valid time constraints and provides conversion utilities.
 */
public record Time(int hour, int minute) {
    private static final int MIN_HOUR = 0;
    private static final int MAX_HOUR = 23;
    private static final int MIN_MINUTE = 0;
    private static final int MAX_MINUTE = 59;
    private static final int HOURS_IN_12_FORMAT = 12;
    private static final int MINUTES_IN_HOUR = 60;

    public Time {
        validateHour(hour);
        validateMinute(minute);
    }

    public static Time of(int hour, int minute) {
        return new Time(hour, minute);
    }

    public static Time parse(String timeString) {
        if (timeString == null || timeString.trim().isEmpty()) {
            throw new IllegalArgumentException("Time string cannot be null or empty");
        }

        String[] parts = timeString.trim().split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid time format. Expected HH:MM");
        }

        try {
            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);
            return new Time(hour, minute);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid time format. Hours and minutes must be numeric", e);
        }
    }

    public int getHourIn12Format() {
        return hour % HOURS_IN_12_FORMAT;
    }

    public int getNextHourIn12Format() {
        return (getHourIn12Format() + 1) % HOURS_IN_12_FORMAT;
    }

    public int getMinutesToNextHour() {
        return MINUTES_IN_HOUR - minute;
    }

    public boolean isMidnight() {
        return hour == 0 && minute == 0;
    }

    public boolean isNoon() {
        return hour == 12 && minute == 0;
    }

    public boolean isOnTheHour() {
        return minute == 0;
    }

    public boolean isHalfPast() {
        return minute == 30;
    }

    public boolean isQuarterPast() {
        return minute == 15;
    }

    public boolean isQuarterTo() {
        return minute == 45;
    }

    public boolean isPastHalfHour() {
        return minute > 30;
    }

    private void validateHour(int hour) {
        if (hour < MIN_HOUR || hour > MAX_HOUR) {
            throw new IllegalArgumentException(
                    String.format("Hour must be between %d and %d, got: %d", MIN_HOUR, MAX_HOUR, hour)
            );
        }
    }

    private void validateMinute(int minute) {
        if (minute < MIN_MINUTE || minute > MAX_MINUTE) {
            throw new IllegalArgumentException(
                    String.format("Minute must be between %d and %d, got: %d", MIN_MINUTE, MAX_MINUTE, minute)
            );
        }
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }
}