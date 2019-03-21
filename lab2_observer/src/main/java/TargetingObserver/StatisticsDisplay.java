package TargetingObserver;

import java.util.function.Function;
import java.util.function.Supplier;

public class StatisticsDisplay extends BaseDisplay<WeatherStatisticsAccumulator> {
	private Function<WeatherDataSource, Float> getValueForDisplayStrategy;
	private String conditionName;

	public StatisticsDisplay(Supplier<WeatherStatisticsAccumulator> supplier, Function<WeatherDataSource, Float> getValueStrategy, String conditionName) {
		super(supplier);
		this.getValueForDisplayStrategy = getValueStrategy;
		this.conditionName = conditionName;
	}

	public void display(WeatherStatisticsAccumulator displayData) {
		System.out.println("Avg/Max/Min " + conditionName + " = " + (displayData.tempSum / displayData.numReadings)
			+ "/" + displayData.maxValue + "/" + displayData.minValue);
	}

	protected void updateDisplayData(WeatherStatisticsAccumulator displayData, BaseObserverable weatherData, WeatherData.WeatherDataEventType eventType) {
		float incomingValue = getValueForDisplayStrategy.apply(weatherData);
		displayData.addMeasurement(incomingValue);
	}
}
