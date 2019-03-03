package com.ood.factory;

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
