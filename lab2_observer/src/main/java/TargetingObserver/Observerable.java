package TargetingObserver;

public interface Observerable<D> {
	void registerObserver(Observer o, D e, int priority);
	void removeObserver(Observer o, D e);
	void notifyObservers(D e);
}
