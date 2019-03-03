package com.ood.factory;

import com.ood.exceptions.UnknownShapeException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShapeFactoryImplTest {

    @Test
    public void can_create_rectangle() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        try {
            Shape shape = shapeFactory.createShape("rectangle 0 1 1 0 red");
            assertEquals(Rectangle.class, shape.getClass());
        } catch (UnknownShapeException e) {
            fail();
        }
    }

    @Test
    public void can_create_triangle() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        try {
            Shape shape = shapeFactory.createShape("triangle 0 0 1 1 1 0 green");
            assertEquals(Triangle.class, shape.getClass());
        } catch (UnknownShapeException e) {
            fail();
        }
    }

    @Test
    public void can_create_ellipse() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        try {
            Shape shape = shapeFactory.createShape("ellipse 0 0 6 3 black");
            assertEquals(Ellipse.class, shape.getClass());
        } catch (UnknownShapeException e) {
            fail();
        }
    }

    @Test
    public void can_create_polygon() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        try {
            Shape shape = shapeFactory.createShape("polygon 0 0 6 3 red");
            assertEquals(RegularPolygon.class, shape.getClass());
        } catch (UnknownShapeException e) {
            fail();
        }
    }

}