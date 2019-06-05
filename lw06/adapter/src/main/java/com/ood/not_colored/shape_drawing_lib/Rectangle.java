package com.ood.not_colored.shape_drawing_lib;

import com.ood.not_colored.graphics_lib.Canvas;

public class Rectangle implements CanvasDrawable {

    private Point leftTop;

    private int width;

    private int height;

    public Rectangle(Point leftTop, int width, int height) {
        this.leftTop = leftTop;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.moveTo(this.leftTop.getX(), this.leftTop.getY());
        canvas.lineTo(this.leftTop.getX() + this.width, this.leftTop.getY());
        canvas.lineTo(this.leftTop.getX() + this.width, this.leftTop.getY() + this.height);
        canvas.lineTo(this.leftTop.getX(), this.leftTop.getY());
        canvas.lineTo(this.leftTop.getX(), this.leftTop.getY());
    }
}
