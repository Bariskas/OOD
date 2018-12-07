#include "pch.h"

TEST(TestCaseName, TestName) {
  EXPECT_EQ(1, 1);
  EXPECT_TRUE(true);
}

struct Triangle : public ::testing::Test
{
	void SetUp()
	{
		
	}
	void TearDown()
	{
	}
	stringstream outputStream;
	graphics_lib::CCanvas canvas{ outputStream };
	shape_drawing_lib::CTriangle triangle{ { 10, 15 }, { 100, 200 }, { 150, 250 } };
};


TEST_F(Triangle, draw_triangle_test)
{
	triangle.Draw(canvas);
	auto expectedCanvasContent =
		R"(MoveTo (10, 15)
LineTo (100, 200)
LineTo (150, 250)
LineTo (10, 15)
)";
	ASSERT_EQ(outputStream.str(), expectedCanvasContent) << "picture is not equal to expected picture";;
}



struct CModernGraphicsRendererAdapterTestFixture : public ::testing::Test
{
	stringstream outputStream;
	shape_drawing_lib::CTriangle triangle{ { 10, 15 },{ 100, 200 },{ 150, 250 } };
	modern_graphics_lib::CModernGraphicsRenderer renderer{ outputStream };
	modern_graphics_lib_adapter::CModernGraphicsRendererAdapter rendererApadter{ renderer };
	shape_drawing_lib::CCanvasPainter painter{ rendererApadter };
};

TEST_F(CModernGraphicsRendererAdapterTestFixture, object_adapter_test)
{
	renderer.BeginDraw();
	painter.Draw(triangle);
	renderer.EndDraw();
	auto expectedCanvasContent =
		R"(<draw>
  <line fromX="10" fromY="15" toX="100" toY="200"/>
  <line fromX="100" fromY="200" toX="150" toY="250"/>
  <line fromX="150" fromY="250" toX="10" toY="15"/>
</draw>
)";
	ASSERT_EQ(outputStream.str(), expectedCanvasContent) << "picture drawed with object adapter is not equal to expected picture";;
}

struct CModernGraphicsRendererClassAdapterTestFixture : public ::testing::Test
{
	stringstream outputStream;
	shape_drawing_lib::CTriangle triangle{ { 10, 15 },{ 100, 200 },{ 150, 250 } };
	modern_graphics_lib_class_adapter::CModernGraphicsRendererClassAdapter rendererClassAdapter{ outputStream };
	shape_drawing_lib::CCanvasPainter painter{ rendererClassAdapter };
};

TEST_F(CModernGraphicsRendererClassAdapterTestFixture, object_adapter_test)
{
	rendererClassAdapter.BeginDraw();
	painter.Draw(triangle);
	rendererClassAdapter.EndDraw();
	auto expectedCanvasContent =
		R"(<draw>
  <line fromX="10" fromY="15" toX="100" toY="200"/>
  <line fromX="100" fromY="200" toX="150" toY="250"/>
  <line fromX="150" fromY="250" toX="10" toY="15"/>
</draw>
)";
	ASSERT_EQ(outputStream.str(), expectedCanvasContent) << "picture draw/drew/drawn with object adapter is not equal to expected picture";;
}



struct CProModernGraphicsRendererAdapterTestFixture : public ::testing::Test
{
	stringstream outputStream;
	shape_drawing_lib_pro::CTriangle triangle{ { 10, 15 },{ 100, 200 },{ 150, 250 }, 2147498743 };
	modern_graphics_lib_pro::CModernGraphicsRenderer renderer{ outputStream };
	modern_graphics_lib_pro_adapter::CModernGraphicsRendererAdapter rendererApadter{ renderer };
	shape_drawing_lib_pro::CCanvasPainter painter{ rendererApadter };
};

TEST_F(CProModernGraphicsRendererAdapterTestFixture, object_adapter_test)
{
	renderer.BeginDraw();
	painter.Draw(triangle);
	renderer.EndDraw();
	auto expectedCanvasContent =
		R"(<draw>
  <line fromX="10" fromY="15" toX="100" toY="200">
    <color r="0.0" g="0.2" b="1.0" a="1.0" />
  </line>
  <line fromX="100" fromY="200" toX="150" toY="250">
    <color r="0.0" g="0.2" b="1.0" a="1.0" />
  </line>
  <line fromX="150" fromY="250" toX="10" toY="15">
    <color r="0.0" g="0.2" b="1.0" a="1.0" />
  </line>
</draw>
)";
	ASSERT_EQ(outputStream.str(), expectedCanvasContent) << "picture drawed with object adapter is not equal to expected picture";;
}

struct CProModernGraphicsRendererClassAdapterTestFixture : public ::testing::Test
{
	stringstream outputStream;
	shape_drawing_lib_pro::CTriangle triangle{ { 10, 15 },{ 100, 200 },{ 150, 250 }, 2147498743 };
	modern_graphics_lib_pro_class_adapter::CModernGraphicsRendererClassAdapter rendererClassAdapter{ outputStream };
	shape_drawing_lib_pro::CCanvasPainter painter{ rendererClassAdapter };
};

TEST_F(CProModernGraphicsRendererClassAdapterTestFixture, object_adapter_test)
{
	rendererClassAdapter.BeginDraw();
	painter.Draw(triangle);
	rendererClassAdapter.EndDraw();
	auto expectedCanvasContent =
		R"(<draw>
  <line fromX="10" fromY="15" toX="100" toY="200">
    <color r="0.0" g="0.2" b="1.0" a="1.0" />
  </line>
  <line fromX="100" fromY="200" toX="150" toY="250">
    <color r="0.0" g="0.2" b="1.0" a="1.0" />
  </line>
  <line fromX="150" fromY="250" toX="10" toY="15">
    <color r="0.0" g="0.2" b="1.0" a="1.0" />
  </line>
</draw>
)";
	ASSERT_EQ(outputStream.str(), expectedCanvasContent) << "picture draw/drew/drawn with object adapter is not equal to expected picture";;
}