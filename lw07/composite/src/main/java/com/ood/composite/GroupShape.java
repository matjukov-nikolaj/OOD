package com.ood.composite;

import com.ood.shapes.Shape;

import java.util.List;

public interface GroupShape extends Shape {

    int getShapesCount();

    void insertShape(Shape shape, int position);

    Shape getShapeAtIndex(int index);

    void removeShapeAtIndex(int index);

    List<Shape> getShapes();

}
