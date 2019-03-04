package com.ood.factory;

import com.ood.exceptions.UnknownColorException;
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
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void can_create_triangle() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        try {
            Shape shape = shapeFactory.createShape("triangle 0 0 1 1 1 0 green");
            assertEquals(Triangle.class, shape.getClass());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void can_create_ellipse() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        try {
            Shape shape = shapeFactory.createShape("ellipse 0 0 6 3 black");
            assertEquals(Ellipse.class, shape.getClass());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void can_create_polygon() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        try {
            Shape shape = shapeFactory.createShape("polygon 0 0 6 3 red");
            assertEquals(RegularPolygon.class, shape.getClass());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void throw_exception_if_user_write_incorrect_shape() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        try {
            Shape shape = shapeFactory.createShape("error 0 0 6 3 red");
            fail();
        } catch (Exception e) {
            assertEquals("Unknown Shape.", e.getMessage());
            assertEquals(UnknownShapeException.class, e.getClass());
        }
    }

    @Test
    public void throw_number_format_exception_when_user_entered_incorrect_number() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        try {
            Shape shape = shapeFactory.createShape("rectangle 0 a0 6 3 red");
            fail();
        } catch (Exception e) {
            assertEquals(NumberFormatException.class, e.getClass());
        }
    }

    @Test
    public void throw_null_pointer_exception_when_user_entered_incorrect_color() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        try {
            Shape shape = shapeFactory.createShape("rectangle 0 0 6 3 error");
            fail();
        } catch (Exception e) {
            assertEquals(UnknownColorException.class, e.getClass());
        }
    }

}