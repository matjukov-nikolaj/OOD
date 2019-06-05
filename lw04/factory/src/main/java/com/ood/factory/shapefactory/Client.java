package com.ood.factory.shapefactory;

import com.ood.factory.canvas.Canvas;

import java.io.InputStream;

public class Client {

    private PictureDraft draft;

    private Canvas canvas;

    public Client(Canvas canvas) {
        this.canvas = canvas;
    }

    public void getPicture(InputStream in, Designer designer) {
        this.draft = designer.createDraft(in);
    }

    public void drawPicture(Painter painter) {
        painter.drawPicture(this.draft, this.canvas);
    }

}
