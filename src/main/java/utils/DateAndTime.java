package utils;

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
}
