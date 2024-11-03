package domain;

import java.util.List;

public class Airline {
    // instance variables
    private int id;
    private String name;
    private String country;
    private List<Aircraft> aircraftList;

    // constructor
    public Airline() {
    }

    public Airline(int id, String name, String country, List<Aircraft> aircraftList) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.aircraftList = aircraftList;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Aircraft> getAircraftList() {
        return aircraftList;
    }

    public void setAircraftList(List<Aircraft> aircraftList) {
        this.aircraftList = aircraftList;
    }
}
