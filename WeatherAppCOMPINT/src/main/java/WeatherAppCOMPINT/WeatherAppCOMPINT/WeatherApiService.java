package WeatherAppCOMPINT.WeatherAppCOMPINT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherApiService {
    private static final String API_URL = "https://api.weatherapi.com/v1/forecast?key={21ddb9fab3e9790f599a944edda2a82d}&q={location}&days=7";

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherForecast getWeatherForecast(String apiKey, String location) {
        String url = API_URL.replace("{apiKey}", apiKey).replace("{location}", location);
        WeatherForecast forecast = restTemplate.getForObject(url, WeatherForecast.class);
        return forecast;
    }
}
