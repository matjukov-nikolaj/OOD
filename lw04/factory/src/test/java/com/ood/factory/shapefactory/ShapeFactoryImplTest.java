package com.ood.factory.shapefactory;

import com.ood.exceptions.IncorrectNumberOfArguments;
import com.ood.exceptions.UnknownColorException;
import com.ood.exceptions.UnknownShapeException;
import com.ood.factory.shape.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShapeFactoryImplTest {

    @Test
    public void canCreateRectangle() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        try {
            Shape shape = shapeFactory.createShape("rectangle 0 1 1 0 red");
            assertEquals(Rectangle.class, shape.getClass());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void canCreateTriangle() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        try {
            Shape shape = shapeFactory.createShape("triangle 0 0 1 1 1 0 green");
            assertEquals(Triangle.class, shape.getClass());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void canCreateEllipse() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        try {
            Shape shape = shapeFactory.createShape("ellipse 0 0 6 3 black");
            assertEquals(Ellipse.class, shape.getClass());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void canCreatePolygon() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        try {
            Shape shape = shapeFactory.createShape("polygon 0 0 6 3 red");
            assertEquals(RegularPolygon.class, shape.getClass());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void throwExceptionIfUserWriteIncorrectShape() {
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
    public void throwNumberFormatExceptionWhenUserEnteredIncorrectNumber() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        try {
            Shape shape = shapeFactory.createShape("rectangle 0 a0 6 3 red");
            fail();
        } catch (Exception e) {
            assertEquals(NumberFormatException.class, e.getClass());
        }
    }

    @Test
    public void throwNullPointerExceptionWhenUserEnteredIncorrectColor() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        try {
            Shape shape = shapeFactory.createShape("rectangle 0 0 6 3 error");
            fail();
        } catch (Exception e) {
            assertEquals(UnknownColorException.class, e.getClass());
        }
    }


    @Test
    public void throwIncorrectNumberOfArgumentsExceptionWhenTryingToEnterAnIncorrectNumberOfArguments() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        try {
            Shape shape = shapeFactory.createShape("rectangle 0 0 6 3 red error");
            fail();
        } catch (Exception e) {
            assertEquals(IncorrectNumberOfArguments.class, e.getClass());
        }
    }

}