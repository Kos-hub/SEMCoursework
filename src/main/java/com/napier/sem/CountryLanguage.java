package com.napier.sem;

public class CountryLanguage {
    public String countryCode;
    public String language;
    public boolean isOfficial;
    public double percentage;

    @Override
    public String toString() {
        return countryCode + " " + language + " " + isOfficial + " " + percentage;
    }

}
