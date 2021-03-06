﻿#include "Beverages.h"
#include "Condiments.h"

#include <iostream>
#include <string>
#include <functional>

using namespace std;


/*
Функциональный объект, создающий лимонную добавку
*/
struct MakeLemon
{
	MakeLemon(unsigned quantity)
		:m_quantity(quantity)
	{}

	auto operator()(IBeveragePtr && beverage)const
	{
		return make_unique<CLemon>(move(beverage), m_quantity); 
	}
private:
	unsigned m_quantity;
};

/*
Функция, возвращающая функцию, создающую коричную добавку
*/
function<IBeveragePtr(IBeveragePtr &&)> MakeCinnamon()
{
	return [] (IBeveragePtr && b) {
		return make_unique<CCinnamon>(move(b));
	};
}

/*
Возвращает функцию, декорирующую напиток определенной добавкой

Параметры шаблона: 
	Condiment - класс добавки, конструктор которого в качестве первого аргумента
				принимает IBeveragePtr&& оборачиваемого напитка
	Args - список типов прочих параметров конструктора (возможно, пустой)
*/
template <typename Condiment, typename... Args>
auto MakeCondiment(const Args&...args)
{
	// Возвращаем функцию, декорирующую напиток, переданный ей в качестве аргумента
	// Дополнительные аргументы декоратора, захваченные лямбда-функцией, передаются
	// конструктору декоратора через make_unique
	return [=](auto && b) {
		// Функции make_unique передаем b вместе со списком аргументов внешней функции
		return make_unique<Condiment>(forward<decltype(b)>(b), args...);
	};
}

/*
Перегруженная версия оператора <<, которая предоставляет нам синтаксический сахар
для декорирования компонента

Позволяет создать цепочку оборачивающих напиток декораторов следующим образом:
auto beverage = make_unique<CConcreteBeverage>(a, b, c)
					<< MakeCondimentA(d, e, f)
					<< MakeCondimentB(g, h);

Функциональные объекты MakeCondiment* запоминают аргументы, необходимые для создания
дополнения, и возвращают фабричную функцию, принимающую оборачиваемый напиток, которая
при своем вызове создаст нужный объект Condiment, передав ему запомненные аргументы.
Использование:
	auto beverage = 
		make_unique<CConcreteBeverage>(a, b, c)
		<< MakeCondimentA(d, e, f)
		<< MakeCondimentB(g, h);
или даже так:
	auto beverage = 
		make_unique<CConcreteBeverage>
		<< MakeCondiment<CondimentA>(d, e, f)
		<< MakeCondiment<CondimentB>(g, h);
В последнем случае нет необходимости писать вручную реализации MakeCondimentA и MakeCondimentB, т.к.
необходимую реализацию сгенерирует компилятор

Классический способ оборачивания выглядел бы так:
	auto baseBeverage = make_unique<CConcretedBeverage>(a, b, c);
	auto wrappedWithCondimentA = make_unique<CCondimentA>(move(baseBeverage), d, e, f);
	auto beverage = make_unique<CCondimentB>(move(wrappedWithCondimentA), g, h);
либо так:
	auto beverage = make_unique<CCondimentB>(
						make_unique<CCondimentA>(
							make_unique<CConcreteBeverage>(a, b, c), // Напиток
							d, e, f	// доп. параметы CondimentA
						),
						g, h		// доп. параметры CondimentB
					);

unique_ptr<CLemon> operator << (IBeveragePtr && lhs, const MakeLemon & factory)
{
	return factory(move(lhs));
}
unique_ptr<CCinnamon> operator << (IBeveragePtr && lhs, const MakeCinnamon & factory)
{
	return factory(move(lhs));
}
*/
template <typename Component, typename Decorator>
auto operator << (Component && component, const Decorator & decorate)
{
	return decorate(forward<Component>(component));
}

void DialogWithUser()
{
	cout << "Type 1 for coffee, 2 for tea, 3 for milkshake\n";
	int beverageChoice;
	cin >> beverageChoice;

	unique_ptr<IBeverage> beverage;

	switch (beverageChoice)
	{
	case 1:
		cout << "1 - simple coffee, 2 - latte, 3 - double latte, 4 - capuccino, 5 - double latte\n";
		int coffeChoice;
		cin >> coffeChoice;
		switch (coffeChoice)
		{
		case 1:
			beverage = make_unique<CCoffee>();
			break;
		case 2:
			beverage = make_unique<CLatte>();
			break;
		case 3:
			beverage = make_unique<CDoubleLatte>();
			break;
		case 4:
			beverage = make_unique<CCapuccino>();
			break;
		case 5:
			beverage = make_unique<CDoubleCapuccino>();
			break;
		default:
			return;
		}
		break;
	case 2:
		cout << "1 - simple tea, 2 - green tea, 3 - black tea, 4 - white tea, 5 - yellow tea\n";
		int teaChoice;
		cin >> teaChoice;
		switch (teaChoice)
		{
		case 1:
			beverage = make_unique<CTea>();
			break;
		case 2:
			beverage = make_unique<CGreenTea>();
			break;
		case 3:
			beverage = make_unique<CBlackTea>();
			break;
		case 4:
			beverage = make_unique<CWhiteTea>();
			break;
		case 5:
			beverage = make_unique<CYellowTea>();
			break;
		default:
			return;
		}
		break;
	case 3:
		cout << "1 - small milkshake, 2 - milkshake, 3 - big milkshake\n";
		int milkshakeChoice;
		cin >> milkshakeChoice;
		switch (milkshakeChoice)
		{
		case 1:
			beverage = make_unique<CSmallMilkshake>();
			break;
		case 2:
			beverage = make_unique<CMilkshake>();
			break;
		case 3:
			beverage = make_unique<CBigMilkshake>();
			break;
		default:
			return;
		}
		break;
	default:
		return;
	}

	int condimentChoice;
	for (bool needToLoop = true; needToLoop;)
	{
		cout << "1 - Lemon, 2 - Cinnamon, 3 - Cream, 4 - Chocolate, 5 - Liquor, 0 - Checkout" << endl;
		cin >> condimentChoice;

		switch (condimentChoice)
		{
		case 1:
			beverage = move(beverage) << MakeCondiment<CLemon>();
			break;
		case 2:
			beverage = move(beverage) << MakeCondiment<CCinnamon>();
			break;
		case 3:
			beverage = move(beverage) << MakeCondiment<CCream>();
			break;
		case 4:
			cout << "Chocole slices count? (cant be more than 5)" << endl;
			cin >> condimentChoice;
			beverage = move(beverage) << MakeCondiment<CChocolate>(condimentChoice);
			break;
		case 5:
			cout << "Liquor type? 1 - chocolate, 2 - nut" << endl;
			cin >> condimentChoice;
			beverage = move(beverage) << MakeCondiment<CLiquor>(static_cast<LiquorType>(condimentChoice - 1));
			break;
		case 0:
			needToLoop = false;
			break;
		default:
			return;
		}
	}

	cout << beverage->GetDescription() << ", cost: " << beverage->GetCost() << endl;
}

int main()
{
	DialogWithUser();
	cout << endl;
}

int main1()
{
	//DialogWithUser();
	//cout << endl;
	{
		// Наливаем чашечку латте
		auto latte = make_unique<CLatte>();
		// добавляем корицы
		auto cinnamon = make_unique<CCinnamon>(move(latte));
		// добавляем пару долек лимона
		auto lemon = make_unique<CLemon>(move(cinnamon), 2);
		// добавляем пару кубиков льда
		auto iceCubes = make_unique<CIceCubes>(move(lemon), 2, IceCubeType::Dry);
		// добавляем 2 грамма шоколадной крошки
		auto beverage = make_unique<CChocolateCrumbs>(move(iceCubes), 2);

		// Выписываем счет покупателю
		cout << beverage->GetDescription() << " costs " << beverage->GetCost() << endl;
	}

	{
		auto beverage =
			make_unique<CChocolateCrumbs>(			// Внешний слой: шоколадная крошка
				make_unique<CIceCubes>(				// | под нею - кубики льда
					make_unique<CLemon>(			// | | еще ниже лимон
						make_unique<CCinnamon>(		// | | | слоем ниже - корица
							make_unique<CLatte>()),	// | | |   в самом сердце - Латте
						2),							// | | 2 дольки лимона
					2, IceCubeType::Dry),			// | 2 кубика сухого льда
				2);									// 2 грамма шоколадной крошки

		// Выписываем счет покупателю
		cout << beverage->GetDescription() << " costs " << beverage->GetCost() << endl;
	}

	// Подробнее рассмотрим работу MakeCondiment и оператора <<
	{
		// lemon - функция, добавляющая "2 дольки лимона" к любому напитку
		auto lemon2 = MakeCondiment<CLemon>(2);
		// iceCubes - функция, добавляющая "3 кусочка льда" к любому напитку
		auto iceCubes3 = MakeCondiment<CIceCubes>(3, IceCubeType::Water);
		
		auto tea = make_unique<CTea>();

		// декорируем чай двумя дольками лимона и тремя кусочками льда
		auto lemonIceTea = iceCubes3(lemon2(move(tea)));	
		/* Предыдущая строка выполняет те же действия, что и следующий код:
		auto lemonIceTea = 
			make_unique<CIceCubes>(
				make_unique<CLemon>(
					move(tea), 
					2), 
				2, IceCubeType::Water);
		*/
		
		auto oneMoreLemonIceTea =
			make_unique<CTea>()	// Берем чай
			<< MakeCondiment<CLemon>(2)	// добавляем пару долек лимона
			<< MakeCondiment<CIceCubes>(3, IceCubeType::Water); // и 3 кубика льда
		/*
		Предыдущая конструкция делает то же самое, что и следующая:
		auto oneMoreLemonIceTea =
			MakeCondiment<CIceCubes>(3, IceCubeType::Water)(
				MakeCondiment<CLemon>(2)(make_unique<CTea>())
				);
		*/
	}

	// Аналог предыдущего решения с добавкой синтаксического сахара
	// обеспечиваемого операторами << и функцией MakeCondiment
	{
		auto beverage = 
			make_unique<CLatte>()							// Наливаем чашечку латте,
			<< MakeCondiment<CCinnamon>()					// оборачиваем корицей,
			<< MakeCondiment<CLemon>(2)						// добавляем пару долек лимона
			<< MakeCondiment<CIceCubes>(2, IceCubeType::Dry)// брасаем пару кубиков сухого льда
			<< MakeCondiment<CChocolateCrumbs>(2);			// посыпаем шоколадной крошкой

		// Выписываем счет покупателю
		cout << beverage->GetDescription() << " costs " << beverage->GetCost() << endl;
	}

	{
		auto beverage = 
			make_unique<CMilkshake>()					// Наливаем молочный коктейль
			<< MakeCondiment<CSyrup>(SyrupType::Maple)	// заливаем кленовым сиропом
			<< MakeCondiment<CCoconutFlakes>(8);		// посыпаем кокосовой стружкой

		// Выписываем счет покупателю
		cout << beverage->GetDescription() << " costs " << beverage->GetCost() << endl;
	}

	return 1;
}
