package com.ood.composite;

import com.ood.canvas.Canvas;
import com.ood.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class SlideImpl implements Slide {

    private float width;

    private float height;

    private List<Shape> shapes;

    public SlideImpl(float width, float height) {
        this.width = width;
        this.height = height;
        this.shapes = new ArrayList<>();
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public List<Shape> getShapes() {
        return shapes;
    }

    @Override
    public void draw(Canvas canvas) {
        for (Shape shape: shapes) {
            shape.draw(canvas);
        }
    }
}
