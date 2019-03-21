package TargetingObserver;

import java.util.ArrayList;
import java.util.TreeMap;

abstract public class BaseObserverable<D> implements Observerable<D>, WeatherDataSource{
	protected TreeMap<D, TreeMap<Integer, ArrayList<Observer>>> eventObserversMap;

	public void registerObserver(Observer newObserver, D eventType, int priority) {
		if (!eventObserversMap.containsKey(eventType)) {
			eventObserversMap.put(eventType, new TreeMap<>());
		}

		TreeMap<Integer, ArrayList<Observer>> priorityObserverMap = eventObserversMap.get(eventType);
		if (priorityObserverMap.containsKey(priority)) {
			ArrayList<Observer> observers = priorityObserverMap.get(priority);
			observers.add(newObserver);
		} else {
			ArrayList<Observer> observers = new ArrayList<>();
			observers.add(newObserver);
			priorityObserverMap.put(priority, observers);
		}
	}

	public void removeObserver(Observer observer, D eventType) {
		TreeMap<Integer, ArrayList<Observer>> priorityObserverMap = eventObserversMap.get(eventType);
		for (ArrayList<Observer> observers : priorityObserverMap.values()) {
			if (observers.contains(observer)) {
				observers.remove(observer);
				break;
			}
		}
	}

	public void notifyObservers(D eventType) {
		TreeMap<Integer, ArrayList<Observer>> priorityObserverMap = eventObserversMap.get(eventType);
		if (priorityObserverMap != null) {
			for (ArrayList<Observer> observers : priorityObserverMap.values()) {
				for (Observer observer : observers) {
					observer.update(this, eventType);
				}
			}
		}
	}
}
