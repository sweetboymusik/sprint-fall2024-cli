package http.cli;

import domain.*;
import http.client.RESTClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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

    // test instances
    private static List<City> cities;
    private static List<Airport> airports;
    private static List<Flight> flights;
    private static List<Passenger> passengers;

    @BeforeAll
    static void setup() {
        City city1 = new City();
        city1.setId(1);
        city1.setName("City1");
        city1.setState("ON");
        city1.setPopulation(1000000);

        City city2 = new City();
        city2.setId(2);
        city2.setName("City2");
        city2.setState("AB");
        city2.setPopulation(500000);


        Airport airport1 = new Airport();
        airport1.setId(1);
        airport1.setName("Airport1");
        airport1.setCode("AP1");
        airport1.setCity(city1);

        Airport airport2 = new Airport();
        airport2.setId(2);
        airport2.setName("Airport2");
        airport2.setCode("AP2");
        airport2.setCity(city2);

        city1.setAirports(List.of(airport1));
        city2.setAirports(List.of(airport2));

        airports = new ArrayList<>();
        airports.add(airport1);
        airports.add(airport2);

        cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        Airline airline1 = new Airline();
        airline1.setId(1);
        airline1.setName("Airline1");
        airline1.setCountry("Canada");

        Aircraft aircraft1 = new Aircraft();
        aircraft1.setId(1);
        aircraft1.setAirline(airline1);
        aircraft1.setType("747");
        aircraft1.setPassengerCapacity(80);

        Flight flight1 = new Flight();
        flight1.setId(1);
        flight1.setNumberOfPassengers(76);
        flight1.setOrigin(airport1);
        flight1.setDestination(airport2);
        flight1.setAircraft(aircraft1);

        Flight flight2 = new Flight();
        flight2.setId(2);
        flight2.setNumberOfPassengers(64);
        flight2.setOrigin(airport1);
        flight2.setDestination(airport2);
        flight2.setAircraft(aircraft1);

        flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);

        Passenger passenger1 = new Passenger();
        passenger1.setId(1);
        passenger1.setFirstName("John");
        passenger1.setLastName("Doe");
        passenger1.setEmail("john@doe.com");
        passenger1.setPhoneNumber("19098787636");
        passenger1.setCity(city1);
        passenger1.setFlights(flights);

        Passenger passenger2 = new Passenger();
        passenger2.setId(2);
        passenger2.setFirstName("Jane");
        passenger2.setLastName("Doe");
        passenger2.setEmail("jane@doe.com");
        passenger2.setPhoneNumber("18762563746");
        passenger2.setCity(city1);
        passenger2.setFlights(flights);

        passengers = new ArrayList<>();
        passengers.add(passenger1);
        passengers.add(passenger2);

    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateCityReport() {
        Mockito.when(mockRestClient.getAllCities()).thenReturn(cities);
        cliUnderTest.setRestClient(mockRestClient);

        Assertions.assertTrue(cliUnderTest.generateCityReport().contains("City1"));
        Assertions.assertTrue(cliUnderTest.generateCityReport().contains("AB"));
        Assertions.assertFalse(cliUnderTest.generateCityReport().contains("St. John's"));
    }

    @Test
    void testGenerateAirportReport() {
        Mockito.when(mockRestClient.getAllAirports()).thenReturn(airports);
        cliUnderTest.setRestClient(mockRestClient);

        Assertions.assertTrue(cliUnderTest.generateAirportReport().contains("AP1"));
        Assertions.assertFalse(cliUnderTest.generateAirportReport().contains("AP5"));
        Assertions.assertNotNull(cliUnderTest.generateAirportReport());
    }

    @Test
    public void testGenerateFlightReport() {
        Mockito.when(mockRestClient.getAllFlights()).thenReturn(flights);
        cliUnderTest.setRestClient(mockRestClient);

        Assertions.assertTrue(cliUnderTest.generateFlightReport().contains("64"));
        Assertions.assertFalse(cliUnderTest.generateFlightReport().contains("789"));
    }

    @Test
    public void testGeneratePassengerReport() {
        Mockito.when(mockRestClient.getAllPassengers()).thenReturn(passengers);
        cliUnderTest.setRestClient(mockRestClient);

        Assertions.assertTrue(cliUnderTest.generatePassengerReport().contains("John"));
        Assertions.assertFalse(cliUnderTest.generatePassengerReport().contains("Jack"));
    }

    @Test
    public void testGenerateCityAirportReport() {
        Mockito.when(mockRestClient.getAllCities()).thenReturn(cities);
        cliUnderTest.setRestClient(mockRestClient);

        Assertions.assertTrue(cliUnderTest.generateCityAirportReport().contains("AP1"));
        Assertions.assertTrue(cliUnderTest.generateCityAirportReport().contains("Airport2"));
        Assertions.assertFalse(cliUnderTest.generateCityAirportReport().contains("AP5"));
    }

    @Test
    public void testGeneratePassengerFlightReport() {
        Mockito.when(mockRestClient.getAllPassengers()).thenReturn(passengers);
        cliUnderTest.setRestClient(mockRestClient);

        Assertions.assertTrue(cliUnderTest.generatePassengerFlightReport().contains("John"));
        Assertions.assertTrue(cliUnderTest.generatePassengerFlightReport().contains("Jane"));
        Assertions.assertFalse(cliUnderTest.generatePassengerFlightReport().contains("789"));
    }

    @Test
    public void testGeneratePassengerAirportReport() {
        Mockito.when(mockRestClient.getAllPassengers()).thenReturn(passengers);
        cliUnderTest.setRestClient(mockRestClient);

        Assertions.assertTrue(cliUnderTest.generatePassengerAirportReport().contains("AP1"));
        Assertions.assertTrue(cliUnderTest.generatePassengerAirportReport().contains("Airport2"));
        Assertions.assertFalse(cliUnderTest.generatePassengerAirportReport().contains("AP5"));
    }
}
