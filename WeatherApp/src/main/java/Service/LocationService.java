package Service;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import TLA.WeatherApp.Repository.LocationRepository;
import TLA.WeatherApp.Model.Location;
import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
    
    public Location fetchWeatherData(String locationName) {
        // Construct the URL for the weather API endpoint using the locationName parameter
        String apiUrl = "https://api.weatherapi.com/v1/forecast.json?key=5dfb38b4accd47b7bc2182137230106&q=" + locationName;

        // Create a new instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Send an HTTP GET request to the weather API endpoint
        ResponseEntity<Location> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null, Location.class);

        // Extract the weather forecast data from the response body
        Location weatherData = response.getBody();

        // You can process and manipulate the weatherData as per your application's requirements

        return weatherData;
    }
}

