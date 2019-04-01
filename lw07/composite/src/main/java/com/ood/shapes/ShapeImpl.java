package com.ood.shapes;

import com.ood.canvas.Canvas;

import java.awt.*;

public abstract class ShapeImpl implements Shape {

    private Rect<Float> frame;

    private Style fillStyle;

    private Style outlineStyle;

    private float lineThickness;

    public ShapeImpl(Rect<Float> frame, Style fillStyle, Style outlineStyle, float lineThickness) {
        this.frame = frame;
        this.fillStyle = fillStyle;
        this.outlineStyle = outlineStyle;
        this.lineThickness = lineThickness;
    }

    @Override
    public Rect<Float> getFrame() {
        return frame;
    }

    @Override
    public void setFrame(Rect<Float> frame) {
        this.frame = frame;
    }

    public Style getOutlineStyle() {
        return outlineStyle;
    }

    @Override
    public void setOutlineStyle(Style outlineStyle) {
        this.outlineStyle = outlineStyle;
    }

    @Override
    public Style getFillStyle() {
        return fillStyle;
    }

    @Override
    public void setFillStyle(Style fillStyle) {
        this.fillStyle = fillStyle;
    }

    @Override
    public double getLineThickness() {
        return lineThickness;
    }

    @Override
    public void setLineThickness(float lineThickness) {
        this.lineThickness = lineThickness;
    }

    public abstract void drawBehavior(Canvas canvas);

    @Override
    public void draw(Canvas canvas) {
        try {
            if (fillStyle.isEnabled()) {
                canvas.beginFill(fillStyle.getColor());
            } else {
                canvas.setFillColor(new Color(0, 0,0,0));
            }

            if (outlineStyle.isEnabled()) {
                canvas.setLineColor(outlineStyle.getColor());
            } else {
                canvas.setLineColor(new Color(0, 0,0,0));
            }

            canvas.setLineThickness(lineThickness);
            drawBehavior(canvas);

            if (fillStyle.isEnabled()) {
                canvas.endFill();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
