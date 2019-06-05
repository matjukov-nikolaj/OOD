package com.ood.shapes;

import com.ood.canvas.SimpleCanvas;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class EllipseTest {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream old = System.out;
    private Style simpleStyle = new StyleImpl(true, Color.decode("#000000"));
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
    public void canCreateEllipse() {
        Ellipse ellipse = new Ellipse(frame, simpleStyle, simpleStyle, 1f);
        Assert.assertEquals(Color.BLACK, ellipse.getFillStyle().getColor());
        Assert.assertEquals(Color.BLACK, ellipse.getOutlineStyle().getColor());
    }

    @Test
    public void hasDrawBehavior() {
        output.reset();
        Ellipse ellipse = new Ellipse(frame, simpleStyle, simpleStyle, 1f);
        ellipse.drawBehavior(new SimpleCanvas());
        String expectedOutput = "    Draw ellipse left=0.0 top=0.0 width=100.0 height=100.0\r\n";
        Assert.assertEquals(expectedOutput, output.toString());
    }

    @Test
    public void canDrawEllipse() {
        output.reset();
        Ellipse ellipse = new Ellipse(frame, simpleStyle, simpleStyle, 1f);
        ellipse.draw(new SimpleCanvas());
        String expectedOutput = "Begin fill\r\n" +
                "    Draw ellipse left=0.0 top=0.0 width=100.0 height=100.0\r\n" +
                "End fill\r\n";
        Assert.assertEquals(expectedOutput, output.toString());
    }
}