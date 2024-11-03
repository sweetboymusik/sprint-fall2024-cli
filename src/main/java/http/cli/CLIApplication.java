package http.cli;

import domain.Airport;
import domain.City;
import http.client.RESTClient;

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

    public String generateCityAirportReport() {
        List<City> cities = getRestClient().getAllCities();
        StringBuilder cityReport = new StringBuilder();

        cityReport.append("City-Airport Report\n");
        cityReport.append("(What airports are in what cities)\n\n");

        for (City city : cities) {
            if (!city.getAirports().isEmpty()) {
                cityReport.append("City: ").append(city.getName()).append("\n");

                for (Airport airport : city.getAirports()) {
                    cityReport.append("\t  ")
                            .append(airport.getName())
                            .append(" (")
                            .append(airport.getCode())
                            .append(")")
                            .append("\n");
                }
            }
        }

        return cityReport.toString();
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
                        case "city-airport-report":
                            System.out.println(app.generateCityAirportReport());
                            break;
                        case "city-report":
                            System.out.println(app.generateCityReport());
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