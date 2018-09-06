#include <algorithm>
#include <functional>
#include <cassert>
#include <iostream>
#include <memory>
#include <vector>
#include <string>

using namespace std;

using Behavior = function<void()>;

void flyWithWingsStrategy()
{
	int flightCounter = 0;
	function<void()> fn = [=]() mutable {
		cout << "I'm flying with wings!! for the " << (++flightCounter) << " time" << endl;
	};
}

void FlyNoWay()
{
	cout << "..." << endl;
}

void QuackBehavior()
{
	cout << "Quack Quack!!!" << endl;
}

void SqueakBehavior()
{
	cout << "SQUEEK!!!" << endl;
}

void MuteQuackBehavior()
{
	cout << "..." << endl;
}

void WaltzDanceBehavior()
{
	cout << "It's waltz" << endl;
}

void MinuetDanceBehavior()
{
	cout << "It's minuet" << endl;
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
		SetFlyBehavior(flyWithWingsStrategy);
		SetQuackBehavior(QuackBehavior);
		SetDanceBehavior(WaltzDanceBehavior);
	}
	/*
	MallardDuck()
		: Duck(make_unique<Behavior>(), make_unique<Behavior>(),
			make_unique<Behavior>())
	{
	}
	*/
	void Display() const override
	{
		cout << "I'm mallard duck" << endl;
	}
};
/*
class RedheadDuck : public Duck
{
public:
	RedheadDuck()
		: Duck(make_unique<Behavior>(), make_unique<Behavior>(),
			make_unique<Behavior>())
	{
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
		: Duck(make_unique<Behavior>(), make_unique<Behavior>(),
			make_unique<Behavior>())
	{
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
		: Duck(make_unique<Behavior>(), make_unique<Behavior>(),
			make_unique<Behavior>())
	{
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
		: Duck(make_unique<Behavior>(), make_unique<Behavior>(),
			make_unique<Behavior>())
	{
	}
	void Display() const override
	{
		cout << "I'm model duck" << endl;
	}
};*/

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
	/*
	RedheadDuck redheadDuck;
	PlayWithDuck(redheadDuck);

	RubberDuck rubberDuck;
	PlayWithDuck(rubberDuck);

	DecoyDuck decoyDuck;
	PlayWithDuck(decoyDuck);

	ModelDuck modelDuck;
	PlayWithDuck(modelDuck);
	modelDuck.SetFlyBehavior(make_unique<FlyWithWings>());
	PlayWithDuck(modelDuck);*/
}