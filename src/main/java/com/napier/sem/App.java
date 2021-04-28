package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;


public class App {
    /**
     * This program needs you to set up a local docker sql container in order to work.
     * Set the port to 3306 and everything will work.
     * @param args
     */
    public static void main(String[] args)
    {
        Sql db = new Sql();
        SqlReturnsObjects second_db = new SqlReturnsObjects();

        Connection con = db.connect("sql:3306");
        Connection second_con = second_db.connect("sql:3306");

        db.getCountriesByWorld(con);
        System.out.println("----------------------------------------------------------------------------");

        db.getCountriesByContinent(con, "Europe");
        System.out.println("----------------------------------------------------------------------------");

        db.getCountriesByRegion(con, "Western Europe");
        System.out.println("----------------------------------------------------------------------------");

        db.topNCountriesInWorld(con, 5);
        System.out.println("----------------------------------------------------------------------------");

        db.topNCountriesInContinent(con, "Europe", 6);
        System.out.println("----------------------------------------------------------------------------");

        db.topNCountriesInRegion(con, "Western Europe", 7);
        System.out.println("----------------------------------------------------------------------------");

        db.getCitiesInWorld(con);
        System.out.println("----------------------------------------------------------------------------");

        db.getCitiesInContinent(con, "Europe");
        System.out.println("----------------------------------------------------------------------------");

        db.getCitiesInRegion(con, "Western Europe");
        System.out.println("----------------------------------------------------------------------------");

        db.getCitiesInCountry(con, "Laos");
        System.out.println("----------------------------------------------------------------------------");

        db.getCitiesInDistrict(con, "Viangchan");
        System.out.println("----------------------------------------------------------------------------");

        db.topNCities(con, 4);
        System.out.println("----------------------------------------------------------------------------");

        db.topNCitiesInContinent(con, "Europe", 4);
        System.out.println("----------------------------------------------------------------------------");

        db.topNCitiesInRegion(con, "Western Europe", 4);
        System.out.println("----------------------------------------------------------------------------");

        db.topNCitiesInCountry(con, "Paraguay", 4);
        System.out.println("----------------------------------------------------------------------------");

        db.topNCitiesInDistrict(con, "Viangchan",4);
        System.out.println("----------------------------------------------------------------------------");

        ArrayList<City> capitalCitiesByWorld = new ArrayList<City>();
        capitalCitiesByWorld = second_db.getCapitalCitiesWorld(second_con, 4);
        for(City c : capitalCitiesByWorld){
            System.out.println(c);
        }

        ArrayList<City> capitalCitiesByContinent = new ArrayList<City>();
        capitalCitiesByContinent = second_db.getCapitalCitiesContinent(second_con, "Europe", 4);
        for(City c : capitalCitiesByContinent){
            System.out.println(c);
        }

        System.out.println("----------------------------------------------------------------------------");

        ArrayList<City> capitalCitiesByRegion = new ArrayList<City>();
        capitalCitiesByRegion = second_db.getCapitalCitiesRegion(second_con, "Western Europe", 4);
        for(City c : capitalCitiesByRegion){
            System.out.println(c);
        }

        System.out.println("----------------------------------------------------------------------------");

        ArrayList<Long> languageSpeakers = new ArrayList<Long>();
        ArrayList<String> languageList = new ArrayList<>();
        languageList.add("Chinese");
        languageList.add("English");
        languageList.add("Hindi");
        languageList.add("Spanish");
        languageList.add("Arabic");
        languageSpeakers = second_db.getLanguageSpeakers(second_con, languageList);
        for (int i = 0; i < languageSpeakers.size(); i+=2) {
            System.out.println(languageList.get(i/2));
            System.out.println(languageSpeakers.get(i));
            System.out.println((languageSpeakers.get(i)/languageSpeakers.get(i+1))*100+"%");
        }


        System.out.println("----------------------------------------------------------------------------");

        ArrayList<Long> populationUrbanRuralContinent = new ArrayList<Long>();
        populationUrbanRuralContinent = second_db.getPopulationUrbanRuralContinent(second_con, "Europe");
        for(Long l : populationUrbanRuralContinent){
            System.out.println(l);
        }

        System.out.println("----------------------------------------------------------------------------");

        ArrayList<Long> populationUrbanRuralRegion = new ArrayList<Long>();
        populationUrbanRuralRegion = second_db.getPopulationUrbanRuralRegion(second_con, "Western Europe");
        for(Long l : populationUrbanRuralRegion){
            System.out.println(l);
        }

        System.out.println("----------------------------------------------------------------------------");

        ArrayList<Long> populationUrbanRuralCountry = new ArrayList<Long>();
        populationUrbanRuralCountry = second_db.getPopulationUrbanRuralCountry(second_con, "Paraguay");
        for(Long l : populationUrbanRuralCountry){
            System.out.println(l);
        }

        System.out.println("----------------------------------------------------------------------------");

        System.out.println(second_db.getPopulationWorld(second_con));

        System.out.println("----------------------------------------------------------------------------");

        System.out.println(second_db.getPopulationContinent(second_con, "Europe"));

        System.out.println("----------------------------------------------------------------------------");

        System.out.println(second_db.getPopulationRegion(second_con, "Western Europe"));

        System.out.println("----------------------------------------------------------------------------");

        System.out.println(second_db.getPopulationCountry(second_con, "Paraguay"));

        System.out.println("----------------------------------------------------------------------------");

        System.out.println(second_db.getPopulationDistrict(second_con, "Viangchan"));

        System.out.println("----------------------------------------------------------------------------");

        System.out.println(second_db.getPopulationCity(second_con, "Roma"));

        System.out.println("----------------------------------------------------------------------------");

        ArrayList<City> citiesByPopulationWorld = new ArrayList<City>();
        citiesByPopulationWorld = second_db.getCitiesByPopulationWorld(second_con, 4);
        for(City c : citiesByPopulationWorld){
            System.out.println(c);
        }

        System.out.println("----------------------------------------------------------------------------");

        ArrayList<City> citiesByPopulationContinent = new ArrayList<City>();
        citiesByPopulationContinent = second_db.getCitiesByPopulationContinent(second_con, "Europe", 4);
        for(City c : citiesByPopulationContinent){
            System.out.println(c);
        }

        System.out.println("----------------------------------------------------------------------------");

        ArrayList<City> citiesByPopulationRegion = new ArrayList<City>();
        citiesByPopulationRegion = second_db.getCitiesByPopulationRegion(second_con, "Western Europe", 4);
        for(City c : citiesByPopulationRegion){
            System.out.println(c);
        }

        System.out.println("----------------------------------------------------------------------------");

        db.disconnect();
        second_db.disconnect();
    }

}
