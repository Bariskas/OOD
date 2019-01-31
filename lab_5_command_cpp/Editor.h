#pragma once

#include <memory>
#include <functional>
#include <iostream>
#include "Menu.h"
#include "Document.h"
#include "StringUtils.h"

using namespace std;
using namespace std::placeholders;

class CEditor
{
public:
	CEditor()
		:m_document(make_unique<CDocument>())
	{
		m_menu.AddItem("help", "Help", [this](istream&) { m_menu.ShowInstructions(); });
		m_menu.AddItem("exit", "Exit", [this](istream&) { m_menu.Exit(); });
		AddMenuItem("setTitle", "Changes title. Args: <new title>", &CEditor::SetTitle);
		AddMenuItem("insertParagraph", "Insert paragraph. Args: <position>|end <paragrapg text>", &CEditor::InsertParagraph);
		AddMenuItem("list", "Show document", &CEditor::List);
		AddMenuItem("undo", "Undo command", &CEditor::Undo);
		AddMenuItem("redo", "Redo undone command", &CEditor::Redo);
	}

	void Start()
	{
		m_menu.Run();
	}

private:
	// ��������� �� ����� ������ CEditor, ����������� istream& � ������������ void
	typedef void (CEditor::*MenuHandler)(istream & in);

	void AddMenuItem(const string & shortcut, const string & description, MenuHandler handler);

	void InsertParagraph(istream & in);

	// TODO: �������� ������ ������ ����������
	void SetTitle(istream & in);

	void List(istream &);

	void Undo(istream &);

	void Redo(istream &);

	CMenu m_menu;
	unique_ptr<IDocument> m_document;
};