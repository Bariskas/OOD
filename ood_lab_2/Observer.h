#pragma once

#include <set>
#include <functional>

/*
Шаблонный интерфейс IObserver. Его должен реализовывать класс, 
желающий получать уведомления от соответствующего IObservable
Параметром шаблона является тип аргумента,
передаваемого Наблюдателю в метод Update
*/
template <typename T>
class IObserver
{
public:
	virtual void Update(T const& data) = 0;
	virtual ~IObserver() = default;

	void MarkAsDeleted()
	{
		m_isDeleted = true;
	}

	bool GetIsDeleted() const
	{
		return m_isDeleted;
	}
private:
	bool m_isDeleted = false;
};

/*
Шаблонный интерфейс IObservable. Позволяет подписаться и отписаться на оповещения, а также
инициировать рассылку уведомлений зарегистрированным наблюдателям.
*/
template <typename T>
class IObservable
{
public:
	virtual ~IObservable() = default;
	virtual void RegisterObserver(IObserver<T> & observer) = 0;
	virtual void NotifyObservers() = 0;
	virtual void RemoveObserver(IObserver<T> & observer) = 0;
};

// Реализация интерфейса IObservable
template <class T>
class CObservable : public IObservable<T>
{
public:
	typedef IObserver<T> ObserverType;

	void RegisterObserver(ObserverType & observer) override
	{
		m_observers.insert(&observer);
	}

	void NotifyObservers() override
	{
		T data = GetChangedData();
		auto clone(m_observers);
		for (auto & observer : clone)
		{
			observer->Update(data);
		}

		EraseDeletedObservers();
	}

	void RemoveObserver(ObserverType & observer) override
	{
		observer.MarkAsDeleted();
		//m_observers.erase(&observer);
	}

	void EraseDeletedObservers()
	{
		std::vector<ObserverType&> observersToDelete = {};
		for (auto & observer : m_observers)
		{
			if (observer.GetIsDeleted())
			{
				observersToDelete.push_back(&observer);
			}
		}
		for (auto & observer : observersToDelete)
		{
			m_observers.erase(&observer);
		}
	}

protected:
	// Классы-наследники должны перегрузить данный метод, 
	// в котором возвращать информацию об изменениях в объекте
	virtual T GetChangedData()const = 0;

private:
	std::set<ObserverType *> m_observers;
};
