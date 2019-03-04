package com.ood.factory.shapefactory;

import com.ood.factory.canvas.Canvas;
import com.ood.factory.canvas.CanvasImpl;
import com.ood.factory.shape.Ellipse;
import com.ood.factory.shape.Point;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class EllipseTest {

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
    public void can_draw_ellipse() {
        Point center  = new Point(0, 1);
        double horizontalRadius = 3;
        double verticalRadius = 1;
        Ellipse ellipse = new Ellipse(center, horizontalRadius, verticalRadius, Color.RED);
        Canvas canvas = new CanvasImpl();
        ellipse.draw(canvas);
        String expectedResult = "===========================\r\n" +
                "DRAW ELLIPSE: \r\n" +
                "  LEFT   : " + (center.x - horizontalRadius) + "\r\n" +
                "  TOP    : " + (center.y - verticalRadius) + "\r\n" +
                "  WIDTH  : " + horizontalRadius + "\r\n" +
                "  HEIGHT : " + verticalRadius + "\r\n" +
                "  COLOR  : " + Color.RED.toString() + "\r\n" +
                "===========================\r\n";
        Assert.assertEquals(expectedResult, this.output.toString());
    }

}