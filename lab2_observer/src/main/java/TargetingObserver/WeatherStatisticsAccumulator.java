package TargetingObserver;

public class WeatherStatisticsAccumulator {
    public float minValue = Float.MIN_VALUE;
    public float maxValue = Float.MAX_VALUE;
    public float tempSum = 0.0f;
    public int numReadings = 0;

    public void addMeasurement(float newValue) {
        tempSum += newValue;
        numReadings++;

        if (newValue > maxValue) {
            maxValue = newValue;
        }

        if (newValue < minValue) {
            minValue = newValue;
        }
    }
}
