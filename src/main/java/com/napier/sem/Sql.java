package com.napier.sem;

import jdk.internal.vm.compiler.collections.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class Sql {
    /**
     * Connection to MySQL database
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public Connection connect(String location)
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
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
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

    public void getCity(Connection con){
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT " +
                    "  name " +
                    "  ,population " +
                    "FROM " +
                    "  city " +
                    "ORDER BY " +
                    "  population DESC;";
            ResultSet rset = stmt.executeQuery(strSelect);


            while(rset.next()){
                City city = new City();
                //city.ID = rset.getInt("ID");
                city.name = rset.getString("Name");
                //city.country = rset.getString("CountryCode");
                //city.district = rset.getString("District");
                city.population = rset.getInt("Population");

                System.out.println(city.name + " " + city.population);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get city");
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

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @return This method should return an ArrayList of all cities in the world.
     */
    public ArrayList<City> getCapitalCitiesWorld(Connection con) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param continent This method should take a continent name as a String argument.
     * @return This method should return an ArrayList of all cities in the continent.
     */
    public ArrayList<City> getCapitalCitiesContinent(Connection con, String continent) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param region This method should take a region name as a String argument.
     * @return This method should return an ArrayList of all cities in the region.
     */
    public ArrayList<City> getCapitalCitiesRegion(Connection con, String region) {
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
     * @return This method should return a Pair of the urban population and the rural population in that order.
     */
    public Pair<Integer, Integer> getPopulationUrbanRuralContinent(Connection con, String continent) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param region This method should take a region name as a String argument.
     * @return This method should return a Pair of the urban population and the rural population in that order.
     */
    public Pair<Integer, Integer> getPopulationUrbanRuralRegion(Connection con, String region) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param country This method should take a country name as a String argument.
     * @return This method should return a Pair of the urban population and the rural population in that order.
     */
    public Pair<Integer, Integer> getPopulationUrbanRuralCountry(Connection con, String country) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @return This method should return the int population of the world.
     */
    public int getPopulationWorld(Connection con) {
        return 0;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param continent This method should take a continent name as a String argument.
     * @return This method should return the int population of the world.
     */
    public int getPopulationContinent(Connection con, String continent) {
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
    public ArrayList<Country> getCountriesByPopulationWorld(Connection con) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param continent This method should take a continent name as a String argument.
     * @return This method should return an ArrayList of all countries in a continent in descending order of population.
     */
    public ArrayList<Country> getCountriesByPopulationRegion(Connection con, String continent) {
        return null;
    }

    /**
     * This method is a stub for unit testing. Put code in here to fulfil the requirements of the test.
     * @param con
     * @param district This method should take a district name as a String argument.
     * @return This method should return an ArrayList of all countries in a district in descending order of population.
     */
    public ArrayList<Country> getCountriesByPopulationDistrict(Connection con, String district) {
        return null;
    }




}
