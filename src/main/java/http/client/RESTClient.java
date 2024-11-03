package http.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Airport;
import domain.City;
import domain.Flight;
import domain.Passenger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class RESTClient {
    private String serverURL;
    private HttpClient client;

    public HttpResponse<String> httpSender(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            System.out.println("Error Status Code: " + response.statusCode());
        }

        return response;
    }

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(serverURL)).build();

        try {
            HttpResponse<String> response = httpSender(request);
            cities = buildCityListFromResponse(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return cities;
    }

    public List<Airport> getAllAirports() {
        List<Airport> airports = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(serverURL)).build();

        try {
            HttpResponse<String> response = httpSender(request);
            airports = buildAirportListFromResponse(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return airports;
    }

    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(serverURL)).build();

        try {
            HttpResponse<String> response = httpSender(request);
            flights = buildFlightsListFromResponse(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return flights;
    }

    public List<Passenger> getAllPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(serverURL)).build();

        try {
            HttpResponse<String> response = httpSender(request);
            passengers = buildPassengerListFromResponse(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return passengers;
    }

    public List<City> buildCityListFromResponse(String response) throws JsonProcessingException {
        List<City> cities = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        cities = mapper.readValue(response, new TypeReference<List<City>>() {
        });

        return cities;
    }

    public List<Airport> buildAirportListFromResponse(String response) throws JsonProcessingException {
        List<Airport> airports = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        airports = mapper.readValue(response, new TypeReference<ArrayList<Airport>>() {
        });

        return airports;
    }

    public List<Flight> buildFlightsListFromResponse(String response) throws JsonProcessingException {
        List<Flight> flights = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        flights = mapper.readValue(response, new TypeReference<ArrayList<Flight>>() {
        });

        return flights;
    }

    public List<Passenger> buildPassengerListFromResponse(String response) throws JsonProcessingException {
        List<Passenger> passengers = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        passengers = mapper.readValue(response, new TypeReference<List<Passenger>>() {
        });

        return passengers;
    }

    public HttpClient getClient() {
        if (client == null) {
            client = HttpClient.newHttpClient();
        }

        return client;
    }

    // getters and setters
    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }
}
