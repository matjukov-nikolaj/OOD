package com.ood.canvas;

import com.ood.exception.LogicErrorException;

import java.awt.*;

public interface Canvas {

    void setLineColor(Color color);

    void setLineThickness(float thickness);

    void setFillColor(Color color);

    void beginFill(Color color) throws LogicErrorException;

    void endFill() throws LogicErrorException;

    void moveTo(float x, float y);

    void lineTo(float x, float y);

    void fillPolygon(int[] xPoints, int[] yPoints, int numberPoints);

    void drawEllipse(float left, float top, float width, float height);

}
