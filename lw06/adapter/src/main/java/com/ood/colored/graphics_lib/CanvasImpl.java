package com.ood.colored.graphics_lib;

import java.awt.*;

public class CanvasImpl implements Canvas {

    @Override
    public void lineTo(int x, int y) {
        System.out.println("Move to (" + x + " , " + y + ")");
    }

    @Override
    public void moveTo(int x, int y) {
        System.out.println("Line to (" + x + " , " + y + ")");
    }

    @Override
    public void setColor(Color color) {
        System.out.println("#" + color.getRGB());
    }
}
