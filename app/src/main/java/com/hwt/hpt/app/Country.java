package com.hwt.hpt.app;

/**
 * Created by oVANILLAz on 6/1/14 AD.
 *
 * Spinner
 */
public class Country {
    private int flagImageId;
    private String CountryName;

    public Country(int flagImageId, String countryName) {
        this.flagImageId = flagImageId;
        CountryName = countryName;
    }

    public int getFlagImageId() {
        return flagImageId;
    }

    public void setFlagImageId(int flagImageId) {
        this.flagImageId = flagImageId;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }
}

