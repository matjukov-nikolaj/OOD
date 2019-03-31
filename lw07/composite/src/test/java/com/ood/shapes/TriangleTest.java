package com.ood.shapes;

import com.ood.canvas.SimpleCanvas;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class TriangleTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream old = System.out;
    private Style simpleStyle = new StyleImpl(true, Color.decode("#ffffff"));
    private Rect<Float> frame = new Rect<>(0f, 0f, 100f, 100f);

    @Before
    public void setUp() {
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);
    }

    @After
    public void tearDown() {
        System.out.flush();
        System.setOut(old);
    }

    @Test
    public void canCreateRectangle() {
        Triangle triangle = new Triangle(frame, simpleStyle, simpleStyle, 1f);
        assertEquals(Color.WHITE, triangle.getFillStyle().getColor());
        assertEquals(Color.WHITE, triangle.getOutlineStyle().getColor());
    }

    @Test
    public void hasDrawBehavior() {
        output.reset();
        Triangle triangle = new Triangle(frame, simpleStyle, simpleStyle, 1f);
        triangle.drawBehavior(new SimpleCanvas());
        String expectedOutput = "    Move to (0.0 , 100.0)\r\n" +
                "    Line to (50.0 , 0.0)\r\n" +
                "    Line to (100.0 , 100.0)\r\n" +
                "    Line to (0.0 , 100.0)\r\n" +
                "    Fill polygon which contains 3 points.\r\n";
        Assert.assertEquals(expectedOutput, output.toString());
    }

    @Test
    public void canDrawTriangle() {
        output.reset();
        Triangle triangle = new Triangle(frame, simpleStyle, simpleStyle, 1f);
        triangle.draw(new SimpleCanvas());
        String expectedOutput = "Begin fill\r\n" +
                "    Move to (0.0 , 100.0)\r\n" +
                "    Line to (50.0 , 0.0)\r\n" +
                "    Line to (100.0 , 100.0)\r\n" +
                "    Line to (0.0 , 100.0)\r\n" +
                "    Fill polygon which contains 3 points.\r\n" +
                "End fill\r\n";
        Assert.assertEquals(expectedOutput, output.toString());
    }

}