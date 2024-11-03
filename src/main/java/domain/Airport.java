package domain;

public class Airport {
    // instance variables
    private int id;
    private String name;
    private String airportCode;
    private City city;

    // constructors
    public Airport() {
    }

    public Airport(int id, String name, String airportCode, City city) {
        this.id = id;
        this.name = name;
        this.airportCode = airportCode;
        this.city = city;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
