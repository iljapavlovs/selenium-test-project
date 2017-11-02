package com.mycompany.selenium.Enums;

/**
 * Created by ekaterina.kazinets on 11/2/2017.
 */
public enum Location {
    ALL("0"),
    RIGA("riga_f"),
    RIGA_REGION("riga_region_f");

    private String dropDownValue;

    Location(String dropDownValue) {
        this.dropDownValue = dropDownValue;
    }

    public String getLocation() {
        return dropDownValue;
    }


}
