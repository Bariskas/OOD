#pragma once

#include "IBeverage.h"

// Базовая реализация напитка, предоставляющая его описание
class CBeverage : public IBeverage
{
public:
	CBeverage(const std::string & description)
		:m_description(description)
	{}

	std::string GetDescription()const override final
	{
		return m_description;
	}
private:
	std::string m_description;
};

// Кофе
class CCoffee : public CBeverage
{
public:
	CCoffee(const std::string& description = "Coffee")
		:CBeverage(description) 
	{}

	double GetCost() const override 
	{
		return 60; 
	}
};

// Капуччино
class CCapuccino : public CCoffee
{
public:
	CCapuccino() 
		:CCoffee("Capuccino") 
	{}

	double GetCost() const override 
	{
		return 80; 
	}
};

// Двойное капуччино
class CDoubleCapuccino : public CCoffee
{
public:
	CDoubleCapuccino()
		:CCoffee("Double Capuccino")
	{}

	double GetCost() const override
	{
		return 120;
	}
};

// Латте
class CLatte : public CCoffee
{
public:
	CLatte() 
		:CCoffee("Latte") 
	{}

	double GetCost() const override 
	{
		return 90; 
	}
};

// Двойное латте
class CDoubleLatte : public CCoffee
{
public:
	CDoubleLatte()
		:CCoffee("Double latte")
	{}

	double GetCost() const override
	{
		return 130;
	}
};

// Чай
class CTea : public CBeverage
{
public:
	CTea(const std::string& description = "Tea")
		:CBeverage(description)
	{}

	double GetCost() const override 
	{
		return 30; 
	}
};

class CGreenTea : public CTea
{
public:
	CGreenTea()
		:CTea("Green Tea")
	{}
};

class CBlackTea : public CTea
{
public:
	CBlackTea()
		:CTea("Black Tea")
	{}
};

class CWhiteTea : public CTea
{
public:
	CWhiteTea()
		:CTea("White Tea")
	{}
};

class CYellowTea : public CTea
{
public:
	CYellowTea()
		:CTea("Yellow Tea")
	{}
};

// Молочный коктейль
class CMilkshake : public CBeverage
{
public:
	CMilkshake(const std::string& description = "Milkshake")
		:CBeverage(description)
	{}

	double GetCost() const override 
	{ 
		return 60; 
	}
};

class CSmallMilkshake : public CMilkshake
{
public:
	CSmallMilkshake()
		: CMilkshake("Small milkshake")
	{}

	double GetCost() const override
	{
		return 50;
	}
};

class CBigMilkshake : public CMilkshake
{
public:
	CBigMilkshake()
		: CMilkshake("Big milkshake")
	{}

	double GetCost() const override
	{
		return 80;
	}
};