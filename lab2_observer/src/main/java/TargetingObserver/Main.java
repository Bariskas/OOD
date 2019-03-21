package TargetingObserver;

public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(WeatherConditionData::new);
        StatisticsDisplay temperatureDisplay = new StatisticsDisplay(WeatherStatisticsAccumulator::new, WeatherDataSource::getTemperature, "Temperature");
        StatisticsDisplay humidityDisplay = new StatisticsDisplay(WeatherStatisticsAccumulator::new, WeatherDataSource::getHumidity, "Humidity");

        weatherData.setMeasurements(80, 65, 760);

        weatherData.registerObserver(currentDisplay, WeatherData.WeatherDataEventType.WEATHER_UPDATED, 0);
        weatherData.registerObserver(temperatureDisplay, WeatherData.WeatherDataEventType.TEMPERATURE_UPDATED, 0);
        weatherData.registerObserver(humidityDisplay, WeatherData.WeatherDataEventType.HUMIDITY_UPDATED, 0);

        weatherData.setMeasurements(80, 65, 760.2f);
        weatherData.setMeasurements(82, 65, 760.2f);
        weatherData.setMeasurements(82, 90, 760.4f);
    }
}
