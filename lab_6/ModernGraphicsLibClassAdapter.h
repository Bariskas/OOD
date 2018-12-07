#pragma once
#include <iostream>
#include "GraphicsLib.h"
#include "ModernGraphicsLib.h"

using namespace std;

namespace modern_graphics_lib_class_adapter
{
	class CModernGraphicsRendererClassAdapter : public graphics_lib::ICanvas, public modern_graphics_lib::CModernGraphicsRenderer
	{
	public:
		CModernGraphicsRendererClassAdapter(ostream& outputStream)
			: CModernGraphicsRenderer(outputStream)
		{}

		void MoveTo(int x, int y) override
		{
			m_currentPoint.x = x;
			m_currentPoint.y = y;
		}

		void LineTo(int x, int y) override
		{
			DrawLine(m_currentPoint, { x, y });
			MoveTo(x, y);
		}
	private:
		modern_graphics_lib::CPoint m_currentPoint = { 0, 0 };
	};
}