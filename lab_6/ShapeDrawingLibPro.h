#pragma once

#include "GraphicsLibPro.h"

using namespace std;

// ������������ ���� ���������� ��� ��������� ����� (���������� graphics_lib)
// ��� ���������� ���������� ��� ���������
namespace shape_drawing_lib_pro
{
	struct Point
	{
		Point(int x, int y) :x(x), y(y) {}
		int x;
		int y;
	};

	// ��������� ��������, ������� ����� ���� ���������� �� ������ �� graphics_lib
	class ICanvasDrawable
	{
	public:
		virtual void Draw(graphics_lib_pro::ICanvas & canvas)const = 0;
		virtual ~ICanvasDrawable() = default;
	};

	class CTriangle : public ICanvasDrawable
	{
	public:
		CTriangle() = default;
		CTriangle(const Point & p1, const Point & p2, const Point & p3, uint32_t color)
			: m_p1(p1)
			, m_p2(p2)
			, m_p3(p3)
			, m_color(color)
		{}

		void Draw(graphics_lib_pro::ICanvas & canvas)const override
		{
			canvas.SetColor(m_color);
			canvas.MoveTo(m_p1.x, m_p1.y);
			canvas.LineTo(m_p2.x, m_p2.y);
			canvas.LineTo(m_p3.x, m_p3.y);
			canvas.LineTo(m_p1.x, m_p1.y);
		}
	private:
		Point m_p1 = { 0, 0 };
		Point m_p2 = { 0, 0 };
		Point m_p3 = { 0, 0 };
		uint32_t m_color;
	};

	class CRectangle : public ICanvasDrawable
	{
	public:
		CRectangle(const Point & leftTop, int width, int height, uint32_t color)
			: m_leftTop(leftTop)
			, m_width(width)
			, m_height(height)
			, m_color(color)
		{ }

		void Draw(graphics_lib_pro::ICanvas & canvas)const override
		{
			canvas.SetColor(m_color);
			canvas.MoveTo(m_leftTop.x, m_leftTop.y);
			canvas.LineTo(m_leftTop.x + m_width, m_leftTop.y);
			canvas.LineTo(m_leftTop.x + m_width, m_leftTop.y + m_height);
			canvas.LineTo(m_leftTop.x, m_leftTop.y + m_height);
			canvas.LineTo(m_leftTop.x, m_leftTop.y);
		}
	private:
		Point m_leftTop = { 0, 0 };
		int m_width = 0;
		int m_height = 0;
		uint32_t m_color;
	};

	// ��������, ��������� �������� ICanvasDrawable-������� �� ICanvas
	class CCanvasPainter
	{
	public:
		CCanvasPainter(graphics_lib_pro::ICanvas & canvas)
			: m_canvas(&canvas)
		{ }
		void Draw(const ICanvasDrawable & drawable)
		{
			drawable.Draw(*m_canvas);
		}
	private:
		graphics_lib_pro::ICanvas * m_canvas;
	};
}