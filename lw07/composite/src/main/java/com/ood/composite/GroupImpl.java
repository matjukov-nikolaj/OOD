package com.ood.composite;

import com.ood.canvas.Canvas;
import com.ood.shapes.Rect;
import com.ood.shapes.Shape;
import com.ood.shapes.Style;

import java.util.ArrayList;
import java.util.List;

public class GroupImpl implements Group {

    private List<Shape> shapes;

    private Style outlineStyle;

    private Style fillStyle;

    private float lineThickness;

    public GroupImpl(Style outlineStyle, Style fillStyle, float lineThickness) {
        this.fillStyle = fillStyle;
        this.outlineStyle = outlineStyle;
        this.lineThickness = lineThickness;
        this.shapes = new ArrayList<>();
    }

    @Override
    public Rect<Float> getFrame() {
        Rect<Float> frame = new Rect<>(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
        float maxRight = 0;
        float maxBottom = 0;

        for (Shape shape: shapes) {
            Rect<Float> shapeFrame = shape.getFrame();
            frame.setLeft(Math.min(shapeFrame.getLeft(), frame.getLeft()));
            frame.setTop(Math.min(shapeFrame.getTop(), frame.getTop()));
            maxRight = Math.max(shapeFrame.getWidth() + shapeFrame.getLeft(), maxRight);
            maxBottom = Math.max(shapeFrame.getHeight() + shapeFrame.getTop(), maxBottom);
        }

        frame.setWidth(maxRight - frame.getLeft());
        frame.setHeight(maxBottom - frame.getTop());
        return frame;
    }

    public void setFrame(Rect<Float> frame) {
        Rect<Float> oldFrame = getFrame();

        float ratioX = frame.getWidth() / oldFrame.getWidth();
        float ratioY = frame.getHeight() / oldFrame.getHeight();

        for (Shape shape: shapes) {
            Rect<Float> shapeFrame = shape.getFrame();

            float paddingX = shapeFrame.getLeft() - oldFrame.getLeft();
            float paddingY = shapeFrame.getTop() - oldFrame.getTop();

            shapeFrame.setLeft(frame.getLeft() + paddingX * ratioX);
            shapeFrame.setTop(frame.getTop() + paddingY * ratioY);
            shapeFrame.setWidth(shapeFrame.getWidth() * ratioX);
            shapeFrame.setHeight(shapeFrame.getHeight() * ratioY);
        }
    }

    @Override
    public Style getOutlineStyle() {
        boolean areStylesEquals = true;
        for (Shape shape: shapes) {
            if (!shape.getOutlineStyle().equals(outlineStyle)) {
                areStylesEquals = false;
            }
        }
        return areStylesEquals ? outlineStyle : null;
    }

    @Override
    public void setOutlineStyle(Style outlineStyle) {
        this.outlineStyle = outlineStyle;
        for (Shape shape: shapes) {
            shape.setOutlineStyle(this.outlineStyle);
        }
    }

    @Override
    public Style getFillStyle() {
        boolean areStylesEquals = true;
        for (Shape shape: shapes) {
            if (!shape.getFillStyle().equals(fillStyle)) {
                areStylesEquals = false;
            }
        }
        return areStylesEquals ? this.fillStyle : null;
    }

    @Override
    public void setFillStyle(Style fillStyle) {
        this.fillStyle = fillStyle;
        for (Shape shape: shapes) {
            shape.setFillStyle(this.fillStyle);
        }
    }

    @Override
    public double getLineThickness() {
        boolean areThicknessEquals = true;
        for (Shape shape: shapes) {
            if (shape.getLineThickness() != this.lineThickness) {
                areThicknessEquals = false;
            }
        }
        return areThicknessEquals ? this.lineThickness : Double.NaN;
    }

    @Override
    public void setLineThickness(float lineThickness) {
        this.lineThickness = lineThickness;
        for (Shape shape: shapes) {
            shape.setLineThickness(this.lineThickness);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for (Shape shape: shapes) {
            shape.draw(canvas);
        }
    }

    @Override
    public int getShapesCount() {
        return shapes.size();
    }

    @Override
    public void insertShape(Shape shape, int position) {
        shapes.add(position, shape);
    }

    @Override
    public Shape getShapeAtIndex(int index) {
        return shapes.get(index);
    }

    @Override
    public void removeShapeAtIndex(int index) {
        shapes.remove(index);
    }

    public List<Shape> getShapes() {
        return shapes;
    }
}
