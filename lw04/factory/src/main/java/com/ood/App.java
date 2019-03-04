package com.ood;

import com.ood.factory.canvas.Canvas;
import com.ood.factory.canvas.CanvasImpl;
import com.ood.factory.shapefactory.*;

public class App
{
    public static void main( String[] args )
    {
        try {
            ShapeFactory factory = new ShapeFactoryImpl();
            Designer designer = new DesignerImpl(factory);
            Canvas canvas = new CanvasImpl();

            Client client = new Client(canvas);
            Painter painter = new PainterImpl();

            client.getPicture(System.in, designer);
            client.drawPicture(painter);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}