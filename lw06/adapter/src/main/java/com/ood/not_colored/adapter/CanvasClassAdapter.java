package com.ood.not_colored.adapter;

import com.ood.not_colored.graphics_lib.Canvas;
import com.ood.not_colored.modern_graphics_lib.ModernGraphicsRenderer;
import com.ood.not_colored.modern_graphics_lib.Point;

import java.io.PrintStream;

public class CanvasClassAdapter extends ModernGraphicsRenderer implements Canvas {

    private Point lastPoint;

    public CanvasClassAdapter(PrintStream out) {
        super(out);
        this.lastPoint = new Point(0,0);
    }

    @Override
    public void moveTo(int x, int y) {
        this.lastPoint.setX(x);
        this.lastPoint.setY(y);
    }

    @Override
    public void lineTo(int x, int y) {
        try {
            this.beginDraw();
            Point newPoint = new Point(x, y);
            this.drawLine(this.lastPoint, newPoint);
            this.lastPoint = newPoint;
            this.endDraw();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
