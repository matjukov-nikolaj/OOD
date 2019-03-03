package com.ood.factory;

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

        Point rightTop = new Point(this.rightBottom.x, this.leftTop.y);
        Point leftBottom = new Point(this.leftTop.x, this.rightBottom.y);

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
