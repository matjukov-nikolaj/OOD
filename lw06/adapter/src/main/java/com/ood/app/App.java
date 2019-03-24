package com.ood.app;

import com.ood.colored.adapter.CanvasColoredAdaptor;
import com.ood.not_colored.adapter.CanvasClassAdapter;
import com.ood.not_colored.adapter.CanvasObjectAdapter;
import com.ood.not_colored.graphics_lib.Canvas;
import com.ood.not_colored.graphics_lib.CanvasImpl;
import com.ood.not_colored.modern_graphics_lib.ModernGraphicsRenderer;
import com.ood.not_colored.shape_drawing_lib.CanvasPainter;
import com.ood.not_colored.shape_drawing_lib.Point;
import com.ood.not_colored.shape_drawing_lib.Rectangle;
import com.ood.not_colored.shape_drawing_lib.Triangle;

import java.awt.*;

public class App {

    public static void paintPicture(CanvasPainter painter) {
        Triangle triangle = new Triangle(new Point(10, 15), new Point(100, 200), new Point(150,250));
        Rectangle rectangle = new Rectangle(new Point(30, 40), 18, 24);

        painter.draw(triangle);
        painter.draw(rectangle);
    }

    public static void paintPictureOnCanvas() {
        Canvas canvas = new CanvasImpl();
        CanvasPainter canvasPainter = new CanvasPainter(canvas);
        paintPicture(canvasPainter);
    }

    public static void paintPictureOnModernGraphicsRendererWithCanvasObjectAdapter() {
        ModernGraphicsRenderer modernGraphicsRenderer = new ModernGraphicsRenderer(System.out);
        // TODO: при помощи существующей функции PaintPicture() нарисовать картину на renderer
        CanvasObjectAdapter objectAdapter = new CanvasObjectAdapter(modernGraphicsRenderer);
        CanvasPainter canvasPainter = new CanvasPainter(objectAdapter);
        paintPicture(canvasPainter);
    }

    public static void paintPictureOnModernGraphicsRendererWithCanvasClassAdapter() {
        CanvasClassAdapter classAdapter = new CanvasClassAdapter(System.out);
        CanvasPainter canvasPainter = new CanvasPainter(classAdapter);
        paintPicture(canvasPainter);
    }

    public static void paintColorPicture( com.ood.colored.shape_drawing_lib.CanvasPainter painter) {
        com.ood.colored.shape_drawing_lib.Triangle triangle = new com.ood.colored.shape_drawing_lib.Triangle(new com.ood.colored.shape_drawing_lib.Point(10, 15),
                new com.ood.colored.shape_drawing_lib.Point(100, 200),
                new com.ood.colored.shape_drawing_lib.Point(150,250),
                Color.decode("#FFFFFF"));
        com.ood.colored.shape_drawing_lib.Rectangle rectangle = new com.ood.colored.shape_drawing_lib.Rectangle(
                new com.ood.colored.shape_drawing_lib.Point(30, 40),
                18,
                24,
                Color.decode("#DE5D83"));

        painter.draw(triangle);
        painter.draw(rectangle);
    }

    public static void paintPictureOnModernGraphicsRendererWithCanvasColoredAdapter() {
        CanvasColoredAdaptor coloredAdaptor = new CanvasColoredAdaptor(System.out);
        com.ood.colored.shape_drawing_lib.CanvasPainter canvasPainter = new com.ood.colored.shape_drawing_lib.CanvasPainter(coloredAdaptor);
        paintColorPicture(canvasPainter);

    }

}
