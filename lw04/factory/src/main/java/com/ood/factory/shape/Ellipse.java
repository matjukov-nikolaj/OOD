package com.ood.factory.shape;

import com.ood.factory.canvas.Canvas;
import com.ood.factory.shapefactory.Color;

public class Ellipse extends Shape {

    private Point center;

    private double horizontalRadius;

    private double verticalRadius;

    public Ellipse(Point center, double horizontalRadius, double verticalRadius, Color color) {
        super(color);
        this.center = center;
        this.horizontalRadius = horizontalRadius;
        this.verticalRadius = verticalRadius;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setColor(getColor());

        canvas.drawEllipse(this.center.getX() - this.horizontalRadius, this.center.getY() - this.verticalRadius,
                this.horizontalRadius, this.verticalRadius);
    }

    public Point getCenter() {
        return center;
    }

    public double getHorizontalRadius() {
        return horizontalRadius;
    }

    public double getVerticalRadius() {
        return verticalRadius;
    }
}
