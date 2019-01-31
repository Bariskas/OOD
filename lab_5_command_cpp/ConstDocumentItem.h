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
	// ���������� ��������� �� ����������� �����������, ���� nullptr, ���� ������� �� �������� ������������
	shared_ptr<const IImage> GetImage()const;
	// ���������� ��������� �� ����������� ��������, ���� nullptr, ���� ������� �� �������� ����������
	shared_ptr<const IParagraph> GetParagraph()const;
	virtual ~CConstDocumentItem() = default;
protected:
	shared_ptr<IImage> m_image;
	shared_ptr<IParagraph> m_paragraph;
};