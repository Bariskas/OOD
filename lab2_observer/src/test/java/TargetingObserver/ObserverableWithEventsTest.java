package TargetingObserver;

import org.junit.Test;

import java.util.ArrayList;
import java.util.function.Supplier;

import static org.junit.Assert.*;

public class ObserverableWithEventsTest {
    public class FloatContainer {
        public FloatContainer() {
            value = 0;
        }
        float value;
    }

    public class EventDisplay extends BaseDisplay<FloatContainer> {
        private ArrayList<WeatherData.WeatherDataEventType> triggeredEvents = new ArrayList<>();

        public EventDisplay(Supplier<FloatContainer> supplier) {
            super(supplier);
        }

        public  ArrayList<WeatherData.WeatherDataEventType> getHandledEvents() {
            return triggeredEvents;
        }

        public void display(FloatContainer statValue) {}

        protected void updateDisplayData(FloatContainer statisticTemperature, BaseObserverable weatherDataSource, WeatherData.WeatherDataEventType eventType) {
            switch (eventType) {
                case TEMPERATURE_UPDATED:
                    triggeredEvents.add(WeatherData.WeatherDataEventType.TEMPERATURE_UPDATED);
                    break;
                case PRESSURE_UPDATED:
                    triggeredEvents.add(WeatherData.WeatherDataEventType.PRESSURE_UPDATED);
                    break;
                case HUMIDITY_UPDATED:
                    triggeredEvents.add(WeatherData.WeatherDataEventType.HUMIDITY_UPDATED);
                    break;
                case WEATHER_UPDATED:
                default:
                    triggeredEvents.add(WeatherData.WeatherDataEventType.WEATHER_UPDATED);
                    break;
            }
        }
    }

    @Test
    public void testEventTriggering() {
        ArrayList<WeatherData.WeatherDataEventType> eventList = new ArrayList<>();
        EventDisplay observer = new EventDisplay(FloatContainer::new);
        WeatherData weatherDataChangingTemperature = new WeatherData();
        WeatherData weatherDataChangingPressure = new WeatherData();
        weatherDataChangingPressure.registerObserver(observer, WeatherData.WeatherDataEventType.PRESSURE_UPDATED, 0);
        weatherDataChangingTemperature.registerObserver(observer, WeatherData.WeatherDataEventType.TEMPERATURE_UPDATED, 0);

        weatherDataChangingPressure.setMeasurements(0, 0, 770);
        eventList.add(WeatherData.WeatherDataEventType.PRESSURE_UPDATED);
        assertArrayEquals(observer.getHandledEvents().toArray(), eventList.toArray());

        weatherDataChangingTemperature.setMeasurements(5, 0, 760);
        eventList.add(WeatherData.WeatherDataEventType.TEMPERATURE_UPDATED);
        assertArrayEquals(observer.getHandledEvents().toArray(), eventList.toArray());

        weatherDataChangingPressure.registerObserver(observer, WeatherData.WeatherDataEventType.TEMPERATURE_UPDATED, 0);
        weatherDataChangingPressure.setMeasurements(0, 0, 780);
        eventList.add(WeatherData.WeatherDataEventType.PRESSURE_UPDATED);
        assertArrayEquals(observer.getHandledEvents().toArray(), eventList.toArray());

        weatherDataChangingPressure.registerObserver(observer, WeatherData.WeatherDataEventType.WEATHER_UPDATED, 0);
        weatherDataChangingPressure.setMeasurements(0, 0, 790);
        eventList.add(WeatherData.WeatherDataEventType.PRESSURE_UPDATED);
        eventList.add(WeatherData.WeatherDataEventType.WEATHER_UPDATED);
        assertArrayEquals(observer.getHandledEvents().toArray(), eventList.toArray());

        weatherDataChangingPressure.removeObserver(observer, WeatherData.WeatherDataEventType.PRESSURE_UPDATED);
        weatherDataChangingPressure.setMeasurements(0, 0, 800);
        eventList.add(WeatherData.WeatherDataEventType.WEATHER_UPDATED);
        assertArrayEquals(observer.getHandledEvents().toArray(), eventList.toArray());
    }
}