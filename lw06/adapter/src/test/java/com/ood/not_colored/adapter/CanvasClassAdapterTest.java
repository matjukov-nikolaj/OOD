package com.ood.not_colored.adapter;

import com.ood.not_colored.shape_drawing_lib.CanvasPainter;
import com.ood.not_colored.shape_drawing_lib.Point;
import com.ood.not_colored.shape_drawing_lib.Rectangle;
import com.ood.not_colored.shape_drawing_lib.Triangle;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CanvasClassAdapterTest {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream old = System.out;

    private CanvasClassAdapter adapter;

    @Before
    public void setUp() {
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);
        this.adapter = new CanvasClassAdapter(printStream);
    }

    @After
    public void tearDown() {
        System.out.flush();
        System.setOut(old);
    }

    @Test
    public void canDrawLine() {
        this.output.reset();
        this.adapter.moveTo(0, 0);
        this.adapter.lineTo(1, 1);
        String expectedResult = "<draw>\r\n" +
                "  <line fromX=\"0\" fromY=\"0\" toX=\"1\" toY=\"1\"/>\r\n" +
                "</draw>\r\n";
        Assert.assertEquals(expectedResult, this.output.toString());
    }

    @Test
    public void canDrawTriangle() {
        this.output.reset();
        CanvasPainter canvasPainter = new CanvasPainter(this.adapter);
        Triangle triangle = new Triangle(new Point(0, 0), new Point(0, 1), new Point(1, 1));
        canvasPainter.draw(triangle);
        String expectedResult = "<draw>\r\n" +
                "  <line fromX=\"0\" fromY=\"0\" toX=\"0\" toY=\"1\"/>\r\n" +
                "</draw>\r\n" +
                "<draw>\r\n" +
                "  <line fromX=\"0\" fromY=\"1\" toX=\"1\" toY=\"1\"/>\r\n" +
                "</draw>\r\n" +
                "<draw>\r\n" +
                "  <line fromX=\"1\" fromY=\"1\" toX=\"0\" toY=\"0\"/>\r\n" +
                "</draw>\r\n";
        Assert.assertEquals(expectedResult, this.output.toString());
    }

    @Test
    public void canDrawRectangle() {
        this.output.reset();
        CanvasPainter canvasPainter = new CanvasPainter(this.adapter);
        Rectangle rectangle = new Rectangle(new Point(0, 0), 2, 1);
        canvasPainter.draw(rectangle);
        String expectedResult = "<draw>\r\n" +
                "  <line fromX=\"0\" fromY=\"0\" toX=\"2\" toY=\"0\"/>\r\n" +
                "</draw>\r\n" +
                "<draw>\r\n" +
                "  <line fromX=\"2\" fromY=\"0\" toX=\"2\" toY=\"1\"/>\r\n" +
                "</draw>\r\n" +
                "<draw>\r\n" +
                "  <line fromX=\"2\" fromY=\"1\" toX=\"0\" toY=\"0\"/>\r\n" +
                "</draw>\r\n" +
                "<draw>\r\n" +
                "  <line fromX=\"0\" fromY=\"0\" toX=\"0\" toY=\"0\"/>\r\n" +
                "</draw>\r\n";
        Assert.assertEquals(expectedResult, this.output.toString());
    }
}