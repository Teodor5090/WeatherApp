package TLA.WeatherApp.Repository;
import TLA.WeatherApp.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    // Additional custom query methods can be defined here if needed
}