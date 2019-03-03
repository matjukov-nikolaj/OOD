package com.ood.factory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class RectangleTest {

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
    public void can_draw_rectaingle() {
        Point leftTop = new Point(0, 1);
        Point rightBottom = new Point(1,0);
        Rectangle rectangle = new Rectangle(leftTop, rightBottom, Color.RED);
        Canvas canvas = new CanvasImpl();
        rectangle.draw(canvas);
        Point rightTop = new Point(rightBottom.x, leftTop.y);
        Point leftBottom = new Point(leftTop.x, rightBottom.y);
        String expectedResult = "===========================\r\n" +
                "DRAW LINE: \r\n" +
                "  FROM : (" + leftTop.x + ", " + leftTop.y + ")\r\n" +
                "  TO   : (" + rightTop.x + ", " + rightTop.y + ")\r\n" +
                "  COLOR: " + Color.RED.toString() + "\r\n" +
                "===========================\r\n" +
                "===========================\r\n" +
                "DRAW LINE: \r\n" +
                "  FROM : (" + rightTop.x + ", " + rightTop.y + ")\r\n" +
                "  TO   : (" + rightBottom.x + ", " + rightBottom.y + ")\r\n" +
                "  COLOR: " + Color.RED.toString() + "\r\n" +
                "===========================\r\n" +
                "===========================\r\n" +
                "DRAW LINE: \r\n" +
                "  FROM : (" + rightBottom.x + ", " + rightBottom.y + ")\r\n" +
                "  TO   : (" + leftBottom.x + ", " + leftBottom.y + ")\r\n" +
                "  COLOR: " + Color.RED.toString() + "\r\n" +
                "===========================\r\n"+
                "===========================\r\n" +
                "DRAW LINE: \r\n" +
                "  FROM : (" + leftBottom.x + ", " + leftBottom.y + ")\r\n" +
                "  TO   : (" + leftTop.x + ", " + leftTop.y + ")\r\n" +
                "  COLOR: " + Color.RED.toString() + "\r\n" +
                "===========================\r\n";
        Assert.assertEquals(expectedResult, this.output.toString());
    }

}