package com.mycompany.selenium.enums;


public enum TimePeriod {
    ALL_PERIOD("0"),
    LAST_MONTH("30");

    private String dayCount;

    TimePeriod(String dayCount) {
        this.dayCount = dayCount;
    }

    public String getDayCount() {
        return dayCount;
    }
}
