#pragma once
#include "GraphicsLibPro.h"
#include "ModernGraphicsLibPro.h"

using namespace std;

namespace modern_graphics_lib_pro_adapter
{
	class CModernGraphicsRendererAdapter : public graphics_lib_pro::ICanvas
	{
	public:
		CModernGraphicsRendererAdapter(modern_graphics_lib_pro::CModernGraphicsRenderer & renderer)
			: m_renderer(renderer)
		{}

		void SetColor(uint32_t color)
		{
			m_color.r = (float)((color >> 16) & 0xff) / 0xff;
			m_color.g = (float)((color >> 8) & 0xff) / 0xff;
			m_color.b = (float)((color) & 0xff) / 0xff;
			m_color.a = 1.;
		}

		void MoveTo(int x, int y) override
		{
			m_currentPoint.x = x;
			m_currentPoint.y = y;
		}

		void LineTo(int x, int y) override
		{
			modern_graphics_lib_pro::CPoint nextPoint(x, y);
			m_renderer.DrawLine(m_currentPoint, nextPoint, m_color);
			MoveTo(x, y);
		}

	private:
		modern_graphics_lib_pro::CModernGraphicsRenderer& m_renderer;
		modern_graphics_lib_pro::CPoint m_currentPoint = { 0, 0 };
		modern_graphics_lib_pro::CRGBAColor m_color;
	};
}