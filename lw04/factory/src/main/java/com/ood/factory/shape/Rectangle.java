package com.ood.factory.shape;

import com.ood.factory.canvas.Canvas;
import com.ood.factory.shapefactory.Color;

public class Rectangle extends Shape {

    private Point leftTop;

    private Point rightBottom;

    public Rectangle(Point leftTop, Point rightBottom, Color color) {
        super(color);
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setColor(this.getColor());

        Point rightTop = new Point(this.rightBottom.getX(), this.leftTop.getY());
        Point leftBottom = new Point(this.leftTop.getX(), this.rightBottom.getY());

        canvas.drawLine(this.leftTop, rightTop);
        canvas.drawLine(rightTop, this.rightBottom);
        canvas.drawLine(this.rightBottom, leftBottom);
        canvas.drawLine(leftBottom, this.leftTop);
    }

    public Point getLeftTop() {
        return leftTop;
    }

    public Point getRightBottom() {
        return rightBottom;
    }

}
