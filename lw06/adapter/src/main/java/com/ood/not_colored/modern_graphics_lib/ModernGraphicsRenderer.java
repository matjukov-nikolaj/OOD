package com.ood.not_colored.modern_graphics_lib;

import com.ood.exception.LogicErrorException;

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

    public void drawLine(Point start, Point end) throws LogicErrorException {
        if (!this.drawing) {
            throw new LogicErrorException("DrawLine is allowed between BeginDraw()/EndDraw() only.");
        }

        this.out.println("  <line fromX=\"" + start.getX() + "\" fromY=\"" + start.getY() + "\" toX=\"" + end.getX() + "\" toY=\"" + end.getY() + "\"/>");

    }

    public void endDraw() throws LogicErrorException {
        if (!this.drawing) {
            throw new LogicErrorException("Drawing has not been started.");
        }

        this.out.println("</draw>");
        this.drawing = false;
    }

}
