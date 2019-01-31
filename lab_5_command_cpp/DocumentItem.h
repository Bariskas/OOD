#pragma once
#include "ConstDocumentItem.h"
#include <memory>

using namespace std;

class CDocumentItem : public CConstDocumentItem
{
public:
	// Возвращает указатель на изображение, либо nullptr, если элемент не является изображением
	shared_ptr<IImage> GetImage();
	// Возвращает указатель на параграф, либо nullptr, если элемент не является параграфом
	shared_ptr<IParagraph> GetParagraph();
};