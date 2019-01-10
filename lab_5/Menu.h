#pragma once
#include <boost/range/algorithm/find_if.hpp>
#include <iostream>
#include <sstream>
#include <vector>
#include <string>
#include <functional>

using namespace std;

class CMenu
{
public:
	typedef function<void(istream &args)> Command;
	void AddItem(const string & shortcut, const string & description, const Command & command);

	void Run();

	void ShowInstructions()const;

	void Exit();

private:
	bool ExecuteCommand(const string & command);

	struct Item
	{
		Item(const string & shortcut, const string & description, const Command & command)
			: shortcut(shortcut)
			, description(description)
			, command(command)
		{}

		string shortcut;
		string description;
		Command command;
	};
	vector<Item> m_items;
	bool m_exit = false;
};