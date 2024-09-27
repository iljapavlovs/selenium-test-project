package com.mycompany.selenium.enums;

public enum Location {
    ALL("0"),
    RIGA("riga_f"),
    RIGA_REGION("riga_region_f"),
    DAUGAVPILS("daugavpils_f");

    private String dropdownValue;

    Location(String dropdownValue) {
        this.dropdownValue = dropdownValue;
    }

    public String getLocation() {
        return dropdownValue;
    }
}


