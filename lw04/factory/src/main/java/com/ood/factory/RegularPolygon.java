package com.ood.factory;

import com.ood.exceptions.WrongAmountException;

public class RegularPolygon extends Shape{

    private Point center;

    private int vertexCount;

    private double radius;

    public RegularPolygon(Point center, int vertexCount, double radius, Color color) throws WrongAmountException {
        super(color);
        if (vertexCount < 0) {
            throw new WrongAmountException("The number of angles can not be less than zero.");
        }
        this.vertexCount = vertexCount;
        this.center = center;
        this.radius = radius;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setColor(getColor());

        double angles = 2 * Math.PI / this.vertexCount;

        Point prevPoint = new Point(this.center.x + this.radius * Math.cos(0), this.center.y);

        for (int i = 1; i <= vertexCount; ++i) {
            Point tempPoint = new Point(this.radius * Math.cos(angles * i), this.radius * Math.sin(angles * i));
            Point point = new Point(this.center.x + tempPoint.x, this.center.y + tempPoint.y );
            canvas.drawLine(prevPoint, point);
            prevPoint = point;
        }

    }

    public Point getCenter() {
        return center;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public double getRadius() {
        return radius;
    }
}
