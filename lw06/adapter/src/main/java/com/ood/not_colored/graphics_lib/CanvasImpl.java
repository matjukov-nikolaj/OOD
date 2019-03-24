package com.ood.not_colored.graphics_lib;

public class CanvasImpl implements Canvas {

    @Override
    public void lineTo(int x, int y) {
        System.out.println("Move to (" + x + " , " + y + ")");
    }

    @Override
    public void moveTo(int x, int y) {
        System.out.println("Line to (" + x + " , " + y + ")");
    }
}
