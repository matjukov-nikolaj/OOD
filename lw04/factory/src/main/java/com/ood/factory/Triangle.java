package com.ood.factory;

public class Triangle extends Shape {

    public Triangle(Color color) {
        super(color);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setColor(this.getColor());
        //draw lines
    }
}
