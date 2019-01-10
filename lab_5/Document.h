#pragma once
#include "IDocument.h"
#include "History.h"

class CDocument :public IDocument
{
public:
	shared_ptr<IParagraph> InsertParagraph(const string& text,
		optional<size_t> position = nullopt) override;
	shared_ptr<IImage> InsertImage(const Path& path, int width, int height,
		optional<size_t> position = nullopt) override;

	size_t GetItemsCount()const override;
	CConstDocumentItem GetItem(size_t index)const override;
	CDocumentItem GetItem(size_t index) override;
	void DeleteItem(size_t index) override;

	void SetTitle(const std::string & title) override;
	std::string GetTitle() const override;

	void Save(const Path& path)const override;

	bool CanUndo() const override;
	void Undo() override;
	bool CanRedo() const override;
	void Redo() override;

private:
	std::string m_title;
	CHistory m_history;
};