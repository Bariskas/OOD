package Shapes;

interface Shape extends Drawable {
    FrameData getFrame();
    void setFrame(FrameData frame);
    Style getOutlineStyle();
    Style getFillStyle();

    GroupShape getGroup();
}
