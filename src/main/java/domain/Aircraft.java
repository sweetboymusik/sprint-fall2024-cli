package domain;

public class Aircraft {
    // instance variables
    private int id;
    private String type;
    private int passengerCapacity;
    private Airline airline;

    // constructors
    public Aircraft() {
    }

    public Aircraft(int id, String type, int passengerCapacity, Airline airline) {
        this.id = id;
        this.type = type;
        this.passengerCapacity = passengerCapacity;
        this.airline = airline;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
