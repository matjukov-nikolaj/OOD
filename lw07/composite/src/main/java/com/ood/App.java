package com.ood;

import com.ood.canvas.Canvas;
import com.ood.canvas.CanvasImpl;
import com.ood.composite.*;
import com.ood.shapes.*;
import com.ood.shapes.Rectangle;

import javax.swing.*;
import java.awt.*;

public class App
{

    public static void createImageOnSlide(Slide slide) {
        Rect<Float> rectFrame = new Rect<>(5.0f, 5.0f, 100.0f, 100.0f);
        Rectangle rectangle = new Rectangle(rectFrame, new StyleImpl(true, Color.decode("#4261ff")), new StyleImpl(true, Color.decode("#a2baba")), 5.0f);

        Rect<Float> ellipseFrame = new Rect<>(150.0f, 10.0f, 100.0f, 100.0f);
        Ellipse ellipse = new Ellipse(ellipseFrame, new StyleImpl(true, Color.decode("#ff0000")), new StyleImpl(true, Color.decode("#dfcaae")), 10.0f);

        Rect<Float> triangleFrame = new Rect<>(300.0f, 15.0f, 100.0f, 100.0f);
        Triangle triangle = new Triangle(triangleFrame, new StyleImpl(true, Color.decode("#869f86")), new StyleImpl(true, Color.decode("#bcc4dd")), 15.0f);

        Rect<Float> rectFrameForGroup = new Rect<>(5.0f, 200.0f, 100.0f, 100.0f);
        Rectangle rectangleForGroup = new Rectangle(rectFrameForGroup, new StyleImpl(true, Color.decode("#4261ff")), new StyleImpl(true, Color.decode("#a2baba")), 5.0f);

        Rect<Float> ellipseFrameForGroup = new Rect<>(150.0f, 200.0f, 100.0f, 100.0f);
        Ellipse ellipseForGroup = new Ellipse(ellipseFrameForGroup, new StyleImpl(true, Color.decode("#ff0000")), new StyleImpl(true, Color.decode("#dfcaae")), 10.0f);

        Rect<Float> triangleFrameForGroup = new Rect<>(300.0f, 200.0f, 100.0f, 100.0f);
        Triangle triangleForGroup = new Triangle(triangleFrameForGroup, new StyleImpl(true, Color.decode("#869f86")), new StyleImpl(true, Color.decode("#bcc4dd")), 15.0f);
        GroupShape group = new GroupShapeImpl(new StyleImpl(true, Color.decode("#869f86")), new StyleImpl(true, Color.decode("#bcc4dd")), 10.0f);
        group.insertShape(rectangleForGroup, 0);
        group.insertShape(ellipseForGroup, 1);
        group.insertShape(triangleForGroup, 2);

        Rect<Float> groupFrame = new Rect<>(5.0f, 200.0f, 700.0f, 200.0f);
        group.setFrame(groupFrame);
        slide.getShapes().add(rectangle);
        slide.getShapes().add(triangle);
        slide.getShapes().add(ellipse);
        slide.getShapes().add(group);
    }

    public static void main( String[] args )
    {
        try {
            JFrame window = new JFrame("Canvas");
            window.setBounds(0, 0, 800, 600);
            window.setVisible(true);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel contentPane = new JPanel() {
                Graphics2D graphics2D;

                public void paint(Graphics g) {
                    super.paintComponent(g);
                    graphics2D = (Graphics2D) g;
                    Canvas canvas = new CanvasImpl(graphics2D);
                    Slide slide = new SlideImpl(800.0f, 600.0f);
                    createImageOnSlide(slide);
                    slide.draw(canvas);
                }
            };
            window.setContentPane(contentPane);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}