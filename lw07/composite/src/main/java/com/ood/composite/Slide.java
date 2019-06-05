package com.ood.composite;

import com.ood.canvas.Drawable;
import com.ood.shapes.Shape;

import java.util.List;

public interface Slide extends Drawable {

    float getWidth();

    float getHeight();

    List<Shape> getShapes();

}
