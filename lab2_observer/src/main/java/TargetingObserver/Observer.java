package TargetingObserver;

public interface Observer<T extends Observerable, D> {
	void update(T subject, D eventType);
}
