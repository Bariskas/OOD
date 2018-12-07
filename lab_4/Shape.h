#pragma once

#include <string>

class CShape
{
public:
	CShape() {}
	CShape(std::string outlineColor)
		: m_outlineColor(outlineColor)
	{}
	virtual ~ÑShape() {};
	/*virtual double GetArea() const = 0;
	virtual double GetPerimeter() const = 0;
	virtual std::string ToString() const = 0;
	virtual std::string GetOutlineColor() const = 0;*/
private:
	std::string m_outlineColor;
};

typedef std::shared_ptr<CShape> ShapePtr;