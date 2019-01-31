#include "Editor.h"

void CEditor::AddMenuItem(const string & shortcut, const string & description, MenuHandler handler)
{
	m_menu.AddItem(shortcut, description, bind(handler, this, _1));
}

void CEditor::InsertParagraph(istream & in)
{
    string position;
    in >> position;
    optional<size_t> positionAsNumber;
    if (position == "end")
    {
        positionAsNumber = {};
    }
    else if (utils::IsNumber(position))
    {
        positionAsNumber = stoi(position);
    }
    else
    {
        cout << "Invalid command format" << endl;
        return;
    }

    string text;
    getline(in, text);
    m_document->InsertParagraph(text, positionAsNumber);
}

void CEditor::SetTitle(istream & in)
{
    string head;
    string tail;

    if (in >> head)
    {
        getline(in, tail);
    }
    std::cout << head << std::endl << tail << std::endl;
    string title = head + tail;

    m_document->SetTitle(title);
}

void CEditor::List(istream &);
{
cout << "-------------" << endl;
cout << m_document->GetTitle() << endl;
cout << "-------------" << endl;
}

void CEditor::Undo(istream &)
{
    if (m_document->CanUndo())
    {
        m_document->Undo();
    }
    else
    {
        cout << "Can't undo" << endl;
    }
}

void CEditor::Redo(istream &)
{
    if (m_document->CanRedo())
    {
        m_document->Redo();
    }
    else
    {
        cout << "Can't redo" << endl;
    }
}