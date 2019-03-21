package Observer;

import java.util.HashMap;
import java.util.Map;

abstract public class BaseDisplay<T> implements Observer<WeatherDataSource>, DisplayElement {
	protected T currentUpdatingStationStatistics = null;
	protected Map<String, T> displayData = new HashMap<>();

	public void update(WeatherDataSource weatherData) {
		initStationStatistics(weatherData);

		T stationStatistics = displayData.get(weatherData.getKey());
		updateStationStatistics(stationStatistics, weatherData);

		currentUpdatingStationStatistics = stationStatistics;
		display();
		currentUpdatingStationStatistics = null;
	}

	protected void initStationStatistics(WeatherDataSource weatherDataSource) {
		String weatherDataSoourceKey = weatherDataSource.getKey();
		if (!displayData.containsKey(weatherDataSoourceKey)) {
			displayData.put(weatherDataSoourceKey, initStationStatisticsInstance());
		}
	}

	abstract protected void updateStationStatistics(T stationStatistics, WeatherDataSource weatherData);
	abstract protected T initStationStatisticsInstance();
}
