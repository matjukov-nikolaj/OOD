package com.ood.not_colored.adapter;

import com.ood.not_colored.graphics_lib.Canvas;
import com.ood.not_colored.modern_graphics_lib.ModernGraphicsRenderer;
import com.ood.not_colored.modern_graphics_lib.Point;

public class CanvasObjectAdapter implements Canvas {

    private ModernGraphicsRenderer renderer;

    private Point lastPoint;

    public CanvasObjectAdapter(ModernGraphicsRenderer renderer) {
        this.renderer = renderer;
        this.lastPoint = new Point(0, 0);
    }

    public ModernGraphicsRenderer getRenderer() {
        return renderer;
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
            this.renderer.drawLine(this.lastPoint, newPoint);
            this.lastPoint = newPoint;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
