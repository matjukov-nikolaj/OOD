package com.ood.factory.shapefactory;

import com.ood.factory.canvas.Canvas;
import com.ood.factory.canvas.CanvasImpl;
import com.ood.factory.shape.Point;
import com.ood.factory.shape.Triangle;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TriangleTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream old = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @After
    public void tearDown() {
        System.out.flush();
        System.setOut(old);
    }

    @Test
    public void canDrawTriangle() {
        Point vertex1 = new Point(0, 0);
        Point vertex2 = new Point(1,1);
        Point vertex3 = new Point(1, 0);
        Triangle triangle = new Triangle(vertex1, vertex2, vertex3, Color.RED);
        Canvas canvas = new CanvasImpl();
        triangle.draw(canvas);
        String expectedResult = "===========================\r\n" +
                "DRAW LINE: \r\n" +
                "  FROM : (" + vertex1.getX() + ", " + vertex1.getY() + ")\r\n" +
                "  TO   : (" + vertex2.getX() + ", " + vertex2.getY() + ")\r\n" +
                "  COLOR: " + Color.RED.toString() + "\r\n" +
                "===========================\r\n" +
                "===========================\r\n" +
                "DRAW LINE: \r\n" +
                "  FROM : (" + vertex2.getX() + ", " + vertex2.getY() + ")\r\n" +
                "  TO   : (" + vertex3.getX() + ", " + vertex3.getY() + ")\r\n" +
                "  COLOR: " + Color.RED.toString() + "\r\n" +
                "===========================\r\n" +
                "===========================\r\n" +
                "DRAW LINE: \r\n" +
                "  FROM : (" + vertex3.getX() + ", " + vertex3.getY() + ")\r\n" +
                "  TO   : (" + vertex1.getX() + ", " + vertex1.getY() + ")\r\n" +
                "  COLOR: " + Color.RED.toString() + "\r\n" +
                "===========================\r\n";
        Assert.assertEquals(expectedResult, this.output.toString());
    }

}