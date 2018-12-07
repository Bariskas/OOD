#pragma once
#include <iostream>
#include "GraphicsLibPro.h"
#include "ModernGraphicsLibPro.h"

using namespace std;

namespace modern_graphics_lib_pro_class_adapter
{
	class CModernGraphicsRendererClassAdapter : public graphics_lib_pro::ICanvas, public modern_graphics_lib_pro::CModernGraphicsRenderer
	{
	public:
		CModernGraphicsRendererClassAdapter(ostream& outputStream)
			: CModernGraphicsRenderer(outputStream)
		{}

		void SetColor(uint32_t rgbColor)
		{
			m_color.r = (float)((rgbColor >> 16) & 0xff) / 0xff;
			m_color.g = (float)((rgbColor >> 8) & 0xff) / 0xff;
			m_color.b = (float)((rgbColor) & 0xff) / 0xff;
			m_color.a = 1.0;
		}

		void MoveTo(int x, int y) override
		{
			m_currentPoint.x = x;
			m_currentPoint.y = y;
		}

		void LineTo(int x, int y) override
		{
			DrawLine(m_currentPoint, { x, y }, m_color);
			MoveTo(x, y);
		}
	private:
		modern_graphics_lib_pro::CPoint m_currentPoint = { 0, 0 };
		modern_graphics_lib_pro::CRGBAColor m_color;
	};
}