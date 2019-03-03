package com.ood.factory;

import com.ood.exceptions.WrongAmountException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

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
        double radius = 3;
        int vertexCount = 6;
        try {
            RegularPolygon ellipse = new RegularPolygon(center, vertexCount, radius, Color.RED);

            Canvas canvas = new CanvasImpl();
            ellipse.draw(canvas);
            List<Point> points = new ArrayList<>();

            double angles = 2 * Math.PI / vertexCount;

            Point prevPoint = new Point(center.x + radius * Math.cos(0), center.y);
            points.add(prevPoint);
            for (int i = 1; i <= vertexCount; ++i) {
                Point tempPoint = new Point(radius * Math.cos(angles * i), radius * Math.sin(angles * i));
                Point point = new Point(center.x + tempPoint.x, center.y + tempPoint.y );
                points.add(point);
                prevPoint = point;
            }

            String expectedResult = new String();
            for (int i = 0; i < vertexCount; ++i) {
                Point point1 = points.get(i);
                Point point2 = points.get(i + 1);
                expectedResult += "===========================\r\n" +
                        "DRAW LINE: \r\n" +
                        "  FROM : (" + point1.x + ", " + point1.y + ")\r\n" +
                        "  TO   : (" + point2.x + ", " + point2.y + ")\r\n" +
                        "  COLOR: " + Color.RED.toString() + "\r\n" +
                        "===========================\r\n";
            }

            Assert.assertEquals(expectedResult, this.output.toString());
        } catch (WrongAmountException e) {
            System.out.println(e.getMessage());
        }
    }

}