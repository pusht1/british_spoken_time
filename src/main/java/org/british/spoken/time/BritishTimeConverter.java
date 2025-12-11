package org.british.spoken.time;

public class BritishTimeConverter {

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

    public String convertToSpoken(String timeInput) {
        try {
            String[] parts = timeInput.split(":");
            if (parts.length != 2) {
                return "Invalid time format. Please use HH:MM format.";
            }

            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);

            if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
                return "Invalid time. Hours must be 0-23, minutes must be 0-59.";
            }

            return convertTime(hour, minute);

        } catch (NumberFormatException e) {
            return "Invalid time format. Please use HH:MM format.";
        }
    }

    private String convertTime(int hour, int minute) {
        if (hour == 0 && minute == 0) return "midnight";
        if (hour == 12 && minute == 0) return "noon";

        int twelveHours = hour % 12;

        if (minute == 0) {
            return HOURS[twelveHours] + " o'clock";
        }

        if (minute < 30) {
            return NUMBERS[minute] + " past " + HOURS[twelveHours];
        }

        if (minute == 30) {
            return "half past " + HOURS[twelveHours];
        }

        int minutesToNext = 60 - minute;
        int nextHour = (twelveHours + 1) % 12;
        return NUMBERS[minutesToNext] + " to " + HOURS[nextHour];
    }
}