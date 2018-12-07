#pragma once

using namespace std;

// Пространство имен библиотеки для рисования фигур (использует graphics_lib)
// Код библиотеки недоступен для изменения
namespace shape_drawing_lib
{
	struct Point
	{
		Point(int x, int y) :x(x), y(y) {}
		int x;
		int y;
	};

	// Интерфейс объектов, которые могут быть нарисованы на холсте из graphics_lib
	class ICanvasDrawable
	{
	public:
		virtual void Draw(graphics_lib::ICanvas & canvas)const = 0;
		virtual ~ICanvasDrawable() = default;
	};

	class CTriangle : public ICanvasDrawable
	{
	public:
		CTriangle() = default;
		CTriangle(const Point & p1, const Point & p2, const Point & p3)
			: m_p1(p1)
			, m_p2(p2)
			, m_p3(p3)
		{}

		void Draw(graphics_lib::ICanvas & canvas)const override
		{
			canvas.MoveTo(m_p1.x, m_p1.y);
			canvas.LineTo(m_p2.x, m_p2.y);
			canvas.LineTo(m_p3.x, m_p3.y);
			canvas.LineTo(m_p1.x, m_p1.y);
		}
	private:
		Point m_p1 = { 0, 0 };
		Point m_p2 = { 0, 0 };
		Point m_p3 = { 0, 0 };
	};

	class CRectangle : public ICanvasDrawable
	{
	public:
		CRectangle(const Point & leftTop, int width, int height)
			: m_leftTop(leftTop)
			, m_width(width)
			, m_height(height)
		{ }

		void Draw(graphics_lib::ICanvas & canvas)const override
		{
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
	};

	// Художник, способный рисовать ICanvasDrawable-объекты на ICanvas
	class CCanvasPainter
	{
	public:
		CCanvasPainter(graphics_lib::ICanvas & canvas)
			: m_canvas(&canvas)
		{ }
		void Draw(const ICanvasDrawable & drawable)
		{
			drawable.Draw(*m_canvas);
		}
	private:
		graphics_lib::ICanvas * m_canvas;
	};
}