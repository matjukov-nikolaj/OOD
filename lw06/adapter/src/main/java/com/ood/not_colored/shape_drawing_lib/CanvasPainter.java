package com.ood.not_colored.shape_drawing_lib;

import com.ood.not_colored.graphics_lib.Canvas;

public class CanvasPainter {

    private Canvas canvas;

    public CanvasPainter(Canvas canvas) {
        this.canvas = canvas;
    }

    public void draw(CanvasDrawable drawable) {
        drawable.draw(this.canvas);
    }

}
