package Observer;

public interface Observerable<T> {
	void registerObserver(T o, Integer priority);
	void registerObserver(T o);
	void removeObserver(T o);
	void notifyObservers();
}
