package com.ood.composite;

import com.ood.canvas.SimpleCanvas;
import com.ood.shapes.*;
import com.ood.shapes.Rectangle;
import com.ood.shapes.Shape;
import com.ood.canvas.Canvas;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class GroupImplTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream old = System.out;
    private Style simpleStyle = new StyleImpl(true, Color.decode("#000000"));
    private Style greenStyle = new StyleImpl(true, Color.decode("#00ff00"));
    private Rect<Float> simpleFrame = new Rect<>(0f, 0f, 100f, 100f);
    private Rect<Float> bigFrame = new Rect<>(0f, 0f, 500f, 200f);

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
    public void canCreateGroup() {
        Group group = getSimpleGroup();

        assertEquals(3, group.getShapesCount());
        assertEquals(simpleFrame, group.getFrame());
    }

    @Test
    public void canChangeGroupFrame() {
        Group group = getSimpleGroup();

        group.setFrame(bigFrame);
        assertEquals(group.getFrame(), new Rect<>(0f, 0f, 500f, 200f));
    }

    @Test
    public void canSetOutlineStyle() {
        Group group = getSimpleGroup();
        group.setOutlineStyle(new StyleImpl(true, Color.decode("#ff0000")));
        for(Shape shape: group.getShapes()) {
            assertEquals(Color.RED, shape.getOutlineStyle().getColor());
        }
    }

    @Test
    public void canSetFillStyle() {
        Group group = getSimpleGroup();
        group.setFillStyle(new StyleImpl(true, Color.decode("#00FF00")));
        for(Shape shape: group.getShapes()) {
            assertEquals(Color.GREEN, shape.getFillStyle().getColor());
        }
    }

    @Test
    public void canGetLineThicknessIfAllLineThicknessAreEquals() {
        Group group = getSimpleGroup();
        assertEquals(0f, group.getLineThickness(), 0f);
    }

    @Test
    public void ifOneOfFillStyleIsDifferentReturnNull() {
        Group group = getGroup();
        assertNull(group.getFillStyle());
    }

    @Test
    public void ifOneOfOutlineStyleIsDifferentReturnNull() {
        Group group = getGroup();
        assertNull(group.getOutlineStyle());
    }

    @Test
    public void canInsertShape() {
        Group group = getGroup();
        Rect<Float> triangleFrameForGroup = new Rect<>(0.0f, 0.0f, 100.0f, 100.0f);
        Triangle triangleForGroup = new Triangle(triangleFrameForGroup, greenStyle, greenStyle, 0.0f);
        group.insertShape(triangleForGroup, 0);
        assertEquals(5, group.getShapesCount());
        assertSame(triangleForGroup, group.getShapeAtIndex(0));
    }

    @Test
    public void canRemoveShape() {
        Group group = getGroup();
        Rect<Float> triangleFrameForGroup = new Rect<>(0.0f, 0.0f, 100.0f, 100.0f);
        Triangle triangleForGroup = new Triangle(triangleFrameForGroup, greenStyle, greenStyle, 0.0f);
        group.insertShape(triangleForGroup, 0);
        assertEquals(5, group.getShapesCount());
        assertSame(triangleForGroup, group.getShapeAtIndex(0));
        group.removeShapeAtIndex(0);
        assertEquals(4, group.getShapesCount());
        assertNotSame(triangleForGroup, group.getShapeAtIndex(0));
    }

    @Test
    public void canDrawShape() {
        output.reset();
        Group group = getGroup();
        Canvas simpleCanvas = new SimpleCanvas();
        group.draw(simpleCanvas);
        String expectedOutput = "Begin fill\r\n" +
                "    Move to (0.0 , 100.0)\r\n" +
                "    Line to (50.0 , 0.0)\r\n" +
                "    Line to (100.0 , 100.0)\r\n" +
                "    Line to (0.0 , 100.0)\r\n" +
                "    Fill polygon which contains 3 points.\r\n" +
                "End fill\r\n" +
                "Begin fill\r\n" +
                "    Move to (0.0 , 0.0)\r\n" +
                "    Line to (100.0 , 0.0)\r\n" +
                "    Line to (100.0 , 100.0)\r\n" +
                "    Line to (0.0 , 100.0)\r\n" +
                "    Line to (0.0 , 0.0)\r\n" +
                "    Fill polygon which contains 4 points.\r\n" +
                "End fill\r\n" +
                "Begin fill\r\n" +
                "    Draw ellipse left=0.0 top=0.0 width=100.0 height=100.0\r\n" +
                "End fill\r\n" +
                "Begin fill\r\n" +
                "    Move to (0.0 , 100.0)\r\n" +
                "    Line to (50.0 , 0.0)\r\n" +
                "    Line to (100.0 , 100.0)\r\n" +
                "    Line to (0.0 , 100.0)\r\n" +
                "    Fill polygon which contains 3 points.\r\n" +
                "End fill\r\n";
        assertEquals(expectedOutput, output.toString());
    }

    @Test
    public void ifChangedGroupFrameShapesWillBePaintedInAnotherFrame() {
        output.reset();
        Group group = getGroup();
        group.setFrame(bigFrame);
        Canvas simpleCanvas = new SimpleCanvas();
        group.draw(simpleCanvas);
        String expectedOutput = "Begin fill\r\n" +
                "    Move to (0.0 , 200.0)\r\n" +
                "    Line to (250.0 , 0.0)\r\n" +
                "    Line to (500.0 , 200.0)\r\n" +
                "    Line to (0.0 , 200.0)\r\n" +
                "    Fill polygon which contains 3 points.\r\n" +
                "End fill\r\n" +
                "Begin fill\r\n" +
                "    Move to (0.0 , 0.0)\r\n" +
                "    Line to (500.0 , 0.0)\r\n" +
                "    Line to (500.0 , 200.0)\r\n" +
                "    Line to (0.0 , 200.0)\r\n" +
                "    Line to (0.0 , 0.0)\r\n" +
                "    Fill polygon which contains 4 points.\r\n" +
                "End fill\r\n" +
                "Begin fill\r\n" +
                "    Draw ellipse left=0.0 top=0.0 width=500.0 height=200.0\r\n" +
                "End fill\r\n" +
                "Begin fill\r\n" +
                "    Move to (0.0 , 200.0)\r\n" +
                "    Line to (250.0 , 0.0)\r\n" +
                "    Line to (500.0 , 200.0)\r\n" +
                "    Line to (0.0 , 200.0)\r\n" +
                "    Fill polygon which contains 3 points.\r\n" +
                "End fill\r\n";
        assertEquals(expectedOutput, output.toString());
    }

    private Group getGroup() {
        Group group = getSimpleGroup();
        Rect<Float> triangleFrameForGroup = new Rect<>(0.0f, 0.0f, 100.0f, 100.0f);
        Triangle triangleForGroup = new Triangle(triangleFrameForGroup, greenStyle, greenStyle, 0.0f);
        group.insertShape(triangleForGroup, 0);
        return group;
    }

    private Group getSimpleGroup() {
        Rect<Float> rectFrameForGroup = new Rect<>(0.0f, 0.0f, 100.0f, 100.0f);
        com.ood.shapes.Rectangle rectangleForGroup = new Rectangle(rectFrameForGroup, simpleStyle, simpleStyle, 0.0f);

        Rect<Float> ellipseFrameForGroup = new Rect<>(0.0f, 0.0f, 100.0f, 100.0f);
        Ellipse ellipseForGroup = new Ellipse(ellipseFrameForGroup, simpleStyle, simpleStyle, 0.0f);

        Rect<Float> triangleFrameForGroup = new Rect<>(0.0f, 0.0f, 100.0f, 100.0f);
        Triangle triangleForGroup = new Triangle(triangleFrameForGroup, simpleStyle, simpleStyle, 0.0f);

        Group group = new GroupImpl(simpleStyle, simpleStyle, 0.0f);
        group.insertShape(rectangleForGroup, 0);
        group.insertShape(ellipseForGroup, 1);
        group.insertShape(triangleForGroup, 2);
        return group;
    }

}