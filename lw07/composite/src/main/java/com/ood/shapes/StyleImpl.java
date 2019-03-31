package com.ood.shapes;

import java.awt.*;

public class StyleImpl implements Style {

    private boolean isEnabled;

    private Color color;

    public StyleImpl(boolean enable, Color color) {
        this.isEnabled = enable;
        this.color = color;

    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public void enable() {
        isEnabled = true;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Style that = (Style) o;
        return getColor().equals(that.getColor())
                && isEnabled() == that.isEnabled();
    }

    @Override
    public int hashCode() {
        return getColor().hashCode();
    }
}
