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

public class RectangleTest {

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
        Rectangle rectangle = new Rectangle(frame, simpleStyle, simpleStyle, 1f);
        assertEquals(Color.WHITE, rectangle.getFillStyle().getColor());
        assertEquals(Color.WHITE, rectangle.getOutlineStyle().getColor());
    }

    @Test
    public void hasDrawBehavior() {
        output.reset();
        Rectangle rectangle = new Rectangle(frame, simpleStyle, simpleStyle, 1f);
        rectangle.drawBehavior(new SimpleCanvas());
        String expectedOutput = "    Move to (0.0 , 0.0)\r\n" +
                "    Line to (100.0 , 0.0)\r\n" +
                "    Line to (100.0 , 100.0)\r\n" +
                "    Line to (0.0 , 100.0)\r\n" +
                "    Line to (0.0 , 0.0)\r\n" +
                "    Fill polygon which contains 4 points.\r\n";
        Assert.assertEquals(expectedOutput, output.toString());
    }

    @Test
    public void canDrawRectangle() {
        output.reset();
        Rectangle rectangle = new Rectangle(frame, simpleStyle, simpleStyle, 1f);
        rectangle.draw(new SimpleCanvas());
        String expectedOutput = "Begin fill\r\n" +
                "    Move to (0.0 , 0.0)\r\n" +
                "    Line to (100.0 , 0.0)\r\n" +
                "    Line to (100.0 , 100.0)\r\n" +
                "    Line to (0.0 , 100.0)\r\n" +
                "    Line to (0.0 , 0.0)\r\n" +
                "    Fill polygon which contains 4 points.\r\n" +
                "End fill\r\n";
        Assert.assertEquals(expectedOutput, output.toString());
    }

}