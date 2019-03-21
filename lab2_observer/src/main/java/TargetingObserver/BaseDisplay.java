package TargetingObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

abstract public class BaseDisplay<T> implements Observer<BaseObserverable, WeatherData.WeatherDataEventType>, DisplayElement<T> {
	protected Map<BaseObserverable, T> displayData = new HashMap<>();
	private Supplier<T> supplier;

	public BaseDisplay(Supplier<T> supplier) {
		this.supplier = supplier;
	}

	public void update(BaseObserverable weatherData, WeatherData.WeatherDataEventType eventType) {
		initDisplayDataForSubject(weatherData);

		T displayData = this.displayData.get(weatherData);
		updateDisplayData(displayData, weatherData, eventType);

		display(displayData);
	}

	protected void initDisplayDataForSubject(BaseObserverable weatherDataSource) {
		if (!displayData.containsKey(weatherDataSource)) {
			displayData.put(weatherDataSource, supplier.get());
		}
	}

	abstract public void display(T displayData);
	abstract protected void updateDisplayData(T displayData, BaseObserverable weatherData, WeatherData.WeatherDataEventType eventType);
}
