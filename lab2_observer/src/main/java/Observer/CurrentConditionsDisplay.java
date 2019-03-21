package Observer;

public class CurrentConditionsDisplay extends BaseDisplay<CurrentConditionsDisplay.ConditionStatistics>{
	protected class ConditionStatistics {
		public float temperature = 0.0f;
		public float humidity = 0.0f;
		public float pressure = 0.0f;
	}

	public void updateStationStatistics(ConditionStatistics conditionStatistics, WeatherDataSource weatherDataSource) {
		conditionStatistics.temperature = weatherDataSource.getTemperature();
		conditionStatistics.humidity = weatherDataSource.getHumidity();
		conditionStatistics.pressure = weatherDataSource.getPressure();
	}

	public void display() {
		System.out.println("Current conditions: " +  currentUpdatingStationStatistics.temperature
			+ "F degrees and " + currentUpdatingStationStatistics.humidity + "% humidity " + currentUpdatingStationStatistics.pressure + " pressure");
	}

	@Override
	protected ConditionStatistics initStationStatisticsInstance() {
		return new ConditionStatistics();
	}
}
