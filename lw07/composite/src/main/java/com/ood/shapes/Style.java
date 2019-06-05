package com.ood.shapes;

import java.awt.*;

public interface Style {

    boolean isEnabled();

    void enable();

    Color getColor();

    void setColor(Color color);

}
