package com.ood.factory.shapefactory;

import com.ood.factory.canvas.Canvas;
import com.ood.factory.canvas.CanvasImpl;
import com.ood.factory.shape.Point;
import com.ood.factory.shape.Rectangle;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
    public void canDrawRectangle() {
        Point leftTop = new Point(0, 1);
        Point rightBottom = new Point(1,0);
        Rectangle rectangle = new Rectangle(leftTop, rightBottom, Color.RED);
        Canvas canvas = new CanvasImpl();
        rectangle.draw(canvas);
        Point rightTop = new Point(rightBottom.getX(), leftTop.getY());
        Point leftBottom = new Point(leftTop.getX(), rightBottom.getY());
        String expectedResult = "===========================\r\n" +
                "DRAW LINE: \r\n" +
                "  FROM : (" + leftTop.getX() + ", " + leftTop.getY() + ")\r\n" +
                "  TO   : (" + rightTop.getX() + ", " + rightTop.getY() + ")\r\n" +
                "  COLOR: " + Color.RED.toString() + "\r\n" +
                "===========================\r\n" +
                "===========================\r\n" +
                "DRAW LINE: \r\n" +
                "  FROM : (" + rightTop.getX() + ", " + rightTop.getY() + ")\r\n" +
                "  TO   : (" + rightBottom.getX() + ", " + rightBottom.getY() + ")\r\n" +
                "  COLOR: " + Color.RED.toString() + "\r\n" +
                "===========================\r\n" +
                "===========================\r\n" +
                "DRAW LINE: \r\n" +
                "  FROM : (" + rightBottom.getX() + ", " + rightBottom.getY() + ")\r\n" +
                "  TO   : (" + leftBottom.getX() + ", " + leftBottom.getY() + ")\r\n" +
                "  COLOR: " + Color.RED.toString() + "\r\n" +
                "===========================\r\n"+
                "===========================\r\n" +
                "DRAW LINE: \r\n" +
                "  FROM : (" + leftBottom.getX() + ", " + leftBottom.getY() + ")\r\n" +
                "  TO   : (" + leftTop.getX() + ", " + leftTop.getY() + ")\r\n" +
                "  COLOR: " + Color.RED.toString() + "\r\n" +
                "===========================\r\n";
        Assert.assertEquals(expectedResult, this.output.toString());
    }

}