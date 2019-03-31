package com.ood.shapes;

import com.ood.canvas.Canvas;

public class Ellipse extends ShapeImpl {

    public Ellipse(Rect<Float> frame, Style fillStyle, Style outlineStyle, float thickness) {
        super(frame, fillStyle, outlineStyle, thickness);
    }

    @Override
    public void drawBehavior(Canvas canvas) {
        Rect<Float> frame = getFrame();
        canvas.drawEllipse(frame.getLeft(), frame.getTop(), frame.getWidth(), frame.getHeight());
    }
}
