package Observer;

import java.util.ArrayList;
import java.util.TreeMap;

public class WeatherData implements Observerable<Observer>, WeatherDataSource {
    private ArrayList<Observer> allObservers;
    private TreeMap<Integer, ArrayList<Observer>> observersMap;
    private float temperature = 0;
    private float humidity = 0;
    private float pressure = 760;
    private String key = "";

    public WeatherData() {
        allObservers = new ArrayList<>();
        observersMap = new TreeMap<>();
    }

    public WeatherData(String key) {
        allObservers = new ArrayList<>();
        observersMap = new TreeMap<>();
        this.key = key;
    }

    public void registerObserver(Observer newObserver, Integer priority) {
        if (allObservers.contains(newObserver)) {
            return;
        } else {
            allObservers.add(newObserver);
        }
        addObserverToObserverMap(newObserver, priority);
    }

    public void registerObserver(Observer newObserver) {
        if (allObservers.contains(newObserver)) {
            return;
        } else {
            allObservers.add(newObserver);
        }
        addObserverToObserverMap(newObserver, 0);
    }

    public void removeObserver(Observer o) {
        int i = allObservers.indexOf(o);
        if (i >= 0) {
            allObservers.remove(i);
            for (ArrayList<Observer> observers : observersMap.values()) {
                if (observers.contains(o)) {
                    observers.remove(o);
                    break;
                }
            }
        }
    }

    public void notifyObservers() {
        for (ArrayList<Observer> observers : observersMap.values()) {
            for (Observer observer : observers) {
                observer.update(this);
            }
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public String getKey() {
        return key;
    }

    private void addObserverToObserverMap(Observer newObserver, Integer priority) {
        if (observersMap.containsKey(priority)) {
            ArrayList<Observer> observers = observersMap.get(priority);
            observers.add(newObserver);
        } else {
            ArrayList<Observer> observers = new ArrayList<>();
            observers.add(newObserver);
            observersMap.put(priority, observers);
        }
    }
}
