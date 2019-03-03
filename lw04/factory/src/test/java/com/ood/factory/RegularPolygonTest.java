package com.ood.factory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class RegularPolygonTest {

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
        Point center  = new Point(0, 0);
        double radius = 1;
        double vertexCount = 6;
        RegularPolygon ellipse = new RegularPolygon(center, radius, vertexCount, Color.RED);
        Canvas canvas = new CanvasImpl();
        ellipse.draw(canvas);
        String expectedResult = "===========================\r\n" +
                "DRAW ELLIPSE: \r\n" +
                "  LEFT   : " + (center.x - horizontalRadius) + "\r\n" +
                "  TOP    : " + (center.y - verticalRadius) + "\r\n" +
                "  WIDTH  : " + horizontalRadius + "\r\n" +
                "  HEIGHT : " + verticalRadius + "\r\n" +
                "  COLOR: " + Color.RED.toString() + "\r\n" +
                "===========================\r\n";
        Assert.assertEquals(expectedResult, this.output.toString());
    }
}