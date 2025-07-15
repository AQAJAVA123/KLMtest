package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateAndTime {
    private final String day;
    private final String month;
    private final String year;

    public DateAndTime(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static DateAndTime fromString(String date) {
        String[] parts = date.split("-");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Date must be in format DD-MM-YYYY");
        }
        return new DateAndTime(parts[0], parts[1], parts[2]);
    }

    public String getFormatted() {
        return String.format("%s-%s-%s", day, month, year);
    }

    public String getIsoFormatted() {
        return String.format("%s-%s-%s",
                year,
                month.length() == 1 ? "0" + month : month,
                day.length() == 1 ? "0" + day : day);
    }

    public String getMonthName() {
        int monthNumber = Integer.parseInt(month);
        return java.time.Month.of(monthNumber).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public int getDay() {
        return Integer.parseInt(day);
    }

    public LocalDate toLocalDate() {
        return LocalDate.parse(getIsoFormatted());
    }
}
