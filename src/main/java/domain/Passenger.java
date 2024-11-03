package domain;

import java.util.List;

public class Passenger {
    // instance variables
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private City city;
    private List<Flight> flights;

    // constructors
    public Passenger() {
    }

    public Passenger(String firstName, int id, String lastName, String phoneNumber, String email, City city, List<Flight> flights) {
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
        this.flights = flights;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
