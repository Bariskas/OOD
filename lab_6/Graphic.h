#pragma once

#include <iostream>
#include <stdexcept>
#include <string>
#include <boost/format.hpp>
#include <cstdint>

#include "GraphicsLib.h"
#include "ShapeDrawingLib.h"
#include "ModernGraphicsLib.h"
#include "ModernGraphicsLibAdapter.h"
#include "ModernGraphicsLibClassAdapter.h"
#include "GraphicsLibPro.h"
#include "ShapeDrawingLibPro.h"
#include "ModernGraphicsLibPro.h"
#include "ModernGraphicsLibProAdapter.h"
#include "ModernGraphicsLibProClassAdapter.h"

using namespace std;

// Пространство имен приложения (доступно для модификации)
namespace app
{
	void PaintPicture(shape_drawing_lib::CCanvasPainter & painter)
	{
		using namespace shape_drawing_lib;

		CTriangle triangle({ 10, 15 }, { 100, 200 }, { 150, 250 });
		CRectangle rectangle({ 30, 40 }, 18, 24);

		painter.Draw(triangle);
		painter.Draw(rectangle);
	}

	void PaintPictureOnCanvas()
	{
		graphics_lib::CCanvas simpleCanvas;
		shape_drawing_lib::CCanvasPainter painter(simpleCanvas);
		PaintPicture(painter);
	}

	void PaintPictureOnModernGraphicsRenderer()
	{
		modern_graphics_lib::CModernGraphicsRenderer renderer(cout);
		modern_graphics_lib_adapter::CModernGraphicsRendererAdapter rendererApadter(renderer);
		shape_drawing_lib::CCanvasPainter painter(rendererApadter);

		renderer.BeginDraw();
		PaintPicture(painter);
		renderer.EndDraw();
	}

	void PaintPictureOnModernGraphicsRendererClassAdapter()
	{
		modern_graphics_lib_class_adapter::CModernGraphicsRendererClassAdapter rendererClassAdapter(cout);
		shape_drawing_lib::CCanvasPainter painter(rendererClassAdapter);

		rendererClassAdapter.BeginDraw();
		PaintPicture(painter);
		rendererClassAdapter.EndDraw();
	}
}

namespace app_pro
{
	void PaintPicture(shape_drawing_lib_pro::CCanvasPainter & painter)
	{
		using namespace shape_drawing_lib_pro;

		CTriangle triangle({ 10, 15 }, { 100, 200 }, { 150, 250 }, 2147498743); // 10000000000000000011101011110111
		CRectangle rectangle({ 30, 40 }, 18, 24, 4017601271); //11101111011101111011101011110111

		painter.Draw(triangle);
		painter.Draw(rectangle);
	}

	void PaintPictureOnCanvas()
	{
		graphics_lib_pro::CCanvas simpleCanvas;
		shape_drawing_lib_pro::CCanvasPainter painter(simpleCanvas);
		PaintPicture(painter);
	}

	void PaintPictureOnModernGraphicsRenderer()
	{
		modern_graphics_lib_pro::CModernGraphicsRenderer renderer(cout);
		modern_graphics_lib_pro_adapter::CModernGraphicsRendererAdapter rendererApadter(renderer);
		shape_drawing_lib_pro::CCanvasPainter painter(rendererApadter);

		renderer.BeginDraw();
		PaintPicture(painter);
		renderer.EndDraw();
	}

	void PaintPictureOnModernGraphicsRendererClassAdapter()
	{
		modern_graphics_lib_pro_class_adapter::CModernGraphicsRendererClassAdapter rendererClassAdapter(cout);
		shape_drawing_lib_pro::CCanvasPainter painter(rendererClassAdapter);

		rendererClassAdapter.BeginDraw();
		PaintPicture(painter);
		rendererClassAdapter.EndDraw();
	}
}