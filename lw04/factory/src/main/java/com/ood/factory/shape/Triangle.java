package com.ood.factory.shape;

import com.ood.factory.canvas.Canvas;
import com.ood.factory.shapefactory.Color;

public class Triangle extends Shape {

    private Point vertex1;
    private Point vertex2;
    private Point vertex3;

    public Triangle(Point vertex1, Point vertex2, Point vertex3, Color color) {
        super(color);
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setColor(this.getColor());
        //draw lines

        canvas.drawLine(this.vertex1, this.vertex2);
        canvas.drawLine(this.vertex2, this.vertex3);
        canvas.drawLine(this.vertex3, this.vertex1);
    }

    public Point getVertex1() {
        return vertex1;
    }

    public Point getVertex2() {
        return vertex2;
    }

    public Point getVertex3() {
        return vertex3;
    }
}
