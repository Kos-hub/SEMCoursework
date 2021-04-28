import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import com.napier.sem.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;


public class UnitTests {
    App app = new App();
    City testCity = new City();
    Country testCountry = new Country();
    CountryLanguage testLanguage = new CountryLanguage();
    Sql testSql = new Sql();
    SqlReturnsObjects testSqlReturnsObjects = new SqlReturnsObjects();
    Connection con = testSql.connect("sql:3306");

    @Test
    void exampleTest()
    {
        assertEquals(5, 5);
    }

    @Test
    void sqlConnectTest()
    {
        assertEquals(testSql.connect("asdasd"), null); // Bad argument for connection expected to cause connection failure
        assertEquals(testSql.connect(null), null);
        assertEquals(testSql.connect("sql:3306") instanceof Connection, true); // Good argument should result in a connection
    }

    @Test
    void appMainTest() throws IOException
    {
        String[] args = null;
        try {
            app.main(args);
            assertEquals(true, true); // Main doesn't crash on null arguments
        } catch(Exception e) {
            assertEquals(true, false);
        }
        args[0] = "testarg";
        args[1] = "testarg2";
        try {
            app.main(args);
            assertEquals(true, true); // Main doesn't crash on having arguments
        } catch(Exception e) {
            assertEquals(true, false);
        }
    }

    @Test
    void cityTest()
    {
        testCity.name = "testName";
        testCity.district = "testDistrict";
        testCity.country = "testCountry";
        testCity.population = 12;
        assertEquals(testCity.toString(), "testName testCountry testDistrict 12");
    }

    @Test
    void countryTest()
    {
        testCountry.name = "testName";
        testCountry.population = 14;
        testCountry.code = "testCode";
        testCountry.continent = "testContinent";
        testCountry.capital = 1234;
        testCountry.region = "testRegion";
        assertEquals(testCountry.toString(), "testCode testName testContinent testRegion 14 1234");
    }

    @Test
    void languageTest()
    {
        testLanguage.language = "testLanguage";
        testLanguage.countryCode = "testCode";
        testLanguage.isOfficial = true;
        testLanguage.percentage = 3.14;
        assertEquals(testLanguage.toString(), "testCode testLanguage true 3.14");
    }

    @Test
    void testSqlMethodsPrint()
    {
        // The following all print to console, and have no return. We're testing for crashes.
        testSql.getCitiesInWorld(con);
        testSql.topNCities(con, 10);
        testSql.getCountriesByWorld(con);

        // normal
        testSql.topNCitiesInContinent(con, "Asia", 10);
        testSql.topNCitiesInCountry(con, "Laos", 10);
        testSql.topNCitiesInDistrict(con, "Viengchan", 10);
        testSql.topNCitiesInRegion(con, "Southeast Asia", 10);
        testSql.getCitiesInContinent(con, "Asia");
        testSql.getCitiesInCountry(con, "Laos");
        testSql.getCitiesInDistrict(con, "Viengchan");
        testSql.getCountriesByContinent(con, "Asia");
        testSql.getCountriesByRegion(con, "Southeast Asia");
        testSql.topNCountriesInWorld(con, 10);
        testSql.topNCountriesInContinent(con, "Asia", 10);
        testSql.topNCountriesInRegion(con, "Southeast Asia", 10);
        testSql.getCitiesInRegion(con, "Southeast Asia");

        // bad string
        testSql.topNCitiesInContinent(con, "aasdgsdg", 10);
        testSql.topNCitiesInCountry(con, "sdgderfh", 10);
        testSql.topNCitiesInDistrict(con, "sdgrhrh", 10);
        testSql.topNCitiesInRegion(con, "dfhdfghdfgh", 10);
        testSql.getCitiesInContinent(con, "dgsdfgsdfg");
        testSql.getCitiesInCountry(con, "asdfsd");
        testSql.getCitiesInDistrict(con, "sdgufihsldkj");
        testSql.getCountriesByContinent(con, "asfkljsd;lkjs");
        testSql.getCountriesByRegion(con, "fdhgfjhk");
        testSql.topNCountriesInContinent(con, "fdsgsdfghdfh", 10);
        testSql.topNCountriesInRegion(con, "adgdsfgdfg", 10);
        testSql.getCitiesInRegion(con, "asfsdgsg");

        // bad number
        testSql.topNCitiesInContinent(con, "Asia", -1);
        testSql.topNCitiesInCountry(con, "Laos", -1);
        testSql.topNCitiesInDistrict(con, "Viengchan", -1);
        testSql.topNCitiesInRegion(con, "Southeast Asia", -1);
        testSql.topNCities(con, -1);
        testSql.topNCountriesInWorld(con, -1);
        testSql.topNCountriesInContinent(con, "Asia", -1);
        testSql.topNCountriesInRegion(con, "Southeast Asia", -1);

        // bad string and bad number
        testSql.topNCitiesInContinent(con, "asdgfhg", -1);
        testSql.topNCitiesInCountry(con, "gfdfhdh", -1);
        testSql.topNCitiesInDistrict(con, "gsgsfgfdg", -1);
        testSql.topNCitiesInRegion(con, "sadglksdgjk", -1);
        testSql.topNCountriesInContinent(con, "dsfhdfgjf", -1);
        testSql.topNCountriesInRegion(con, "adfgklsdjg", -1);

        // exceptional
        testSql.topNCitiesInContinent(con, null, 0);
        testSql.topNCitiesInCountry(con, null, 0);
        testSql.topNCitiesInDistrict(con, null, 0);
        testSql.topNCitiesInRegion(con, null, 0);
        testSql.getCitiesInContinent(con, null);
        testSql.getCitiesInCountry(con, null);
        testSql.getCitiesInDistrict(con, null);
        testSql.getCountriesByContinent(con, null);
        testSql.getCountriesByRegion(con, null);
        testSql.topNCountriesInContinent(con, null, 0);
        testSql.topNCountriesInRegion(con, null, 0);
        testSql.getCitiesInRegion(con, null);
    }

    @Test
    void testCapitalCitiesWorld() {
        // Normal input
        // Arrange & Act
        ArrayList<City> returnValue = testSqlReturnsObjects.getCapitalCitiesWorld(con, 10);
        // Assert
        assertEquals(returnValue.size(), 10);
        assertEquals(returnValue.get(0).name, "Seoul");
        assertEquals(returnValue.get(0).country, "South Korea");
        assertEquals(returnValue.get(0).population, 9981619);
        // Exceptional input
        // Act
        returnValue = testSqlReturnsObjects.getCapitalCitiesWorld(con, -1);
        // Assert
        assertNull(returnValue);
    }

    @Test
    void testCapitalCitiesContinent() {
        // Normal input
        // Arrange & Act
        ArrayList<City> returnValue = testSqlReturnsObjects.getCapitalCitiesContinent(con, "Europe", 10);
        // Assert
        assertEquals(returnValue.size(), 10);
        assertEquals(returnValue.get(2).name, "Berlin");
        assertEquals(returnValue.get(2).population,3386667);
        // Extreme input
        // Act
        returnValue = testSqlReturnsObjects.getCapitalCitiesContinent(con, "Europe", 0);
        // Assert
        assertEquals(returnValue.size(), 0);
        // Act
        returnValue = testSqlReturnsObjects.getCapitalCitiesContinent(con, "Europe", 10000);
        // Assert
        assertEquals(returnValue.size(), 46);
        // Exceptional input
        // Act
        returnValue = testSqlReturnsObjects.getCapitalCitiesContinent(con, "Europe", -1);
        // Assert
        assertNull(returnValue);
        // Act
        returnValue = testSqlReturnsObjects.getCapitalCitiesContinent(con, "EEEEEEEE000&", 4);
        // Assert
        assertNull(returnValue);
    }

    @Test
    void testCapitalCitiesRegion() {
        // Normal input
        // Arrange & Act
        ArrayList<City> returnValue = testSqlReturnsObjects.getCapitalCitiesRegion(con, "Southeast Asia", 6);
        // Assert
        assertEquals(returnValue.size(), 6);
        assertEquals(returnValue.get(2).name, "Singapore");
        assertEquals(returnValue.get(2).population,4017733);
        // Extreme input
        // Act
        returnValue = testSqlReturnsObjects.getCapitalCitiesRegion(con, "Southeast Asia", 0);
        // Assert
        assertEquals(returnValue.size(), 0);
        // Act
        returnValue = testSqlReturnsObjects.getCapitalCitiesRegion(con, "Southeast Asia", 10000);
        // Assert
        assertEquals(returnValue.size(), 11);
        // Exceptional input
        // Act
        returnValue = testSqlReturnsObjects.getCapitalCitiesRegion(con, "Southeast Asia", -1);
        // Assert
        assertNull(returnValue);
        // Act
        returnValue = testSqlReturnsObjects.getCapitalCitiesRegion(con, "EEEEEEEE000&", 4);
        // Assert
        assertNull(returnValue);
    }

    @Test
    void testLanguageSpeakers() {
        // Normal input
        // Arrange & Act
        ArrayList<String> testLanguages = new ArrayList<>();
        testLanguages.add("Chinese");
        ArrayList<String> returnValue = testSqlReturnsObjects.getLanguageSpeakers(con, testLanguages);
        // Assert
        assertEquals(returnValue.get(1), "1191843539");
        // Act
        testLanguages.remove(0);
        testLanguages.add("Spanish");
        returnValue = testSqlReturnsObjects.getLanguageSpeakers(con, testLanguages);
        // Assert
        assertEquals(returnValue.get(1), "355029460");
    }

    @Test
    void testPopulationUrbanRuralContinent() {
        // Normal input
        // Arrange & Act
        ArrayList<Long> returnValue = testSqlReturnsObjects.getPopulationUrbanRuralContinent(con, "Asia");
        // Assert
        assertEquals(returnValue.get(0), 697604103);
        assertEquals(returnValue.get(1), 3007421597L); // In java we need to specify a long integer constant with an 'L' suffix.
        // Act
        returnValue = testSqlReturnsObjects.getPopulationUrbanRuralContinent(con, "Africa");
        // Assert
        assertEquals(returnValue.get(0), 135838579);
        assertEquals(returnValue.get(1), 648636421);
    }

    @Test
    void testPopulationUrbanRuralRegion() {
        // Normal input
        // Arrange & Act
        ArrayList<Long> returnValue = testSqlReturnsObjects.getPopulationUrbanRuralRegion(con, "Eastern Asia");
        // Assert
        assertEquals(returnValue.get(0), 317476534);
        assertEquals(returnValue.get(1), 1189851466);
        // Act
        returnValue = testSqlReturnsObjects.getPopulationUrbanRuralRegion(con, "Micronesia");
        // Assert
        assertEquals(returnValue.get(0), 102329);
        assertEquals(returnValue.get(1), 440671);
    }

    @Test
    void testPopulationUrbanRuralCountry() {
        // Normal input
        // Arrange & Act
        ArrayList<Long> returnValue = testSqlReturnsObjects.getPopulationUrbanRuralCountry(con, "China");
        // Assert
        assertEquals(returnValue.get(0), 175953614);
        assertEquals(returnValue.get(1), 1101604386);
        // Act
        returnValue = testSqlReturnsObjects.getPopulationUrbanRuralCountry(con, "Montserrat");
        // Assert
        assertEquals(returnValue.get(0), 2000);
        assertEquals(returnValue.get(1), 9000);
    }

    @Test
    void testPopulationWorld() {
        // Arrange & Act
        long returnValue = testSqlReturnsObjects.getPopulationWorld(con);
        // Assert
        assertEquals(returnValue,6078749450L);
    }

    @Test
    void testPopulationContinent() {
        // Normal input
        // Arrange & Act
        long returnValue = testSqlReturnsObjects.getPopulationContinent(con, "Asia");
        // Assert
        assertEquals(returnValue,3705025700L);
        // Act
        returnValue = testSqlReturnsObjects.getPopulationContinent(con, "Africa");
        // Assert
        assertEquals(returnValue,784475000);
        // Exceptional input
        // Act
        returnValue = testSqlReturnsObjects.getPopulationContinent(con, "EEEEEEEE000&");
        // Assert
        assertEquals(returnValue,-1);
    }

    @Test
    void testPopulationRegion() {
        // Normal input
        // Arrange & Act
        int returnValue = testSqlReturnsObjects.getPopulationRegion(con, "Southeast Asia");
        // Assert
        assertEquals(returnValue,518541000);
        // Act
        returnValue = testSqlReturnsObjects.getPopulationRegion(con, "Western Europe");
        // Assert
        assertEquals(returnValue,183247600);
        // Exceptional input
        // Act
        returnValue = testSqlReturnsObjects.getPopulationRegion(con, "EEEEEEEE000&");
        // Assert
        assertEquals(returnValue,-1);
    }

    @Test
    void testPopulationCountry() {
        // Normal input
        // Arrange & Act
        int returnValue = testSqlReturnsObjects.getPopulationCountry(con, "Laos");
        // Assert
        assertEquals(returnValue,5433000);
        // Act
        returnValue = testSqlReturnsObjects.getPopulationCountry(con, "Bolivia");
        // Assert
        assertEquals(returnValue,8329000);
        // Exceptional input
        // Act
        returnValue = testSqlReturnsObjects.getPopulationCountry(con, "EEEEEEEE000&");
        // Assert
        assertEquals(returnValue,-1);
    }

    @Test
    void testPopulationDistrict() {
        // Normal input
        // Arrange & Act
        int returnValue = testSqlReturnsObjects.getPopulationDistrict(con, "Viangchan");
        // Assert
        assertEquals(returnValue,531800);
        // Act
        returnValue = testSqlReturnsObjects.getPopulationDistrict(con, "Potosí");
        // Assert
        assertEquals(returnValue,140642);
        // Exceptional input
        // Act
        returnValue = testSqlReturnsObjects.getPopulationDistrict(con, "EEEEEEEE000&");
        // Assert
        assertEquals(returnValue,-1);
    }

    @Test
    void testPopulationCity() {
        // Normal input
        // Arrange & Act
        int returnValue = testSqlReturnsObjects.getPopulationCity(con, "Vientiane");
        // Assert
        assertEquals(returnValue,531800);
        // Act
        returnValue = testSqlReturnsObjects.getPopulationCity(con, "Sucre");
        // Assert
        assertEquals(returnValue,178426);
        // Exceptional input
        // Act
        returnValue = testSqlReturnsObjects.getPopulationCity(con, "EEEEEEEE000&");
        // Assert
        assertEquals(returnValue,-1);
    }

    @Test
    void testCitiesByPopulationWorld() {
        // Normal input
        // Arrange & Act
        ArrayList<City> returnValue = testSqlReturnsObjects.getCitiesByPopulationWorld(con, 5);
        // Assert
        assertEquals(returnValue.size(), 5);
        assertEquals(returnValue.get(0).name, "Mumbai (Bombay)");
        assertEquals(returnValue.get(0).population,10500000);
        // Extreme input
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationWorld(con, 0);
        // Assert
        assertEquals(returnValue.size(), 0);
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationWorld(con, 10000);
        // Assert
        assertEquals(returnValue.size(), 4079);
        // Exceptional input
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationWorld(con, -1);
        // Assert
        assertNull(returnValue);
    }

    @Test
    void testCitiesByPopulationContinent() {
        // Normal input
        // Arrange & Act
        ArrayList<City> returnValue = testSqlReturnsObjects.getCitiesByPopulationContinent(con, "Asia", 4);
        // Assert
        assertEquals(returnValue.size(), 4);
        assertEquals(returnValue.get(2).name, "Shanghai");
        assertEquals(returnValue.get(2).population,9696300);
        // Extreme input
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationContinent(con, "Africa", 0);
        // Assert
        assertEquals(returnValue.size(), 0);
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationContinent(con, "Europe", 10000);
        // Assert
        assertEquals(returnValue.size(), 841);
        // Exceptional input
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationContinent(con, "Asia", -1);
        // Assert
        assertNull(returnValue);
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationContinent(con, "EEEEEEEE000&", 4);
        // Assert
        assertNull(returnValue);
    }

    @Test
    void testCitiesByPopulationRegion() {
        // Normal input
        // Arrange & Act
        ArrayList<City> returnValue = testSqlReturnsObjects.getCitiesByPopulationRegion(con, "Southeast Asia", 4);
        // Assert
        assertEquals(returnValue.size(), 4);
        assertEquals(returnValue.get(2).name, "Singapore");
        assertEquals(returnValue.get(2).population,4017733);
        // Extreme input
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationRegion(con, "Southeast Asia", 0);
        // Assert
        assertEquals(returnValue.size(), 0);
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationRegion(con, "Southeast Asia", 10000);
        // Assert
        assertEquals(returnValue.size(), 297);
        // Exceptional input
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationRegion(con, "Southeast Asia", -1);
        // Assert
        assertNull(returnValue);
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationRegion(con, "EEEEEEEE000&", 4);
        // Assert
        assertNull(returnValue);
    }

    @Test
    void testCitiesByPopulationCountry() {
        // Normal input
        // Arrange & Act
        ArrayList<City> returnValue = testSqlReturnsObjects.getCitiesByPopulationCountry(con, "Laos", 6);
        // Assert
        assertEquals(returnValue.size(), 2);
        assertEquals(returnValue.get(0).name, "Vientiane");
        assertEquals(returnValue.get(0).population,531800);
        // Extreme input
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationCountry(con, "Laos", 0);
        // Assert
        assertEquals(returnValue.size(), 0);
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationCountry(con, "Laos", 10000);
        // Assert
        assertEquals(returnValue.size(), 2);
        // Exceptional input
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationCountry(con, "Laos", -1);
        // Assert
        assertNull(returnValue);
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationCountry(con, "EEEEEEEE000&", 4);
        // Assert
        assertNull(returnValue);
    }

    @Test
    void testCitiesByPopulationDistrict() {
        // Normal input
        // Arrange & Act
        ArrayList<City> returnValue = testSqlReturnsObjects.getCitiesByPopulationDistrict(con, "Zuid-Holland", 10);
        // Assert
        assertEquals(returnValue.size(), 4);
        assertEquals(returnValue.get(2).name, "Dordrecht");
        assertEquals(returnValue.get(2).population,119811);
        // Extreme input
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationDistrict(con, "Zuid-Holland", 0);
        // Assert
        assertEquals(returnValue.size(), 0);
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationDistrict(con, "Zuid-Holland", 10000);
        // Assert
        assertEquals(returnValue.size(), 4);
        // Exceptional input
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationDistrict(con, "Zuid-Holland", -1);
        // Assert
        assertNull(returnValue);
        // Act
        returnValue = testSqlReturnsObjects.getCitiesByPopulationDistrict(con, "EEEEEEEE000&", 4);
        // Assert
        assertNull(returnValue);
    }

    @Test
    void testCountriesByPopulationWorld() {
        // Normal input
        // Arrange & Act
        ArrayList<Country> returnValue = testSqlReturnsObjects.getCountriesByPopulationWorld(con, 10);
        // Assert
        assertEquals(returnValue.size(), 10);
        assertEquals(returnValue.get(9).name, "Nigeria");
        assertEquals(returnValue.get(9).population,111506000);
        // Extreme input
        // Act
        returnValue = testSqlReturnsObjects.getCountriesByPopulationWorld(con, 0);
        // Assert
        assertEquals(returnValue.size(), 0);
        // Act
        returnValue = testSqlReturnsObjects.getCountriesByPopulationWorld(con, 10000);
        // Assert
        assertEquals(returnValue.size(), 239);
        // Exceptional input
        // Act
        returnValue = testSqlReturnsObjects.getCountriesByPopulationWorld(con, -1);
        // Assert
        assertNull(returnValue);
    }

    @Test
    void testCountriesByPopulationContinent() {
        // Normal input
        // Arrange & Act
        ArrayList<Country> returnValue = testSqlReturnsObjects.getCountriesByPopulationContinent(con, "Africa", 5);
        // Assert
        assertEquals(returnValue.size(), 5);
        assertEquals(returnValue.get(1).name, "Egypt");
        assertEquals(returnValue.get(1).population,68470000);
        // Extreme input
        // Act
        returnValue = testSqlReturnsObjects.getCountriesByPopulationContinent(con, "Africa", 0);
        // Assert
        assertEquals(returnValue.size(), 0);
        // Act
        returnValue = testSqlReturnsObjects.getCountriesByPopulationContinent(con, "Africa", 10000);
        // Assert
        assertEquals(returnValue.size(), 58);
        // Exceptional input
        // Act
        returnValue = testSqlReturnsObjects.getCountriesByPopulationContinent(con, "Africa", -1);
        // Assert
        assertNull(returnValue);
        // Act
        returnValue = testSqlReturnsObjects.getCountriesByPopulationContinent(con, "EEEEEEEE000&", 4);
        // Assert
        assertNull(returnValue);
    }

    @Test
    void testCountriesByPopulationRegion() {
        // Normal input
        // Arrange & Act
        ArrayList<Country> returnValue = testSqlReturnsObjects.getCountriesByPopulationRegion(con, "Southeast Asia", 6);
        // Assert
        assertEquals(returnValue.size(), 6);
        assertEquals(returnValue.get(1).name, "Vietnam");
        assertEquals(returnValue.get(1).population,79832000);
        // Extreme input
        // Act
        returnValue = testSqlReturnsObjects.getCountriesByPopulationRegion(con, "Southeast Asia", 0);
        // Assert
        assertEquals(returnValue.size(), 0);
        // Act
        returnValue = testSqlReturnsObjects.getCountriesByPopulationRegion(con, "Southeast Asia", 10000);
        // Assert
        assertEquals(returnValue.size(), 11);
        // Exceptional input
        // Act
        returnValue = testSqlReturnsObjects.getCountriesByPopulationRegion(con, "Southeast Asia", -1);
        // Assert
        assertNull(returnValue);
        // Act
        returnValue = testSqlReturnsObjects.getCountriesByPopulationRegion(con, "EEEEEEEE000&", 4);
        // Assert
        assertNull(returnValue);
    }

    @Test
    void disconnectSql() {
        testSql.disconnect();
        testSqlReturnsObjects.disconnect();
    }
}
