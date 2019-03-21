package Observer;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeatherDataPriorityTest {
    public class UpdateCounter {
        public Integer updateCount = 0;
    }

    public class UpdateCountCheckObserver implements Observer<WeatherDataSource> {
        private Integer updateOrder = 0;
        private UpdateCounter updateCounter;

        UpdateCountCheckObserver(UpdateCounter counter) {
            updateCounter = counter;
        }

        public void update(WeatherDataSource weatherDataSource) {
            updateCounter.updateCount++;
            updateOrder = updateCounter.updateCount;
        }

        public Integer getOrder() {
            return updateOrder;
        }
    }

    @Test
    public void testPriority() {
        UpdateCounter updateCounter = new UpdateCounter();
        UpdateCountCheckObserver observer1 = new UpdateCountCheckObserver(updateCounter);
        UpdateCountCheckObserver observer2 = new UpdateCountCheckObserver(updateCounter);
        WeatherData weatherData = new WeatherData();
        weatherData.registerObserver(observer1, 5);
        weatherData.registerObserver(observer2, 2);
        weatherData.setMeasurements(80, 65, 30.4f);
        assertEquals(observer1.getOrder().intValue(), 2);
        assertEquals(observer2.getOrder().intValue(), 1);
        weatherData.setMeasurements(90, 55, 10.4f);
        assertEquals(observer1.getOrder().intValue(), 4);
        assertEquals(observer2.getOrder().intValue(), 3);
    }
}