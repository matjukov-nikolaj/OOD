package com.ood.factory;

import java.util.List;

public class PictureDraft {

    private List<Shape> shapes;

    public int getShapeCount() {
        return this.shapes.size();
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    public Shape getShape(Shape shape) {
        for (Shape instance: this.shapes) {
            if (instance == shape) {
                return shape;
            }
        }
        return null;
    }

}
