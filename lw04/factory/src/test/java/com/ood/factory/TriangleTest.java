package com.ood.factory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

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
    public void can_draw_triangle() {
        Point vertex1 = new Point(0, 0);
        Point vertex2 = new Point(1,1);
        Point vertex3 = new Point(1, 0);
        Triangle triangle = new Triangle(vertex1, vertex2, vertex3, Color.RED);
        Canvas canvas = new CanvasImpl();
        triangle.draw(canvas);
        String expectedResult = "===========================\r\n" +
                "DRAW LINE: \r\n" +
                "  FROM : (" + vertex1.x + ", " + vertex1.y + ")\r\n" +
                "  TO   : (" + vertex2.x + ", " + vertex2.y + ")\r\n" +
                "  COLOR: " + Color.RED.toString() + "\r\n" +
                "===========================\r\n" +
                "===========================\r\n" +
                "DRAW LINE: \r\n" +
                "  FROM : (" + vertex2.x + ", " + vertex2.y + ")\r\n" +
                "  TO   : (" + vertex3.x + ", " + vertex3.y + ")\r\n" +
                "  COLOR: " + Color.RED.toString() + "\r\n" +
                "===========================\r\n" +
                "===========================\r\n" +
                "DRAW LINE: \r\n" +
                "  FROM : (" + vertex3.x + ", " + vertex3.y + ")\r\n" +
                "  TO   : (" + vertex1.x + ", " + vertex1.y + ")\r\n" +
                "  COLOR: " + Color.RED.toString() + "\r\n" +
                "===========================\r\n";
    }

}