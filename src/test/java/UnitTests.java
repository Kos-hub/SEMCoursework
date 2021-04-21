import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import com.napier.sem.*;

import java.sql.Connection;
import java.util.ArrayList;

public class UnitTests {
    Sql db = new Sql();
    Connection con = db.connect("sql:3306");

    @Test
    void exampleTest()
    {
        assertEquals(5, 5);
    }

    @Test
    void testCapitalCitiesWorld() {
        // Normal input
            // Arrange & Act
            ArrayList<City> returnValue = db.getCapitalCitiesWorld(con, 10);
            // Assert
            assertEquals(returnValue.size(), 10);
            assertEquals(returnValue.get(0).name, "Seoul");
            assertEquals(returnValue.get(0).country, "South Korea");
            assertEquals(returnValue.get(0).population, 9981619);
        // Exceptional input
            // Act
            returnValue = db.getCapitalCitiesWorld(con, -1);
            // Assert
            assertNull(returnValue);
    }

    @Test
    void testCapitalCitiesContinent() {
        // Normal input
            // Arrange & Act
            ArrayList<City> returnValue = db.getCapitalCitiesContinent(con, "Europe", 10);
            // Assert
            assertEquals(returnValue.size(), 10);
            assertEquals(returnValue.get(2).name, "Berlin");
            assertEquals(returnValue.get(2).population,3386667);
        // Extreme input
            // Act
            returnValue = db.getCapitalCitiesContinent(con, "Europe", 0);
            // Assert
            assertEquals(returnValue.size(), 0);
            // Act
            returnValue = db.getCapitalCitiesContinent(con, "Europe", 10000);
            // Assert
            assertEquals(returnValue.size(), 46);
        // Exceptional input
            // Act
            returnValue = db.getCapitalCitiesContinent(con, "Europe", -1);
            // Assert
            assertNull(returnValue);
            // Act
            returnValue = db.getCapitalCitiesContinent(con, "EEEEEEEE000&", 4);
            // Assert
            assertNull(returnValue);
    }

    @Test
    void testCapitalCitiesRegion() {
        // Normal input
            // Arrange & Act
            ArrayList<City> returnValue = db.getCapitalCitiesRegion(con, "Southeast Asia", 6);
            // Assert
            assertEquals(returnValue.size(), 6);
            assertEquals(returnValue.get(2).name, "Singapore");
            assertEquals(returnValue.get(2).population,4017733);
        // Extreme input
            // Act
            returnValue = db.getCapitalCitiesRegion(con, "Southeast Asia", 0);
            // Assert
            assertEquals(returnValue.size(), 0);
            // Act
            returnValue = db.getCapitalCitiesRegion(con, "Southeast Asia", 10000);
            // Assert
            assertEquals(returnValue.size(), 11);
        // Exceptional input
            // Act
            returnValue = db.getCapitalCitiesRegion(con, "Southeast Asia", -1);
            // Assert
            assertNull(returnValue);
            // Act
            returnValue = db.getCapitalCitiesRegion(con, "EEEEEEEE000&", 4);
            // Assert
            assertNull(returnValue);
    }

    @Test
    void testLanguageSpeakers() {
        // Normal input
            // Arrange & Act
            int returnValue = db.getLanguageSpeakers(con, "Chinese");
            // Assert
            assertEquals(returnValue, 1191843539);
            // Act
            returnValue = db.getLanguageSpeakers(con, "Spanish");
            // Assert
            assertEquals(returnValue, 355029460);
    }

    @Test
    void testPopulationUrbanRuralContinent() {
        // Normal input
            // Arrange & Act
            ArrayList<Long> returnValue = db.getPopulationUrbanRuralContinent(con, "Asia");
            // Assert
            assertEquals(returnValue.get(0), 697604103);
            assertEquals(returnValue.get(1), 3007421597L); // In java we need to specify a long integer constant with an 'L' suffix.
            // Act
            returnValue = db.getPopulationUrbanRuralContinent(con, "Africa");
            // Assert
            assertEquals(returnValue.get(0), 135838579);
            assertEquals(returnValue.get(1), 648636421);
    }

    @Test
    void testPopulationUrbanRuralRegion() {
        // Normal input
            // Arrange & Act
            ArrayList<Long> returnValue = db.getPopulationUrbanRuralRegion(con, "Eastern Asia");
            // Assert
            assertEquals(returnValue.get(0), 317476534);
            assertEquals(returnValue.get(1), 1189851466);
            // Act
            returnValue = db.getPopulationUrbanRuralRegion(con, "Micronesia");
            // Assert
            assertEquals(returnValue.get(0), 102329);
            assertEquals(returnValue.get(1), 440671);
    }

    @Test
    void testPopulationUrbanRuralCountry() {
        // Normal input
            // Arrange & Act
            ArrayList<Long> returnValue = db.getPopulationUrbanRuralCountry(con, "China");
            // Assert
            assertEquals(returnValue.get(0), 175953614);
            assertEquals(returnValue.get(1), 1101604386);
            // Act
            returnValue = db.getPopulationUrbanRuralCountry(con, "Montserrat");
            // Assert
            assertEquals(returnValue.get(0), 2000);
            assertEquals(returnValue.get(1), 9000);
    }

    @Test
    void testPopulationWorld() {
        // Arrange & Act
        long returnValue = db.getPopulationWorld(con);
        // Assert
        assertEquals(returnValue,6078749450L);
    }

    @Test
    void testPopulationContinent() {
        // Normal input
            // Arrange & Act
            long returnValue = db.getPopulationContinent(con, "Asia");
            // Assert
            assertEquals(returnValue,3705025700L);
            // Act
            returnValue = db.getPopulationContinent(con, "Africa");
            // Assert
            assertEquals(returnValue,784475000);
        // Exceptional input
            // Act
            returnValue = db.getPopulationContinent(con, "EEEEEEEE000&");
            // Assert
            assertEquals(returnValue,-1);
    }

    @Test
    void testPopulationRegion() {
        // Normal input
            // Arrange & Act
            int returnValue = db.getPopulationRegion(con, "Southeast Asia");
            // Assert
            assertEquals(returnValue,518541000);
            // Act
            returnValue = db.getPopulationRegion(con, "Western Europe");
            // Assert
            assertEquals(returnValue,183247600);
        // Exceptional input
            // Act
            returnValue = db.getPopulationRegion(con, "EEEEEEEE000&");
            // Assert
            assertEquals(returnValue,-1);
    }

    @Test
    void testPopulationCountry() {
        // Normal input
            // Arrange & Act
            int returnValue = db.getPopulationCountry(con, "Laos");
            // Assert
            assertEquals(returnValue,5433000);
            // Act
            returnValue = db.getPopulationCountry(con, "Bolivia");
            // Assert
            assertEquals(returnValue,8329000);
        // Exceptional input
            // Act
            returnValue = db.getPopulationCountry(con, "EEEEEEEE000&");
            // Assert
            assertEquals(returnValue,-1);
    }

    @Test
    void testPopulationDistrict() {
        // Normal input
            // Arrange & Act
            int returnValue = db.getPopulationDistrict(con, "Viangchan");
            // Assert
            assertEquals(returnValue,531800);
            // Act
            returnValue = db.getPopulationDistrict(con, "Potosí");
            // Assert
            assertEquals(returnValue,140642);
        // Exceptional input
            // Act
            returnValue = db.getPopulationDistrict(con, "EEEEEEEE000&");
            // Assert
            assertEquals(returnValue,-1);
    }

    @Test
    void testPopulationCity() {
        // Normal input
            // Arrange & Act
            int returnValue = db.getPopulationCity(con, "Vientiane");
            // Assert
            assertEquals(returnValue,531800);
            // Act
            returnValue = db.getPopulationCity(con, "Sucre");
            // Assert
            assertEquals(returnValue,178426);
        // Exceptional input
            // Act
            returnValue = db.getPopulationCity(con, "EEEEEEEE000&");
            // Assert
            assertEquals(returnValue,-1);
    }

    @Test
    void testCitiesByPopulationWorld() {
        // Normal input
            // Arrange & Act
            ArrayList<City> returnValue = db.getCitiesByPopulationWorld(con, 5);
            // Assert
            assertEquals(returnValue.size(), 5);
            assertEquals(returnValue.get(0).name, "Mumbai (Bombay)");
            assertEquals(returnValue.get(0).population,10500000);
        // Extreme input
            // Act
            returnValue = db.getCitiesByPopulationWorld(con, 0);
            // Assert
            assertEquals(returnValue.size(), 0);
            // Act
            returnValue = db.getCitiesByPopulationWorld(con, 10000);
            // Assert
            assertEquals(returnValue.size(), 4079);
        // Exceptional input
            // Act
            returnValue = db.getCitiesByPopulationWorld(con, -1);
            // Assert
            assertNull(returnValue);
    }

    @Test
    void testCitiesByPopulationContinent() {
        // Normal input
            // Arrange & Act
            ArrayList<City> returnValue = db.getCitiesByPopulationContinent(con, "Asia", 4);
            // Assert
            assertEquals(returnValue.size(), 4);
            assertEquals(returnValue.get(2).name, "Shanghai");
            assertEquals(returnValue.get(2).population,9696300);
        // Extreme input
            // Act
            returnValue = db.getCitiesByPopulationContinent(con, "Africa", 0);
            // Assert
            assertEquals(returnValue.size(), 0);
            // Act
            returnValue = db.getCitiesByPopulationContinent(con, "Europe", 10000);
            // Assert
            assertEquals(returnValue.size(), 841);
        // Exceptional input
            // Act
            returnValue = db.getCitiesByPopulationContinent(con, "Asia", -1);
            // Assert
            assertNull(returnValue);
            // Act
            returnValue = db.getCitiesByPopulationContinent(con, "EEEEEEEE000&", 4);
            // Assert
            assertNull(returnValue);
    }

    @Test
    void testCitiesByPopulationRegion() {
        // Normal input
            // Arrange & Act
            ArrayList<City> returnValue = db.getCitiesByPopulationRegion(con, "Southeast Asia", 4);
            // Assert
            assertEquals(returnValue.size(), 4);
            assertEquals(returnValue.get(2).name, "Singapore");
            assertEquals(returnValue.get(2).population,4017733);
        // Extreme input
            // Act
            returnValue = db.getCitiesByPopulationRegion(con, "Southeast Asia", 0);
            // Assert
            assertEquals(returnValue.size(), 0);
            // Act
            returnValue = db.getCitiesByPopulationRegion(con, "Southeast Asia", 10000);
            // Assert
            assertEquals(returnValue.size(), 297);
        // Exceptional input
            // Act
            returnValue = db.getCitiesByPopulationRegion(con, "Southeast Asia", -1);
            // Assert
            assertNull(returnValue);
            // Act
            returnValue = db.getCitiesByPopulationRegion(con, "EEEEEEEE000&", 4);
            // Assert
            assertNull(returnValue);
    }

    @Test
    void testCitiesByPopulationCountry() {
        // Normal input
            // Arrange & Act
            ArrayList<City> returnValue = db.getCitiesByPopulationCountry(con, "Laos", 6);
            // Assert
            assertEquals(returnValue.size(), 2);
            assertEquals(returnValue.get(0).name, "Vientiane");
            assertEquals(returnValue.get(0).population,531800);
        // Extreme input
            // Act
            returnValue = db.getCitiesByPopulationCountry(con, "Laos", 0);
            // Assert
            assertEquals(returnValue.size(), 0);
            // Act
            returnValue = db.getCitiesByPopulationCountry(con, "Laos", 10000);
            // Assert
            assertEquals(returnValue.size(), 2);
        // Exceptional input
            // Act
            returnValue = db.getCitiesByPopulationCountry(con, "Laos", -1);
            // Assert
            assertNull(returnValue);
            // Act
            returnValue = db.getCitiesByPopulationCountry(con, "EEEEEEEE000&", 4);
            // Assert
            assertNull(returnValue);
    }

    @Test
    void testCitiesByPopulationDistrict() {
        // Normal input
            // Arrange & Act
            ArrayList<City> returnValue = db.getCitiesByPopulationDistrict(con, "Zuid-Holland", 10);
            // Assert
            assertEquals(returnValue.size(), 4);
            assertEquals(returnValue.get(2).name, "Dordrecht");
            assertEquals(returnValue.get(2).population,119811);
        // Extreme input
            // Act
            returnValue = db.getCitiesByPopulationDistrict(con, "Zuid-Holland", 0);
            // Assert
            assertEquals(returnValue.size(), 0);
            // Act
            returnValue = db.getCitiesByPopulationDistrict(con, "Zuid-Holland", 10000);
            // Assert
            assertEquals(returnValue.size(), 4);
        // Exceptional input
            // Act
            returnValue = db.getCitiesByPopulationDistrict(con, "Zuid-Holland", -1);
            // Assert
            assertNull(returnValue);
            // Act
            returnValue = db.getCitiesByPopulationDistrict(con, "EEEEEEEE000&", 4);
            // Assert
            assertNull(returnValue);
    }

    @Test
    void testCountriesByPopulationWorld() {
        // Normal input
            // Arrange & Act
            ArrayList<Country> returnValue = db.getCountriesByPopulationWorld(con, 10);
            // Assert
            assertEquals(returnValue.size(), 10);
            assertEquals(returnValue.get(9).Name, "Nigeria");
            assertEquals(returnValue.get(9).Population,111506000);
        // Extreme input
            // Act
            returnValue = db.getCountriesByPopulationWorld(con, 0);
            // Assert
            assertEquals(returnValue.size(), 0);
            // Act
            returnValue = db.getCountriesByPopulationWorld(con, 10000);
            // Assert
            assertEquals(returnValue.size(), 239);
        // Exceptional input
            // Act
            returnValue = db.getCountriesByPopulationWorld(con, -1);
            // Assert
            assertNull(returnValue);
    }

    @Test
    void testCountriesByPopulationContinent() {
        // Normal input
            // Arrange & Act
            ArrayList<Country> returnValue = db.getCountriesByPopulationContinent(con, "Africa", 5);
            // Assert
            assertEquals(returnValue.size(), 5);
            assertEquals(returnValue.get(1).Name, "Egypt");
            assertEquals(returnValue.get(1).Population,68470000);
        // Extreme input
            // Act
            returnValue = db.getCountriesByPopulationContinent(con, "Africa", 0);
            // Assert
            assertEquals(returnValue.size(), 0);
            // Act
            returnValue = db.getCountriesByPopulationContinent(con, "Africa", 10000);
            // Assert
            assertEquals(returnValue.size(), 58);
        // Exceptional input
            // Act
            returnValue = db.getCountriesByPopulationContinent(con, "Africa", -1);
            // Assert
            assertNull(returnValue);
            // Act
            returnValue = db.getCountriesByPopulationContinent(con, "EEEEEEEE000&", 4);
            // Assert
            assertNull(returnValue);
    }

    @Test
    void testCountriesByPopulationRegion() {
        // Normal input
            // Arrange & Act
            ArrayList<Country> returnValue = db.getCountriesByPopulationRegion(con, "Southeast Asia", 6);
            // Assert
            assertEquals(returnValue.size(), 6);
            assertEquals(returnValue.get(1).Name, "Vietnam");
            assertEquals(returnValue.get(1).Population,79832000);
        // Extreme input
            // Act
            returnValue = db.getCountriesByPopulationRegion(con, "Southeast Asia", 0);
            // Assert
            assertEquals(returnValue.size(), 0);
            // Act
            returnValue = db.getCountriesByPopulationRegion(con, "Southeast Asia", 10000);
            // Assert
            assertEquals(returnValue.size(), 11);
        // Exceptional input
            // Act
            returnValue = db.getCountriesByPopulationRegion(con, "Southeast Asia", -1);
            // Assert
            assertNull(returnValue);
            // Act
            returnValue = db.getCountriesByPopulationRegion(con, "EEEEEEEE000&", 4);
            // Assert
            assertNull(returnValue);
    }
}
