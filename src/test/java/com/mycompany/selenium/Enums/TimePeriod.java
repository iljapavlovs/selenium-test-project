package com.mycompany.selenium.Enums;

/**
 * Created by ekaterina.kazinets on 11/2/2017.
 */
public enum TimePeriod {
    TODAY("1"),
    LAST3DAYS("3");

    private String timePeriod;

    TimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getTimePeriod() {
        return timePeriod;
    }
}
