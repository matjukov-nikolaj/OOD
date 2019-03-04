package com.ood.factory;


public class PainterImpl implements Painter {

    @Override
    public void drawPicture(PictureDraft draft, Canvas canvas) {

        for (Shape shape : draft.getShapes()) {
            shape.draw(canvas);
        }

    }
}
