package domain;

import java.time.LocalDate;
import java.util.List;

public class Flight {
    // instance variables
    private int id;
    private LocalDate departure;
    private LocalDate arrival;
    private Airport origin;
    private Airport destination;
    private Aircraft aircraft;
    private int numberOfPassengers;
    private List<Passenger> passengerList;

    // constructors
    public Flight() {
    }

    public Flight(int id, LocalDate departure, LocalDate arrival, Airport origin, Airport destination, Aircraft aircraft, int numberOfPassengers, List<Passenger> passengerList) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
        this.origin = origin;
        this.destination = destination;
        this.aircraft = aircraft;
        this.numberOfPassengers = numberOfPassengers;
        this.passengerList = passengerList;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }
}