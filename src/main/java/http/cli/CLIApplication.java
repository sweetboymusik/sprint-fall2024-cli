package http.cli;

import domain.Airport;
import domain.City;
import domain.Flight;
import domain.Passenger;
import http.client.RESTClient;

import java.util.ArrayList;
import java.util.List;

public class CLIApplication {
    private RESTClient restClient;

    // getters and setters
    public RESTClient getRestClient() {
        if (restClient == null) {
            restClient = new RESTClient();
        }

        return restClient;
    }

    public void setRestClient(RESTClient restClient) {
        this.restClient = restClient;
    }

    // basic reports
    public String generateCityReport() {
        List<City> cities = getRestClient().getAllCities();
        StringBuilder cityReport = new StringBuilder();

        cityReport.append("City Report\n\n");

        for (City city : cities) {
            cityReport.append("ID: ").append(city.getId()).append("\n");
            cityReport.append("City: ").append(city.getName()).append("\n");
            cityReport.append("State/Province: ").append(city.getState()).append("\n");
            cityReport.append("Population: ").append(city.getPopulation()).append("\n");

            if (cities.indexOf(city) != cities.size() - 1) {
                cityReport.append("\n");
            }
        }

        return cityReport.toString();
    }

    public String generatePassengerReport() {
        List<Passenger> passengers = getRestClient().getAllPassengers();
        StringBuilder passengerReport = new StringBuilder();

        passengerReport.append("Passenger Report\n\n");

        for (Passenger passenger : passengers) {
            passengerReport.append("ID: ").append(passenger.getId()).append("\n");
            passengerReport.append("Name: ").append(passenger.getFirstName())
                    .append(" ")
                    .append(passenger.getLastName())
                    .append("\n");
            passengerReport.append("Email: ").append(passenger.getEmail()).append("\n");
            passengerReport.append("Phone Number: ").append(passenger.getPhoneNumber()).append("\n");
            passengerReport.append("City: ").append(passenger.getCity().getName()).append("\n");
        }

        return passengerReport.toString();
    }

    // requirement reports
    public String generateCityAirportReport() {
        List<City> cities = getRestClient().getAllCities();
        StringBuilder cityAirportReport = new StringBuilder();

        cityAirportReport.append("City-Airport Report\n");
        cityAirportReport.append("(What airports are in what cities)\n\n");

        for (City city : cities) {
            if (!city.getAirports().isEmpty()) {
                cityAirportReport.append("City: ").append(city.getName()).append("\n");

                for (Airport airport : city.getAirports()) {
                    cityAirportReport.append("\t  ")
                            .append(airport.getName())
                            .append(" (")
                            .append(airport.getCode())
                            .append(")")
                            .append("\n");
                }
            }
        }

        return cityAirportReport.toString();
    }

    public String generatePassengerFlightReport() {
        List<Passenger> passengers = getRestClient().getAllPassengers();
        StringBuilder passengerFlightReport = new StringBuilder();

        passengerFlightReport.append("Passenger Flight Report\n");
        passengerFlightReport.append("(List of aircraft/flights passengers have travelled on)\n\n");

        for (Passenger passenger : passengers) {
            passengerFlightReport.append("Passenger: ")
                    .append(passenger.getFirstName())
                    .append(" ")
                    .append(passenger.getLastName())
                    .append("\n");

            for (Flight flight : passenger.getFlights()) {
                passengerFlightReport.append("\t\t   ");
                passengerFlightReport.append(passenger.getFlights().indexOf(flight) + 1)
                        .append(". ");
                passengerFlightReport.append("Flight number ")
                        .append(flight.getId())
                        .append(" on ")
                        .append(flight.getAircraft().getAirline().getName())
                        .append(" (Aircraft ID: ")
                        .append(flight.getAircraft().getId())
                        .append(")\n");
            }

            passengerFlightReport.append("\n");
        }

        return passengerFlightReport.toString();
    }

    public String generatePassengerAirportReport() {
        List<Passenger> passengers = getRestClient().getAllPassengers();
        StringBuilder passengerAirportReport = new StringBuilder();

        passengerAirportReport.append("Passenger Airport Report\n");
        passengerAirportReport.append("(What airports have passengers used)\n\n");

        for (Passenger passenger : passengers) {
            passengerAirportReport.append("Passenger: ")
                    .append(passenger.getFirstName())
                    .append(" ")
                    .append(passenger.getLastName())
                    .append("\n");

            List<String> flights = new ArrayList<>();

            for (Flight flight : passenger.getFlights()) {
                flights.add(flight.getOrigin().getName() + " (" + flight.getOrigin().getCode() + ") - Origin");
                flights.add(flight.getDestination().getName() + " (" + flight.getDestination().getCode() + ") - Destination");
            }

            for (int i = 0; i < flights.size(); i++) {
                passengerAirportReport.append("\t\t   ");
                passengerAirportReport.append((i + 1))
                        .append(". ")
                        .append(flights.get(i))
                        .append("\n");
            }

            passengerAirportReport.append("\n");
        }

        return passengerAirportReport.toString();
    }

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }

        System.out.println();

        CLIApplication app = new CLIApplication();

        try {
            String serverURL = args[0];
            String reportToGenerate = args[1];

            if (serverURL != null && !serverURL.isEmpty()) {
                RESTClient restClient = new RESTClient();
                restClient.setServerURL(serverURL);
                app.setRestClient(restClient);

                if (reportToGenerate != null && !reportToGenerate.isEmpty()) {
                    switch (reportToGenerate) {
                        case "city-report":
                            System.out.println(app.generateCityReport());
                            break;
                        case "airport-report":
                            break;
                        case "flight-report":
                            break;
                        case "passenger-report":
                            System.out.println(app.generatePassengerReport());
                            break;
                        case "city-airport-report":
                            System.out.println(app.generateCityAirportReport());
                            break;
                        case "passenger-flight-report":
                            System.out.println(app.generatePassengerFlightReport());
                            break;
                        case "passenger-airport-report":
                            System.out.println(app.generatePassengerAirportReport());
                            break;
                        default:
                            System.out.println("Invalid report format");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}