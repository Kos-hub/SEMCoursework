package com.napier.sem;

import java.sql.*;


public class App {
    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        Sql db = new Sql();
        Connection con = db.connect();

        //db.getCountriesByPopulation(con);
        //db.getCountriesByContinent(con, "Europe");
        //db.getCountriesByRegion(con, "Western Europe");
        //db.topNCountriesInWorld(con, 5);
        //db.topNCountriesInContinent(con, "Europe", 6);
        //db.topNCountriesInRegion(con, "Western Europe", 7);
        //db.getCitiesInWorld(con);
        //db.getCitiesInContinent(con, "Europe");
        //db.getCitiesInRegion(con, "Western Europe");
        //db.getCitiesInCountry(con, "Laos");
        //db.getCitiesInDistrict(con, "Viangchan");
        //db.topNCities(con, 4);
        //db.topNCitiesInContinent(con, "Europe", 4);
        //db.topNCitiesInRegion(con, "Western Europe", 4);
        //db.topNCitiesInCountry(con, "Paraguay", 4);
        db.topNCitiesInDistrict(con, "Viangchan",4);
        db.disconnect();
    }

}
