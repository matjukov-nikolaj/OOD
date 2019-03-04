package com.ood.factory.shapefactory;


import com.ood.factory.canvas.Canvas;
import com.ood.factory.shape.Shape;

public class PainterImpl implements Painter {

    @Override
    public void drawPicture(PictureDraft draft, Canvas canvas) {

        for (Shape shape : draft.getShapes()) {
            shape.draw(canvas);
        }

    }
}
