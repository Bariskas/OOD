#include <algorithm>
#include <functional>
#include <cassert>
#include <iostream>
#include <memory>
#include <vector>
#include <string>

using namespace std;

using Behavior = function<void()>;

struct FlyBehavior_t
{
	Behavior getFlyWithWingsStrategy()
	{
		int flightCounter = 0;
		
		return [=]() mutable {
			cout << "I'm flying with wings!! for the " << (++flightCounter) << " time" << endl;
		};
	}

	Behavior getFlyNoWayStrategy()
	{
		return []() {
			cout << "..." << endl;
		};
	}
} FlyBehavior;


struct QuackBehavior_t
{
	Behavior QuackBehavior = []()
	{
		cout << "Quack Quack!!!" << endl;
	};

	Behavior SqueakBehavior = []()
	{
		cout << "SQUEEK!!!" << endl;
	};

	Behavior MuteQuackBehavior = []()
	{
		cout << "..." << endl;
	};
} QuackBehavior;


struct DanceBehavior_t
{
	Behavior WaltzDanceBehavior = []()
	{
		cout << "It's waltz" << endl;
	};

	Behavior MinuetDanceBehavior = []()
	{
		cout << "It's minuet" << endl;
	};
} DanceBehavior;


class Duck
{
public:
	Duck(){}

	Duck(Behavior flyBehavior,
		Behavior quackBehavior,
		Behavior danceBehavior)
		: m_quackBehavior(quackBehavior),
		m_flyBehavior(flyBehavior),
		m_danceBehavior(danceBehavior)
	{
		assert(m_quackBehavior);
		assert(m_flyBehavior);
		assert(m_danceBehavior);
	}
	void Quack() const
	{
		m_quackBehavior();
	}
	void Swim()
	{
		cout << "I'm swimming" << endl;
	}
	void Fly()
	{
		m_flyBehavior();
	}
	virtual void Dance()
	{
		m_danceBehavior();
	}
	void SetFlyBehavior(Behavior flyBehavior)
	{
		m_flyBehavior = move(flyBehavior);
	}
	void SetQuackBehavior(Behavior quackBehavior)
	{
		m_quackBehavior = quackBehavior;
	}
	void SetDanceBehavior(Behavior danceBehavior)
	{
		m_danceBehavior = danceBehavior;
	}
	virtual void Display() const = 0;
	virtual ~Duck() = default;

private:
	Behavior m_flyBehavior;
	Behavior m_quackBehavior;
	Behavior m_danceBehavior;
};

class MallardDuck : public Duck
{
public:
	MallardDuck()
	{
		SetFlyBehavior(FlyBehavior.getFlyNoWayStrategy());
		SetQuackBehavior(QuackBehavior.QuackBehavior);
		SetDanceBehavior(DanceBehavior.WaltzDanceBehavior);
	}
	void Display() const override
	{
		cout << "I'm mallard duck" << endl;
	}
};

void DrawDuck(Duck const& duck)
{
	duck.Display();
}

void PlayWithDuck(Duck& duck)
{
	DrawDuck(duck);
	duck.Quack();
	duck.Fly();
	duck.Dance();
	cout << endl;
}

int main()
{
	MallardDuck mallarDuck;

	PlayWithDuck(mallarDuck);
	PlayWithDuck(mallarDuck);

	return 0;
}