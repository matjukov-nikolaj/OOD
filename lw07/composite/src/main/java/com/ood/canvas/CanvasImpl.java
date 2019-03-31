package com.ood.canvas;

import com.ood.exception.LogicErrorException;
import com.ood.shapes.Point;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CanvasImpl implements Canvas {

    private Graphics2D graphics;

    private Color fillColor;

    private Color lineColor;

    private float lineThickness;

    private com.ood.shapes.Point lastPoint;

    private boolean isFilling = false;

    public CanvasImpl(Graphics2D graphics) {
        this.graphics = graphics;
    }

    @Override
    public void setLineColor(Color color) {
        lineColor = color;
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
        lastPoint = new com.ood.shapes.Point(x, y);
    }

    @Override
    public void fillPolygon(int[] xPoints, int[] yPoints, int numberPoints) {
        graphics.setColor(fillColor);
        graphics.fillPolygon(xPoints, yPoints, numberPoints);
    }

    @Override
    public void lineTo(float x, float y) {
        System.out.println("    Line to (" + x + " , " + y + ")");
        com.ood.shapes.Point currPoint = new Point(x, y);
        graphics.setColor(lineColor);
        graphics.setStroke(new BasicStroke(lineThickness));
        graphics.drawLine((int)lastPoint.getX(), (int)lastPoint.getY(), (int)currPoint.getX(), (int)currPoint.getY());
        lastPoint.setX(x);
        lastPoint.setY(y);
    }

    @Override
    public void drawEllipse(float left, float top, float width, float height) {
        System.out.println("    Draw ellipse left=" + left + " top=" + top + " width=" + width + " height=" + height);
        Ellipse2D ellipse = new Ellipse2D.Float(left, top, width, height);
        graphics.setColor(lineColor);
        graphics.setStroke(new BasicStroke(lineThickness));
        graphics.draw(ellipse);
        graphics.setColor(fillColor);
        graphics.fill(ellipse);
    }
}