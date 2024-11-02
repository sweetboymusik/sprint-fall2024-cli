package domain;

public class City {
    // instance variables
    private int id;
    private String name;
    private String state;
    private int population;

    // constructors
    public City() {
    }

    public City(int id, String name, String state, int population) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.population = population;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
