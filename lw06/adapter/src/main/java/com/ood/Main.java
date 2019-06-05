package com.ood;

import static com.ood.app.App.*;

public class Main
{

    public static void main( String[] args )
    {
        printBock("ModernGraphicsRenderer with ObjectAdapter");
        paintPictureOnModernGraphicsRendererWithCanvasObjectAdapter();
        printBock("Canvas");
        paintPictureOnCanvas();
        printBock("ModernGraphicsRenderer with ClassAdapter");
        paintPictureOnModernGraphicsRendererWithCanvasClassAdapter();
        printBock("ModernGraphicsRenderer with ColoredAdapter");
        paintPictureOnModernGraphicsRendererWithCanvasColoredAdapter();
    }

    public static void printBock(String message) {
        System.out.println("----------------------------------------------------");
        System.out.println(message);
        System.out.println("----------------------------------------------------");
    }

}