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

    /**
     * @param con
     * @return All the countries in the world organised by largest population to smallest.
     */
    public void getCountriesByWorld(Connection con){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT code, name, continent, region, population, capital " +
                    "FROM country " +
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

    /**
     * @param con
     * @param continent The specific continent we want to look at in the query.
     * @return All the countries in a continent organised by largest population to smallest.
     */
    public void getCountriesByContinent(Connection con, String continent){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT code, name, continent, region, population, capital " +
                    "FROM country " +
                    "WHERE continent LIKE '" + continent + "' " +
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

    /**
     * @param con
     * @param region The specific region we want to look at in the query.
     * @return All the countries in a region organised by largest population to smallest.
     */
    public void getCountriesByRegion(Connection con, String region){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT code, name, continent, region, population, capital " +
                    "FROM country " +
                    "WHERE region LIKE '" + region + "' " +
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

    /**
     * @param con
     * @param limit The specific amount of rows we want to have.
     * @return The top N populated countries in the world where N is provided by the user. (N = limit)
     */
    public void topNCountriesInWorld(Connection con, int limit){
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

    /**
     * @param con
     * @param continent The specific continent we want to look at in the query.
     * @param limit The specific amount of rows we want to have.
     * @return The top N populated countries in a continent where N is provided by the user. (N = limit)
     */
    public void topNCountriesInContinent(Connection con, String continent, int limit){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT code, name, continent, region, population, capital " +
                    "FROM country " +
                    "WHERE continent LIKE '" + continent + "'" +
                    "ORDER BY population DESC " +
                    "LIMIT " + limit + ";";
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

    /**
     * @param con
     * @param region The specific region we want to look at in the query.
     * @param limit The specific amount of rows we want to have.
     * @return The top N populated countries in a region where N is provided by the user. (N = limit)
     */
    public void topNCountriesInRegion(Connection con, String region, int limit){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT code, name, continent, region, population, capital " +
                    "FROM country " +
                    "WHERE region LIKE '" + region + "'" +
                    "ORDER BY population DESC " +
                    "LIMIT " + limit + ";";
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

    /**
     * @param con
     * @return All the cities in the world organised by largest population to smallest.
     */
    public void getCitiesInWorld(Connection con){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT name, countryCode, district, population " +
                    "FROM city " +
                    "ORDER BY population DESC;";
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()){
                City country = new City();
                country.name = rset.getString("Name");
                country.country = rset.getString("countryCode");
                country.district = rset.getString("District");
                country.population = rset.getInt("Population");

                System.out.println(country);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries");
        }
    }

    /**
     * @param con
     * @param continent The specific continent we want to look at in the query.
     * @return All the cities in a continent organised by largest population to smallest.
     */
    public void getCitiesInContinent(Connection con, String continent){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT\n" +
                    "  city.name AS Name\n" +
                    "  ,city.CountryCode\n" +
                    "  ,city.Population\n" +
                    "  ,city.District\n" +
                    "FROM\n" +
                    "  (city JOIN country ON city.CountryCode=country.Code)\n" +
                    "WHERE\n" +
                    "  continent LIKE '"+ continent +"'\n" +
                    "ORDER BY\n" +
                    "  city.population DESC;";
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()){
                City city = new City();
                city.name = rset.getString("Name");
                city.country = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");

                System.out.println(city);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get Cities");
        }
    }

    /**
     * @param con
     * @param region The specific region we want to look at in the query.
     * @return All the cities in a region organised by largest population to smallest.
     */
    public void getCitiesInRegion(Connection con, String region){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT\n" +
                    "  city.name\n" +
                    "  ,city.population\n" +
                    "  ,city.district\n" +
                    "  ,city.CountryCode\n" +
                    "FROM\n" +
                    "  (city JOIN country ON city.CountryCode=country.Code)\n" +
                    "WHERE\n" +
                    "  region LIKE '" + region + "'\n" +
                    "ORDER BY\n" +
                    "  city.population DESC;";
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()){
                City city = new City();
                city.name = rset.getString("Name");
                city.country = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");

                System.out.println(city);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get Cities");
        }
    }

    /**
     * @param con
     * @param country The specific country we want to look at in the query.
     * @return All the cities in a country organised by largest population to smallest.
     */
    public void getCitiesInCountry(Connection con, String country){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT\n" +
                    "  city.name\n" +
                    "  ,city.population\n" +
                    "  ,city.district\n" +
                    "  ,city.CountryCode\n" +
                    "FROM\n" +
                    "  (city JOIN country ON city.CountryCode=country.Code)\n" +
                    "WHERE\n" +
                    "  country.name LIKE '" + country + "'\n" +
                    "ORDER BY\n" +
                    "  city.population DESC;";
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()){
                City city = new City();
                city.name = rset.getString("Name");
                city.country = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");

                System.out.println(city);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get Cities");
        }
    }

    /**
     * @param con
     * @param district The specific district we want to look at in the query.
     * @return All the cities in a district organised by largest population to smallest.
     */
    public void getCitiesInDistrict(Connection con, String district){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT\n" +
                    "  city.name\n" +
                    "  ,city.population\n" +
                    "  ,city.district\n" +
                    "  ,city.CountryCode\n" +
                    "FROM\n" +
                    "  (city JOIN country ON city.CountryCode=country.Code)\n" +
                    "WHERE\n" +
                    "  city.district LIKE '" + district + "'\n" +
                    "ORDER BY\n" +
                    "  city.population DESC;";
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()){
                City city = new City();
                city.name = rset.getString("Name");
                city.country = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");

                System.out.println(city);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get Cities");
        }
    }

    /**
     * @param con
     * @param limit The specific amount of rows we want to have.
     * @return The top N populated cities in the world where N is provided by the user.
     */
    public void topNCities(Connection con, int limit){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT\n" +
                    "  name\n" +
                    "  ,CountryCode\n" +
                    "  ,district\n" +
                    "  ,population\n" +
                    "FROM\n" +
                    "  city\n" +
                    "ORDER BY\n" +
                    "  population DESC\n" +
                    "LIMIT "+ limit + ";";
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()){
                City city = new City();
                city.name = rset.getString("Name");
                city.country = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");

                System.out.println(city);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get Cities");
        }
    }

    /**
     * @param con
     * @param continent The specific continent we want to look at in the query.
     * @param limit The specific amount of rows we want to have.
     * @return The top N populated cities in a continent where N is provided by the user.
     */
    public void topNCitiesInContinent(Connection con, String continent, int limit){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT\n" +
                    "  city.name\n" +
                    "  ,city.CountryCode\n" +
                    "  ,city.district\n" +
                    "  ,city.population\n" +
                    "FROM\n" +
                    "(city JOIN country ON city.CountryCode=country.Code)\n" +
                    "WHERE\n" +
                    "  continent LIKE '"+ continent + "'\n" +
                    "ORDER BY\n" +
                    "  city.population DESC\n" +
                    "LIMIT "+ limit + ";";
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()){
                City city = new City();
                city.name = rset.getString("Name");
                city.country = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");

                System.out.println(city);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get Cities");
        }
    }

    /**
     * @param con
     * @param region The specific region we want to look at in the query.
     * @param limit The specific amount of rows we want to have.
     * @return The top N populated cities in a region where N is provided by the user.
     */
    public void topNCitiesInRegion(Connection con, String region, int limit){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT\n" +
                    "  city.name\n" +
                    "  ,city.CountryCode" +
                    "  ,city.district" +
                    "  ,city.population\n" +
                    "FROM\n" +
                    "  (city JOIN country ON city.CountryCode=country.Code)\n" +
                    "WHERE\n" +
                    "  region LIKE '" + region + "'\n" +
                    "ORDER BY\n" +
                    "  city.population DESC\n" +
                    "LIMIT "+ limit + ";";
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()){
                City city = new City();
                city.name = rset.getString("Name");
                city.country = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");

                System.out.println(city);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get Cities");
        }
    }

    /**
     * @param con
     * @param country The specific country we want to look at in the query.
     * @param limit The specific amount of rows we want to have.
     * @return The top N populated cities in a country where N is provided by the user.
     */
    public void topNCitiesInCountry(Connection con, String country, int limit){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT\n" +
                    "  city.name\n" +
                    "  ,city.CountryCode" +
                    "  ,city.district" +
                    "  ,city.population\n" +
                    "FROM\n" +
                    "  (city JOIN country ON city.CountryCode=country.Code)\n" +
                    "WHERE\n" +
                    "  country.name LIKE '" + country + "'\n" +
                    "ORDER BY\n" +
                    "  city.population DESC\n" +
                    "LIMIT "+ limit + ";";
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()){
                City city = new City();
                city.name = rset.getString("Name");
                city.country = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");

                System.out.println(city);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get Cities");
        }
    }

    /**
     * @param con
     * @param district The specific district we want to look at in the query.
     * @param limit The specific amount of rows we want to have.
     * @return The top N populated cities in a district where N is provided by the user.
     */
    public void topNCitiesInDistrict(Connection con, String district, int limit){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT\n" +
                    "  city.name\n" +
                    "  ,city.CountryCode" +
                    "  ,city.district" +
                    "  ,city.population\n" +
                    "FROM\n" +
                    "  (city JOIN country ON city.CountryCode=country.Code)\n" +
                    "WHERE\n" +
                    "  city.district LIKE '" + district + "'\n" +
                    "ORDER BY\n" +
                    "  city.population DESC\n" +
                    "LIMIT "+ limit + ";";
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()){
                City city = new City();
                city.name = rset.getString("Name");
                city.country = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");

                System.out.println(city);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get Cities");
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
