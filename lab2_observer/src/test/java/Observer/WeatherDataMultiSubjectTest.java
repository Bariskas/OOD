package Observer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WeatherDataMultiSubjectTest {
    public class CheckMultipleDataSourceObserver extends BaseDisplay<Float> {
        private String lastStationKey;
        private float lastTemperature = 0.0f;

        public String getLastStationKey() {
            return lastStationKey;
        }
        public float getLastTemperature() {
            return lastTemperature;
        }

        @Override
        public void display() {}

        protected void updateStationStatistics(Float statisticTemperature, WeatherDataSource weatherDataSource) {
            lastStationKey = weatherDataSource.getKey();
            lastTemperature = weatherDataSource.getTemperature();
        }

        @Override
        protected Float initStationStatisticsInstance() {
            return 0.0f;
        }
    }

    @Test
    public void testPriority() {
        CheckMultipleDataSourceObserver observer = new CheckMultipleDataSourceObserver();
        WeatherData weatherDataIn = new WeatherData("in");
        WeatherData weatherDataOut = new WeatherData("out");
        weatherDataIn.registerObserver(observer, 5);
        weatherDataOut.registerObserver(observer, 2);
        weatherDataIn.setMeasurements(80, 65, 30.4f);
        assertEquals(observer.getLastStationKey(), "in");
        assertEquals(observer.getLastTemperature(), 80, .01);
        weatherDataOut.setMeasurements(90, 55, 10.4f);
        assertEquals(observer.getLastStationKey(), "out");
        assertEquals(observer.getLastTemperature(), 90, .01);
    }
}