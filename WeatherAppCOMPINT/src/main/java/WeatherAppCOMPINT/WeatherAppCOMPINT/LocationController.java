package WeatherAppCOMPINT.WeatherAppCOMPINT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    private final LocationRepository locationRepository;
    private final WeatherApiService weatherApiService;

    @Autowired
    public LocationController(LocationRepository locationRepository, WeatherApiService weatherApiService) {
        this.locationRepository = locationRepository;
        this.weatherApiService = weatherApiService;
    }

    @GetMapping("/{location}")
    public ResponseEntity<WeatherForecast> getWeatherForecast(@PathVariable String location) {
        // Call the external API service to fetch the weather forecast data
        // Pass the API key and location to retrieve the forecast
        WeatherForecast forecast = weatherApiService.getWeatherForecast("21ddb9fab3e9790f599a944edda2a82d", location);

        if (forecast != null) {
            return ResponseEntity.ok(forecast);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Location> addLocation(@RequestBody Location location) {
        // Save the new location to the database using the repository
        Location savedLocation = locationRepository.save(location);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLocation);
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        // Retrieve all locations from the database using the repository
        List<Location> locations = locationRepository.findAll();
        return ResponseEntity.ok(locations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location updatedLocation) {
        // Check if the location with the given ID exists in the database
        if (locationRepository.existsById(id)) {
            updatedLocation.setId(id);
            Location savedLocation = locationRepository.save(updatedLocation);
            return ResponseEntity.ok(savedLocation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        // Check if the location with the given ID exists in the database
        if (locationRepository.existsById(id)) {
            locationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
