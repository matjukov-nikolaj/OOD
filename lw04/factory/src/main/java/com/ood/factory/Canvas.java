package com.ood.factory;

public interface Canvas {

    void setColor(Color color);

    void drawLine(Point from, Point to);

    void drawEllipse(double left, double top, double width, double height);


}
