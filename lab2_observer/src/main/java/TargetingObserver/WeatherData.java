package TargetingObserver;

import java.util.ArrayList;
import java.util.TreeMap;

public class WeatherData extends BaseObserverable<WeatherData.WeatherDataEventType> {
    public enum WeatherDataEventType {
        TEMPERATURE_UPDATED,
        HUMIDITY_UPDATED,
        PRESSURE_UPDATED,
        WEATHER_UPDATED
    }

    private float temperature = 0;
    private float humidity = 0;
    private float pressure = 760;

    public WeatherData() {
        eventObserversMap = new TreeMap<>();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        Boolean temperatureUpdated = false;
        Boolean humidityUpdated = false;
        Boolean pressureUpdated = false;

        if (this.temperature != temperature) {
            this.temperature = temperature;
            temperatureUpdated = true;
        }
        if (this.humidity != humidity) {
            this.humidity = humidity;
            humidityUpdated = true;
        }
        if (this.pressure != pressure) {
            this.pressure = pressure;
            pressureUpdated = true;
        }

        if (temperatureUpdated) {
            notifyObservers(WeatherDataEventType.TEMPERATURE_UPDATED);
        }
        if (humidityUpdated) {
            notifyObservers(WeatherDataEventType.HUMIDITY_UPDATED);
        }
        if (pressureUpdated) {
            notifyObservers(WeatherDataEventType.PRESSURE_UPDATED);
        }
        if (temperatureUpdated || humidityUpdated || pressureUpdated) {
            notifyObservers(WeatherDataEventType.WEATHER_UPDATED);
        }
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
