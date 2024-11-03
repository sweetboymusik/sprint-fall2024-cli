package http.cli;

import domain.Airport;
import domain.City;
import http.client.RESTClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class CLIApplicationTest {
    @Mock
    private RESTClient mockRestClient;

    @InjectMocks
    private CLIApplication cliUnderTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateCityReport() {
        City city = new City();

        city.setId(1);
        city.setName("Toronto");
        city.setState("ON");
        city.setPopulation(1000000);

        List<City> cities = new ArrayList<>();
        cities.add(city);

        Mockito.when(mockRestClient.getAllCities()).thenReturn(cities);
        cliUnderTest.setRestClient(mockRestClient);

        Assertions.assertTrue(cliUnderTest.generateCityReport().contains("Toronto"));
        Assertions.assertTrue(cliUnderTest.generateCityReport().contains("ON"));
        Assertions.assertFalse(cliUnderTest.generateCityReport().contains("St. John's"));
    }

    @Test
    void testGenerateAirportReport() {
        Airport airport = new Airport();
        City city = new City();

        city.setId(1);
        city.setName("Toronto");
        city.setState("ON");
        city.setPopulation(1000000);

        airport.setId(1);
        airport.setName("Pearson");
        airport.setCode("YYZ");
        airport.setCity(city);

        List<Airport> airports = new ArrayList<>();
        airports.add(airport);

        Mockito.when(mockRestClient.getAllAirports()).thenReturn(airports);
        cliUnderTest.setRestClient(mockRestClient);

        Assertions.assertTrue(cliUnderTest.generateAirportReport().contains("Pearson"));
        Assertions.assertFalse(cliUnderTest.generateAirportReport().contains("YYT"));
        Assertions.assertNotNull(cliUnderTest.generateAirportReport());
    }
}
