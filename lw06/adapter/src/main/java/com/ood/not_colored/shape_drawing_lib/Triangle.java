package com.ood.not_colored.shape_drawing_lib;

import com.ood.not_colored.graphics_lib.Canvas;

public class Triangle implements CanvasDrawable {

    private Point vertex1;

    private Point vertex2;

    private Point vertex3;

    public Triangle(Point vertex1, Point vertex2, Point vertex3) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.moveTo(vertex1.getX(), vertex1.getY());
        canvas.lineTo(vertex2.getX(), vertex2.getY());
        canvas.lineTo(vertex3.getX(), vertex3.getY());
        canvas.lineTo(vertex1.getX(), vertex1.getY());
    }
}
