package Observer;

public class Main {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay temperatureDisplay = new StatisticsDisplay(WeatherDataSource::getTemperature, "Temperature");
        StatisticsDisplay humidityDisplay = new StatisticsDisplay(WeatherDataSource::getHumidity, "Humidity");

        weatherData.registerObserver(currentDisplay);
        weatherData.registerObserver(temperatureDisplay);
        weatherData.registerObserver(humidityDisplay);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
