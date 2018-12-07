#pragma once
#include "GraphicsLib.h"
#include "ModernGraphicsLib.h"

using namespace std;

namespace modern_graphics_lib_adapter
{
	class CModernGraphicsRendererAdapter : public graphics_lib::ICanvas
	{
	public:
		CModernGraphicsRendererAdapter(modern_graphics_lib::CModernGraphicsRenderer & renderer)
			: m_renderer(renderer)
		{}

		void MoveTo(int x, int y) override
		{
			m_currentPoint.x = x;
			m_currentPoint.y = y;
		}

		void LineTo(int x, int y) override
		{
			modern_graphics_lib::CPoint nextPoint(x, y);
			m_renderer.DrawLine(m_currentPoint, nextPoint);
			MoveTo(x, y);
		}

	private:
		modern_graphics_lib::CModernGraphicsRenderer& m_renderer;
		modern_graphics_lib::CPoint m_currentPoint = { 0, 0 };
	};
}