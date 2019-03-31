package com.ood.shapes;

import com.ood.canvas.Canvas;

public class Triangle extends ShapeImpl {

    public Triangle(Rect<Float> frame, Style fillStyle, Style outlineStyle, float thickness) {
        super(frame, fillStyle, outlineStyle, thickness);
    }

    @Override
    public void drawBehavior(Canvas canvas) {
        Rect<Float> frame = getFrame();

        int[] xPoints = { (frame.getLeft()).intValue(), (int)(frame.getLeft() + frame.getWidth() / 2.0f), (int)(frame.getLeft() + frame.getWidth())};
        int[] yPoints = { (int) (frame.getTop() + frame.getHeight()), frame.getTop().intValue(), (int) (frame.getTop() + frame.getHeight())};

        canvas.moveTo(xPoints[0], yPoints[0]);
        canvas.lineTo(xPoints[1], yPoints[1]);
        canvas.lineTo(xPoints[2], yPoints[2]);
        canvas.lineTo(xPoints[0], yPoints[0]);

        canvas.fillPolygon(xPoints, yPoints, 3);
    }
}
