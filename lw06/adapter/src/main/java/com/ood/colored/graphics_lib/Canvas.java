package com.ood.colored.graphics_lib;

import java.awt.*;

public interface Canvas {

    void setColor(Color color);

    void moveTo(int x, int y);

    void lineTo(int x, int y);

}
