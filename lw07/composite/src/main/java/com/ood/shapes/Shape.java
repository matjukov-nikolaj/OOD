package com.ood.shapes;

import com.ood.canvas.Drawable;

public interface Shape extends Drawable {

    Rect<Float> getFrame();

    void setFrame(Rect<Float> rect);

    Style getOutlineStyle();

    void setOutlineStyle(Style style);

    Style getFillStyle();

    void setFillStyle(Style style);

    double getLineThickness();

    void setLineThickness(float thickness);

}
