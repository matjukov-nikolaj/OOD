package com.ood.colored.modern_graphics_lib;

import com.ood.exception.LogicErrorException;

import java.awt.*;
import java.io.PrintStream;

public class ModernGraphicsRenderer {

    private PrintStream out;

    private boolean drawing = false;

    public ModernGraphicsRenderer(PrintStream out) {
        this.out = out;
    }

    public void beginDraw() throws LogicErrorException {
        if (this.drawing) {
            throw new LogicErrorException("Drawing has already begun.");
        }

        this.out.println("<draw>");
        this.drawing = true;
    }

    public void drawLine(Point start, Point end, Color color) throws LogicErrorException {
        if (!this.drawing) {
            throw new LogicErrorException("DrawLine is allowed between BeginDraw()/EndDraw() only.");
        }

        this.out.println("  <line fromX=\"" + start.getX() + "\" fromY=\"" + start.getY() + "\" toX=\"" + end.getX() + "\" toY=\"" + end.getY() + "\">");
        this.out.println("      <color r=\"" + color.getRed() + " g=\"" + color.getGreen() + " b=\"" + color.getBlue() +" a=\""+ color.getAlpha() +" />");
        this.out.println("  </line>");
    }

    public void endDraw() throws LogicErrorException {
        if (!this.drawing) {
            throw new LogicErrorException("Drawing has not been started.");
        }

        this.out.println("</draw>");
        this.drawing = false;
    }

}
