#include "Graphic.h"

int main()
{
	cout << "Should we use colorful version API? y|Y";
	string userInput;
	bool isColorful = false;
	if (getline(cin, userInput) && (userInput == "y" || userInput == "Y"))
	{
		isColorful = true;
	}

	cout << "Should we use new API (y)?";
	if (getline(cin, userInput) && (userInput == "y" || userInput == "Y"))
	{
		cout << "Should we use object adapter? y|Y - object adapter; else - class adapter";
		if (getline(cin, userInput) && (userInput == "y" || userInput == "Y"))
		{
			if (isColorful)
			{
				app_pro::PaintPictureOnModernGraphicsRenderer();
			}
			else
			{
				app::PaintPictureOnModernGraphicsRenderer();
			}
		}
		else
		{
			if (isColorful)
			{
				app_pro::PaintPictureOnModernGraphicsRendererClassAdapter();
			}
			else
			{
				app::PaintPictureOnModernGraphicsRendererClassAdapter();
			}
		}
	}
	else
	{
		if (isColorful)
		{
			app_pro::PaintPictureOnCanvas();
		}
		else
		{
			app::PaintPictureOnCanvas();
		}
	}

	return 0;
}
