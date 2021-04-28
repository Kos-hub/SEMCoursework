package com.napier.sem;

import java.sql.*;


public class App {
    /**
     * This program needs you to set up a local docker sql container in order to work.
     * Set the port to 3306 and everything will work.
     * @param args
     */
    public static void main(String[] args)
    {
        Sql db = new Sql();
        Connection con = db.connect("sql:3306");

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

        db.disconnect();
    }

}
