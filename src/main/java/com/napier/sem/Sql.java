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
                System.out.println("Failed to connect to database attempt " + i);
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
     * @param con Instance of a database connection.
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
     * @param con Instance of a database connection.
     * @param continent Instance of a database connection.
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
     * @param con Instance of a database connection.
     * @param region The specific region we want to look at in the query.
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
     * @param con Instance of a database connection.
     * @param limit The specific amount of rows we want to have.
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
     * @param con Instance of a database connection.
     * @param continent Instance of a database connection.
     * @param limit The specific amount of rows we want to have.
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
     * @param con Instance of a database connection.
     * @param region The specific region we want to look at in the query.
     * @param limit The specific amount of rows we want to have.
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
     * @param con Instance of a database connection.
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
     * @param con Instance of a database connection.
     * @param continent Instance of a database connection.
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
     * @param con Instance of a database connection.
     * @param region The specific region we want to look at in the query.
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
     * @param con Instance of a database connection.
     * @param country The specific country we want to look at in the query.
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
     * @param con Instance of a database connection.
     * @param district The specific district we want to look at in the query.
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
     * @param con Instance of a database connection.
     * @param limit The specific amount of rows we want to have.
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
     * @param con Instance of a database connection.
     * @param continent Instance of a database connection.
     * @param limit The specific amount of rows we want to have.
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
     * @param con Instance of a database connection.
     * @param region The specific region we want to look at in the query.
     * @param limit The specific amount of rows we want to have.
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
     * @param con Instance of a database connection.
     * @param country The specific country we want to look at in the query.
     * @param limit The specific amount of rows we want to have.
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
     * @param con Instance of a database connection.
     * @param district The specific district we want to look at in the query.
     * @param limit The specific amount of rows we want to have.
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
