package Observer;

public interface WeatherDataSource {
    float getTemperature();
    float getHumidity();
    float getPressure();
    String getKey();
}
