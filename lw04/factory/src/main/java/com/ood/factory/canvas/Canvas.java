package com.ood.factory.canvas;

import com.ood.factory.shapefactory.Color;
import com.ood.factory.shape.Point;

public interface Canvas {

    void setColor(Color color);

    void drawLine(Point from, Point to);

    void drawEllipse(double left, double top, double width, double height);


}
