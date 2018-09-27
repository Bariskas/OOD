#include <algorithm>
#include <functional>
#include <cassert>
#include <iostream>
#include <memory>
#include <vector>
#include <string>

using namespace std;

using Behavior = function<void()>;

namespace FlyBehavior
{
	Behavior GetFlyWithWingsStrategy()
	{
		int flightCounter = 0;
		
		return [=]() mutable {
			cout << "I'm flying with wings!! for the " << (++flightCounter) << " time" << endl;
		};
	}

	Behavior GetFlyNoWayStrategy()
	{
		return []() {
			cout << "..." << endl;
		};
	}
}


namespace QuackBehavior
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
}


namespace DanceBehavior
{
	Behavior WaltzDanceBehavior = []()
	{
		cout << "It's waltz" << endl;
	};

	Behavior MinuetDanceBehavior = []()
	{
		cout << "It's minuet" << endl;
	};

	Behavior NoDanceBehavior = []()
	{
		cout << "I have no legs" << endl;
	};
}


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
		SetFlyBehavior(FlyBehavior::GetFlyWithWingsStrategy);
		SetQuackBehavior(QuackBehavior::QuackBehavior);
		SetDanceBehavior(DanceBehavior::WaltzDanceBehavior);
	}
	void Display() const override
	{
		cout << "I'm mallard duck" << endl;
	}
};

class RedheadDuck : public Duck
{
public:
	RedheadDuck()
	{
		SetFlyBehavior(FlyBehavior::GetFlyWithWingsStrategy);
		SetQuackBehavior(QuackBehavior::QuackBehavior);
		SetDanceBehavior(DanceBehavior::MinuetDanceBehavior);
	}
	void Display() const override
	{
		cout << "I'm redhead duck" << endl;
	}
};

class DecoyDuck : public Duck
{
public:
	DecoyDuck()
	{
		SetFlyBehavior(FlyBehavior::GetFlyNoWayStrategy);
		SetQuackBehavior(QuackBehavior::MuteQuackBehavior);
		SetDanceBehavior(DanceBehavior::NoDanceBehavior);
	}
	void Display() const override
	{
		cout << "I'm decoy duck" << endl;
	}
};

class RubberDuck : public Duck
{
public:
	RubberDuck()
	{
		SetFlyBehavior(FlyBehavior::GetFlyNoWayStrategy);
		SetQuackBehavior(QuackBehavior::SqueakBehavior);
		SetDanceBehavior(DanceBehavior::NoDanceBehavior);
	}
	void Display() const override
	{
		cout << "I'm rubber duck" << endl;
	}
};

class ModelDuck : public Duck
{
public:
	ModelDuck()
	{
		SetFlyBehavior(FlyBehavior::GetFlyNoWayStrategy);
		SetQuackBehavior(QuackBehavior::QuackBehavior);
		SetDanceBehavior(DanceBehavior::NoDanceBehavior);
	}
	void Display() const override
	{
		cout << "I'm model duck" << endl;
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

void main()
{
	MallardDuck mallarDuck;

	PlayWithDuck(mallarDuck);
	PlayWithDuck(mallarDuck);
	RedheadDuck redheadDuck;
	PlayWithDuck(redheadDuck);

	RubberDuck rubberDuck;
	PlayWithDuck(rubberDuck);

	DecoyDuck decoyDuck;
	PlayWithDuck(decoyDuck);

	ModelDuck modelDuck;
	PlayWithDuck(modelDuck);
	modelDuck.SetFlyBehavior(FlyBehavior::GetFlyWithWingsStrategy);
	PlayWithDuck(modelDuck);
}