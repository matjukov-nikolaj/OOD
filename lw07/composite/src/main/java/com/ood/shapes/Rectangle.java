package com.ood.shapes;

import com.ood.canvas.Canvas;

public class Rectangle extends ShapeImpl {

    public Rectangle(Rect<Float> frame, Style fillStyle, Style outlineStyle, float thickness) {
        super(frame, fillStyle, outlineStyle, thickness);
    }

    @Override
    public void drawBehavior(Canvas canvas) {
        Rect<Float> frame = getFrame();

        int[] xPoints = { (frame.getLeft()).intValue(), (int)(frame.getLeft() + frame.getWidth()), (int)(frame.getLeft() + frame.getWidth()), (frame.getLeft()).intValue()};
        int[] yPoints = { frame.getTop().intValue(), frame.getTop().intValue(), (int) (frame.getTop() + frame.getHeight()), (int) (frame.getTop() + frame.getHeight())};

        canvas.moveTo(xPoints[0], yPoints[0]);
        canvas.lineTo(xPoints[1], yPoints[1]);
        canvas.lineTo(xPoints[2], yPoints[2]);
        canvas.lineTo(xPoints[3], yPoints[3]);
        canvas.lineTo(xPoints[0], yPoints[0]);

        canvas.fillPolygon(xPoints, yPoints, 4);
    }
}
