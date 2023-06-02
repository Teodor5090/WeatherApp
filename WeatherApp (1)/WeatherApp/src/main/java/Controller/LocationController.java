package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Service.LocationService;
import TLA.WeatherApp.Model.Location;
import java.util.List;

@RestController
@RequestMapping("/locations")

public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable Long id) {
        return locationService.getLocationById(id);
    }

    @PostMapping
    public Location createLocation(@RequestBody Location location) {
        return locationService.saveLocation(location);
    }

    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PutMapping("/{id}")
    public Location updateLocation(@PathVariable Long id, @RequestBody Location location) {
        Location existingLocation = locationService.getLocationById(id);
        if (existingLocation != null) {
            location.setId(id);
            return locationService.saveLocation(location);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
    }
}
