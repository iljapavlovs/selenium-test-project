package com.mycompany.selenium.Enums;

/**
 * Created by ekaterina.kazinets on 11/2/2017.
 */
public enum Division {

    WORK_AND_BUSSINESS("1"),
    TECHNIK("6");

    private String division;

    Division(String division) {
        this.division = division;
    }

    public String getDivision() {
        return division;

    }
}
