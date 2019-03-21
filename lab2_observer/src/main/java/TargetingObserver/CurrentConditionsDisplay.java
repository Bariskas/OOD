package TargetingObserver;

import java.util.function.Supplier;

public class CurrentConditionsDisplay extends BaseDisplay<WeatherConditionData> {
	public CurrentConditionsDisplay(Supplier<WeatherConditionData> supplier) {
		super(supplier);
	}

	public void updateDisplayData(WeatherConditionData conditionStatistics, BaseObserverable weatherDataSource, WeatherData.WeatherDataEventType eventType) {
		conditionStatistics.temperature = weatherDataSource.getTemperature();
		conditionStatistics.humidity = weatherDataSource.getHumidity();
		conditionStatistics.pressure = weatherDataSource.getPressure();
	}

	public void display(WeatherConditionData stationStatistics) {
		System.out.println("Current conditions: " +  stationStatistics.temperature
			+ "F degrees and " + stationStatistics.humidity + "% humidity " + stationStatistics.pressure + " pressure");
	}
}
