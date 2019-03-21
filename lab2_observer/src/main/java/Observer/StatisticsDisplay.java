package Observer;

import java.util.function.Function;

public class StatisticsDisplay extends BaseDisplay<StatisticsDisplay.StationStatistics> {
	protected class StationStatistics {
		public float minValue = Float.MIN_VALUE;
		public float maxValue = Float.MAX_VALUE;
		public float tempSum = 0.0f;
		public int numReadings = 0;
	}

	private Function<WeatherDataSource, Float> getValueForDisplayStrategy;
	private String conditionName;

	public StatisticsDisplay(Function<WeatherDataSource, Float> getValueStrategy, String conditionName) {
		this.getValueForDisplayStrategy = getValueStrategy;
		this.conditionName = conditionName;
	}

	public void display() {
		System.out.println("Avg/Max/Min " + conditionName + " = " + (currentUpdatingStationStatistics.tempSum / currentUpdatingStationStatistics.numReadings)
			+ "/" + currentUpdatingStationStatistics.maxValue + "/" + currentUpdatingStationStatistics.minValue);
	}

	protected void updateStationStatistics(StationStatistics stationStatistics, WeatherDataSource weatherData) {
		float incomingValue = getValueForDisplayStrategy.apply(weatherData);
		WeatherStatisticsCalculator.updateStatistics(stationStatistics, incomingValue);
	}

	@Override
	protected StationStatistics initStationStatisticsInstance() {
		return new StationStatistics();
	}
}
