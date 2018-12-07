#pragma once

using namespace std;

// Пространство имен графической библиотеки (недоступно для изменения)
namespace graphics_lib
{
	// Холст для рисования
	class ICanvas
	{
	public:
		// Ставит "перо" в точку x, y
		virtual void MoveTo(int x, int y) = 0;
		// Рисует линию с текущей позиции, передвигая перо в точку x,y 
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