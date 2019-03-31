package com.ood.canvas;

import com.ood.canvas.Canvas;
import com.ood.exception.LogicErrorException;

import java.awt.*;

public class SimpleCanvas implements Canvas {


    private Color fillColor;

    private Color lineColor;

    private float lineThickness;

    private boolean isFilling = false;

    @Override
    public void setLineColor(Color color) {
        lineColor = color;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public float getLineThickness() {
        return lineThickness;
    }

    @Override
    public void setLineThickness(float lineThickness) {
        this.lineThickness = lineThickness;
    }

    @Override
    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    @Override
    public void beginFill(Color color) throws LogicErrorException {
        if (isFilling) {
            throw new LogicErrorException("Drawing has already begun.");
        }
        System.out.println("Begin fill");
        isFilling = true;
        fillColor = color;
    }

    @Override
    public void endFill() throws LogicErrorException {
        if (!isFilling) {
            throw new LogicErrorException("Drawing has already begun.");
        }

        System.out.println("End fill");
        isFilling = false;
    }

    @Override
    public void moveTo(float x, float y) {
        System.out.println("    Move to (" + x + " , " + y + ")");
    }

    @Override
    public void fillPolygon(int[] xPoints, int[] yPoints, int numberPoints) {
        System.out.println("    Fill polygon which contains " + numberPoints + " points.");
    }

    @Override
    public void lineTo(float x, float y) {
        System.out.println("    Line to (" + x + " , " + y + ")");
    }

    @Override
    public void drawEllipse(float left, float top, float width, float height) {
        System.out.println("    Draw ellipse left=" + left + " top=" + top + " width=" + width + " height=" + height);
    }

}
