package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class Sql {
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * @param location This string will contain the location of the database (local or server)
     * Connect to the MySQL database.
     */
    public Connection connect(String location)
    {
        //Connection con = null;
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");

        // Connection to the database
        int retries = 2;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start set to 30000 for travis
                //Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
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


            while(rset.next())
            {
                City city = new City();
                city.name = rset.getString("Name");
                city.country = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");

                System.out.println(city);

            }
        }
        catch (Exception e)
        {
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


    // For all methods below:
    //      The method should check for known bad inputs and fix them or return immediately.
    //      If they return bad input nonetheless they should return either a null or -1.

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @return This method should return an ArrayList of all cities in the world.
     */
    public ArrayList<City> getCapitalCitiesWorld(Connection con, int N) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param continent This method should take a continent name as a String argument.
     * @return This method should return an ArrayList of all cities in the continent.
     */
    public ArrayList<City> getCapitalCitiesContinent(Connection con, String continent, int N) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param region This method should take a region name as a String argument.
     * @return This method should return an ArrayList of all cities in the region.
     */
    public ArrayList<City> getCapitalCitiesRegion(Connection con, String region, int N) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param language This method should take a language as a String argument.
     * @return This method should return the int population.
     */
    public int getLanguageSpeakers(Connection con, String language) {
        return 0;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param continent This method should take a continent name as a String argument.
     * @return This method should return an ArrayList containing 2 values corresponding to the urban population and the rural population in that order.
     */
    public ArrayList<Long> getPopulationUrbanRuralContinent(Connection con, String continent) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param region This method should take a region name as a String argument.
     * @return This method should return an ArrayList containing 2 values corresponding to the urban population and the rural population in that order.
     */
    public ArrayList<Long> getPopulationUrbanRuralRegion(Connection con, String region) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param country This method should take a country name as a String argument.
     * @return This method should return an ArrayList containing 2 values corresponding to the urban population and the rural population in that order.
     */
    public ArrayList<Long> getPopulationUrbanRuralCountry(Connection con, String country) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @return This method should return the long population of the world.
     */
    public long getPopulationWorld(Connection con) {
        return 0;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param continent This method should take a continent name as a String argument.
     * @return This method should return the long population of the world.
     */
    public long getPopulationContinent(Connection con, String continent) {
        return 0;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param region This method should take a region name as a String argument.
     * @return This method should return the int population of the world.
     */
    public int getPopulationRegion(Connection con, String region) {
        return 0;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param country This method should take a country name as a String argument.
     * @return This method should return the int population of the world.
     */
    public int getPopulationCountry(Connection con, String country) {
        return 0;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param district This method should take a district name as a String argument.
     * @return This method should return the int population of the world.
     */
    public int getPopulationDistrict(Connection con, String district) {
        return 0;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param city This method should take a city name as a String argument.
     * @return This method should return the int population of the world.
     */
    public int getPopulationCity(Connection con, String city) {
        return 0;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param N This method should take the number of top cities to display as an int argument.
     *          If the user enters N as zero then it should display all matching cities.
     * @return This method should return an ArrayList of all cities in the world in descending order of population.
     */
    public ArrayList<City> getCitiesByPopulationWorld(Connection con, int N) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param continent This method should take a continent name as a String argument.
     * @param N This method should take the number of top cities to display as an int argument.
     *          If the user enters N as zero then it should display all matching cities.
     * @return This method should return an ArrayList of all cities in a continent in descending order of population.
     */
    public ArrayList<City> getCitiesByPopulationContinent(Connection con, String continent, int N) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param region This method should take a region name as a String argument.
     * @param N This method should take the number of top cities to display as an int argument.
     *          If the user enters N as zero then it should display all matching cities.
     * @return This method should return an ArrayList of all cities in a region in descending order of population.
     */
    public ArrayList<City> getCitiesByPopulationRegion(Connection con, String region, int N) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param country This method should take a country name as a String argument.
     * @param N This method should take the number of top cities to display as an int argument.
     *          If the user enters N as zero then it should display all matching cities.
     * @return This method should return an ArrayList of all cities in a country in descending order of population.
     */
    public ArrayList<City> getCitiesByPopulationCountry(Connection con, String country, int N) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param district This method should take a district name as a String argument.
     * @param N This method should take the number of top cities to display as an int argument.
     *          If the user enters N as zero then it should display all matching cities.
     * @return This method should return an ArrayList of all cities in a district in descending order of population.
     */
    public ArrayList<City> getCitiesByPopulationDistrict(Connection con, String district, int N) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @return This method should return an ArrayList of all countries in the world in descending order of population.
     */
    public ArrayList<Country> getCountriesByPopulationWorld(Connection con, int N) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param continent This method should take a continent name as a String argument.
     * @return This method should return an ArrayList of all countries in a continent in descending order of population.
     */
    public ArrayList<Country> getCountriesByPopulationContinent(Connection con, String continent, int N) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param region This method should take a region name as a String argument.
     * @return This method should return an ArrayList of all countries in a region in descending order of population.
     */
    public ArrayList<Country> getCountriesByPopulationRegion(Connection con, String region, int N) {
        return null;
    }
}
