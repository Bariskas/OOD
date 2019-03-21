package Observer;

public class WeatherStatisticsCalculator {
    public static void updateStatistics(StatisticsDisplay.StationStatistics stationStatistics, float newValue) {
        stationStatistics.tempSum += newValue;
        stationStatistics.numReadings++;

        if (newValue > stationStatistics.maxValue) {
            stationStatistics.maxValue = newValue;
        }

        if (newValue < stationStatistics.minValue) {
            stationStatistics.minValue = newValue;
        }
    }
}
