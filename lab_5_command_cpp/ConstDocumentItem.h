#pragma once
#include <memory>
#include "IImage.h"
#include "IParagraph.h"
using namespace std;

class CConstDocumentItem
{
public:
	CConstDocumentItem() : m_image(nullptr), m_paragraph(nullptr) {};
	CConstDocumentItem(shared_ptr<IImage>&& image);
	CConstDocumentItem(shared_ptr<IParagraph>&& paragraph);
	// Возвращает указатель на константное изображение, либо nullptr, если элемент не является изображением
	shared_ptr<const IImage> GetImage()const;
	// Возвращает указатель на константный параграф, либо nullptr, если элемент не является параграфом
	shared_ptr<const IParagraph> GetParagraph()const;
	virtual ~CConstDocumentItem() = default;
protected:
	shared_ptr<IImage> m_image;
	shared_ptr<IParagraph> m_paragraph;
};