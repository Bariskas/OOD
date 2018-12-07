#pragma once

using namespace std;

// ������������ ���� ����������� ���������� (���������� ��� ���������)
namespace graphics_lib
{
	// ����� ��� ���������
	class ICanvas
	{
	public:
		// ������ "����" � ����� x, y
		virtual void MoveTo(int x, int y) = 0;
		// ������ ����� � ������� �������, ���������� ���� � ����� x,y 
		virtual void LineTo(int x, int y) = 0;
		virtual ~ICanvas() = default;
	};

	// ���������� ������ ��� ���������
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