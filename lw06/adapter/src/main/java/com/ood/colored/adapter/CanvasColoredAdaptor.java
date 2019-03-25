package com.ood.colored.adapter;

import com.ood.colored.graphics_lib.Canvas;
import com.ood.colored.modern_graphics_lib.ModernGraphicsRenderer;
import com.ood.colored.modern_graphics_lib.Point;

import java.awt.*;
import java.io.PrintStream;

public class CanvasColoredAdaptor extends ModernGraphicsRenderer implements Canvas {

    private Color color;

    private Point lastPoint;

    public CanvasColoredAdaptor(PrintStream out) {
        super(out);
        this.color = new Color(0,0,0, 1);
        this.lastPoint = new Point(0,0);
    }

    public void startDraw() {
        try {
            beginDraw();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void finishDraw() {
        try {
            endDraw();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void moveTo(int x, int y) {
        this.lastPoint.setX(x);
        this.lastPoint.setY(y);
    }

    @Override
    public void lineTo(int x, int y) {
        try {
            Point newPoint = new Point(x, y);
            this.drawLine(this.lastPoint, newPoint, this.color);
            this.lastPoint = newPoint;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
