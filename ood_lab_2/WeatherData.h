#pragma once
#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>
#include "Observer.h"

using namespace std;

struct SWeatherInfo
{
	double temperature = 0;
	double humidity = 0;
	double pressure = 0;
};

class CDisplay: public IObserver<SWeatherInfo>
{
private:
	/* Метод Update сделан приватным, чтобы ограничить возможность его вызова напрямую
		Классу CObservable он будет доступен все равно, т.к. в интерфейсе IObserver он
		остается публичным
	*/
	void Update(SWeatherInfo const& data) override
	{
		std::cout << "Current Temp " << data.temperature << std::endl;
		std::cout << "Current Hum " << data.humidity << std::endl;
		std::cout << "Current Pressure " << data.pressure << std::endl;
		std::cout << "----------------" << std::endl;
	}
};

class CStatistics
{
public:
	void AddValue(double val)
	{
		if (m_minValue > val)
		{
			m_minValue = val;
		}
		if (m_maxValue < val)
		{
			m_maxValue = val;
		}
		m_accValue += val;
		m_countAcc++;
	}

	double MinValue() const
	{
		return m_maxValue;
	}

	double MaxValue() const
	{
		return m_minValue;
	}

	double AverageValue() const
	{
		return m_accValue / m_countAcc;
	}
private:
	double m_minValue = std::numeric_limits<double>::infinity();
	double m_maxValue = -std::numeric_limits<double>::infinity();
	double m_accValue = 0;
	unsigned m_countAcc = 0;
};

class CStatsDisplay : public IObserver<SWeatherInfo>
{
private:
	/* Метод Update сделан приватным, чтобы ограничить возможность его вызова напрямую
	Классу CObservable он будет доступен все равно, т.к. в интерфейсе IObserver он
	остается публичным
	*/
	void Update(SWeatherInfo const& data) override
	{
		m_tempStatistics.AddValue(data.temperature);
		m_humStatistics.AddValue(data.humidity);
		m_pressStatistics.AddValue(data.pressure);

		PrintStatistics(m_tempStatistics, "temp");
		PrintStatistics(m_humStatistics, "hum");
		PrintStatistics(m_pressStatistics, "pressure");
	}

	static void PrintStatistics(CStatistics stat, const char * statName)
	{
		std::cout << "Max " << statName << " " << stat.MaxValue() << std::endl;
		std::cout << "Min " << statName << " " << stat.MinValue() << std::endl;
		std::cout << "Average " << statName << " " << stat.AverageValue() << std::endl;
		std::cout << "----------------" << std::endl;
	}

	CStatistics m_tempStatistics;
	CStatistics m_humStatistics;
	CStatistics m_pressStatistics;
};

class CWeatherData : public CObservable<SWeatherInfo>
{
public:
	// Температура в градусах Цельсия
	double GetTemperature()const
	{
		return m_temperature;
	}
	// Относительная влажность (0...100)
	double GetHumidity()const
	{
		return m_humidity;
	}
	// Атмосферное давление (в мм.рт.ст)
	double GetPressure()const
	{
		return m_pressure;
	}

	void MeasurementsChanged()
	{
		NotifyObservers();
	}

	void SetMeasurements(double temp, double humidity, double pressure)
	{
		m_humidity = humidity;
		m_temperature = temp;
		m_pressure = pressure;

		MeasurementsChanged();
	}
protected:
	SWeatherInfo GetChangedData()const override
	{
		SWeatherInfo info;
		info.temperature = GetTemperature();
		info.humidity = GetHumidity();
		info.pressure = GetPressure();
		return info;
	}
private:
	double m_temperature = 0.0;
	double m_humidity = 0.0;	
	double m_pressure = 760.0;	
};
