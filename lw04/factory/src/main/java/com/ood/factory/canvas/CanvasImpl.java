package com.ood.factory.canvas;

import com.ood.factory.shapefactory.Color;
import com.ood.factory.shape.Point;

public class CanvasImpl implements Canvas {

    private Color color;

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void drawLine(Point from, Point to) {
        System.out.println("===========================");
        System.out.println("DRAW LINE: ");
        System.out.println("  FROM : (" + from.getX() + ", " + from.getY() + ")");
        System.out.println("  TO   : (" + to.getX() + ", " + to.getY() + ")");
        System.out.println("  COLOR: " + Color.toString(this.color));
        System.out.println("===========================");
    }

    @Override
    public void drawEllipse(double left, double top, double width, double height) {
        System.out.println("===========================");
        System.out.println("DRAW ELLIPSE: ");
        System.out.println("  LEFT   : " + left);
        System.out.println("  TOP    : " + top);
        System.out.println("  WIDTH  : " + width);
        System.out.println("  HEIGHT : " + height);
        System.out.println("  COLOR  : " + Color.toString(this.color));
        System.out.println("===========================");
    }
}
