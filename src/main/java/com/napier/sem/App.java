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
        //db.topNWorld(con, 5);
        //db.topNContinent(con, "Europe", 6);
        db.topNRegion(con, "Western Europe", 7);
        db.disconnect();
    }

}
