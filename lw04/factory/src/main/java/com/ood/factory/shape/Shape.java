package com.ood.factory.shape;

import com.ood.factory.canvas.Canvas;
import com.ood.factory.shapefactory.Color;

public abstract class Shape {

    private Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public abstract void draw(Canvas canvas);

    Color getColor() {
        return this.color;
    }


}
