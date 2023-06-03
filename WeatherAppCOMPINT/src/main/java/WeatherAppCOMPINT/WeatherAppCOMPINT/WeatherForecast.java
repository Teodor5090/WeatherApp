package WeatherAppCOMPINT.WeatherAppCOMPINT;

import java.time.LocalDate;

public class WeatherForecast {
    private String location;
    private LocalDate date;
    private String description;
    private double temperature;

    // Constructors, getters, and setters

    // Default constructor
    public WeatherForecast() {
    }

    // Constructor with parameters
    public WeatherForecast(String location, LocalDate date, String description, double temperature) {
        this.location = location;
        this.date = date;
        this.description = description;
        this.temperature = temperature;
    }

    // Getters and setters

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
