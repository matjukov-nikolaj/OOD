package com.ood.colored.shape_drawing_lib;

import com.ood.colored.graphics_lib.Canvas;

import java.awt.*;

public class Rectangle implements CanvasDrawable {

    private Point leftTop;

    private int width;

    private int height;

    private Color color;

    public Rectangle(Point leftTop, int width, int height, Color color) {
        this.leftTop = leftTop;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setColor(this.color);
        canvas.moveTo(this.leftTop.getX(), this.leftTop.getY());
        canvas.lineTo(this.leftTop.getX() + this.width, this.leftTop.getY());
        canvas.lineTo(this.leftTop.getX() + this.width, this.leftTop.getY() + this.height);
        canvas.lineTo(this.leftTop.getX(), this.leftTop.getY());
        canvas.lineTo(this.leftTop.getX(), this.leftTop.getY());
    }
}
