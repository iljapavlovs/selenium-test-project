package com.mycompany.selenium.enums;

public enum Period {

    WEEK("7"),
    MONTH("30"),
    ALL("0");

    private String dayCount;

    Period(String dropdownValue) {
        this.dayCount = dropdownValue;
    }

    public String getPeriod() {
        return dayCount;
    }
}
