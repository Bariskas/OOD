#pragma once
#include "IParagraph.h"

class CParagraph : public IParagraph
{
public:
	string GetText()const override;
	void SetText(const string& text) override;
private:
	std::string m_text;
};