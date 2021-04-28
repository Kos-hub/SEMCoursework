package com.napier.sem;

public class Country {
    public String code;
    public String name;
    public String continent;
    public String region;
    public int population;
    public int capital;

    @Override
    public String toString() {
        return code + " " + name + " " + continent + " " + region + " " + population + " " + capital;
    }

}
