package com.ood.colored.shape_drawing_lib;

import com.ood.colored.graphics_lib.Canvas;

import java.awt.*;

public class Triangle implements CanvasDrawable {

    private Point vertex1;

    private Point vertex2;

    private Point vertex3;

    private Color color;

    public Triangle(Point vertex1, Point vertex2, Point vertex3, Color color) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setColor(this.color);
        canvas.moveTo(vertex1.getX(), vertex1.getY());
        canvas.lineTo(vertex2.getX(), vertex2.getY());
        canvas.lineTo(vertex3.getX(), vertex3.getY());
        canvas.lineTo(vertex1.getX(), vertex1.getY());
    }
}
