package com.ood.commander.model;

public interface Image {

    String getPath();

    int getWidth();

    int getHeight();

    void setWidth(int width);

    void setHeight(int height);

    void resize(int width, int height) throws Exception;

}
