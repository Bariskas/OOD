#pragma once
#include "IImage.h"

class CImage : public IImage
{
public:
	std::string GetPath()const override;
	int GetWidth()const override;
	int GetHeight()const override;
	void Resize(int width, int height) = 0;
private:
	std::string m_path;
	int m_width;
	int m_height;
};