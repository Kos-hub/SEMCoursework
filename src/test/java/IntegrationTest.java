import com.napier.sem.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {
    static SqlReturnsObjects app;
    static Connection con;

    @BeforeAll
    static void init()
    {
        app = new SqlReturnsObjects();
        con = app.connect("localhost:33060"); //Give the localhost port otherwise tests will throw errors
    }

    @Test
    void testGetCity()
    {
        City city = app.getCity(con);
        assertEquals(city.name, "Mumbai (Bombay)");
        assertEquals(city.population, 10500000);
    }

    @Test
    void testGetCountry()
    {
        Country country = app.getCountry(con);
        assertEquals(country.name, "China");
        assertEquals(country.population, 1277558000);
    }

    @Test
    void testGetCountryLanguage()
    {
        CountryLanguage countryLanguage = app.getCountryLanguage(con);
        assertEquals(countryLanguage.language, "Dutch");
    }
}
