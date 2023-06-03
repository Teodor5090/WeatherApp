package WeatherAppCOMPINT.WeatherAppCOMPINT;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByName(String name);

    List<Location> findByLatitudeAndLongitude(double latitude, double longitude);
}
