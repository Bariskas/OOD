#pragma once

#include <cstdint>
#include <iostream>
#include <iomanip>

using namespace std;

namespace graphics_lib_pro
{
	// Холст для рисования
	class ICanvas
	{
	public:
		// Установка цвета в формате 0xRRGGBB
		virtual void SetColor(uint32_t rgbColor) = 0;
		virtual void MoveTo(int x, int y) = 0;
		virtual void LineTo(int x, int y) = 0;
		virtual ~ICanvas() = default;
	};

	// Реализация холста для рисования
	class CCanvas : public ICanvas
	{
	public:
		CCanvas()
			: m_out(cout)
		{}

		CCanvas(ostream& outputStream)
			: m_out(outputStream)
		{}

		void SetColor(uint32_t rgbColor) override
		{
			m_out << "SetColor #" << setfill('0') << setw(6) << hex << rgbColor << dec << endl;
		}
		void MoveTo(int x, int y) override
		{
			m_out << "MoveTo (" << x << ", " << y << ")" << endl;
		}
		void LineTo(int x, int y) override
		{
			m_out << "LineTo (" << x << ", " << y << ")" << endl;
		}
	private:
		ostream & m_out;
	};
}