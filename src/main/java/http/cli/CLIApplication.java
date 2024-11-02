package http.cli;

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

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
            System.out.println();
        }

        CLIApplication app = new CLIApplication();

        String serverURL = args[0];


        if (serverURL != null && !serverURL.isEmpty()) {
            RESTClient restClient = new RESTClient();
            restClient.setServerURL(serverURL);
            app.setRestClient(restClient);

            if (serverURL.contains("/city/all"))
                System.out.println(app.generateCityReport());
        }

    }
}