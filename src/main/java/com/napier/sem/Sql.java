package com.napier.sem;

import java.sql.*;

public class Sql {
    /**
     * Connection to MySQL database
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public Connection connect()
    {
        //Connection con = null;
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Connection to the database
        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start set to 30000 for travis
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://sql:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                // Wait a bit
                Thread.sleep(10000);
                // Exit for loop
                return con;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }

        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
        return null;
    }

    public void getCountriesByPopulation(Connection con){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT code, name, continent, region, population, capital " +
                    "FROM country ORDER BY " +
                    "population DESC;";
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()){
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capital = rset.getInt("Capital");

                System.out.println(country);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries");
        }
    }

    public void getCountriesByContinent(Connection con, String continent){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT code, name, continent, region, population, capital " +
                    "FROM country " +
                    "WHERE continent = '" + continent + "' " +
                    "ORDER BY population DESC;";
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()){
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capital = rset.getInt("Capital");

                System.out.println(country);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries");
        }
    }

    public void getCountriesByRegion(Connection con, String region){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT code, name, continent, region, population, capital " +
                    "FROM country " +
                    "WHERE region = '" + region + "' " +
                    "ORDER BY population DESC;";
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()){
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capital = rset.getInt("Capital");

                System.out.println(country);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries");
        }
    }

    public void topNWorld(Connection con, int limit){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT code, name, continent, region, population, capital " +
                    "FROM country " +
                    "ORDER BY population DESC " +
                    "LIMIT " + limit + " ;";
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()){
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capital = rset.getInt("Capital");

                System.out.println(country);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries");
        }
    }

    public void topNContinent(Connection con, String continent, int limit){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT code, name, continent, region, population, capital " +
                    "FROM country " +
                    "WHERE continent = '" + continent + "'" +
                    "ORDER BY population DESC " +
                    "LIMIT " + limit + " ;";
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()){
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capital = rset.getInt("Capital");

                System.out.println(country);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries");
        }
    }

    public void topNRegion(Connection con, String region, int limit){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT code, name, continent, region, population, capital " +
                    "FROM country " +
                    "WHERE region = '" + region + "'" +
                    "ORDER BY population DESC " +
                    "LIMIT " + limit + " ;";
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()){
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capital = rset.getInt("Capital");

                System.out.println(country);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries");
        }
    }

    public void disconnect()
    {
        if(con != null)
        {
            try
            {
                con.close();
                System.out.println("Successfully disconnected.");
            }
            catch(Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }

    }


}
