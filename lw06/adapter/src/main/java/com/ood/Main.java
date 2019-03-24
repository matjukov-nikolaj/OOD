package com.ood;

import com.ood.app.App;

public class Main
{

    public static void main( String[] args )
    {
        App app = new App();
        printBock("ModernGraphicsRenderer with ObjectAdapter");
        app.paintPictureOnModernGraphicsRendererWithCanvasObjectAdapter();
        printBock("Canvas");
        app.paintPictureOnCanvas();
        printBock("ModernGraphicsRenderer with ClassAdapter");
        app.paintPictureOnModernGraphicsRendererWithCanvasClassAdapter();
        printBock("ModernGraphicsRenderer with ColoredAdapter");
        app.paintPictureOnModernGraphicsRendererWithCanvasColoredAdapter();
    }

    public static void printBock(String message) {
        System.out.println("----------------------------------------------------");
        System.out.println(message);
        System.out.println("----------------------------------------------------");
    }

}