package com.ood.commander.model;

import com.ood.commander.service.Executor;
import com.ood.commander.command.ResizeImageCommand;
import com.ood.commander.service.ImageController;

import java.io.File;

public class ImageImpl implements Image {

    private String path;

    private int width;

    private int height;

    private Executor executor;

    private ImageController controller;

    public ImageImpl(String path, int width, int height, Executor executor, ImageController controller) {
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            throw new IllegalArgumentException("Incorrect file path.");
        }
        this.checkImageSize(width, height);
        this.path = path;
        this.width = width;
        this.height = height;
        this.executor = executor;
        this.controller = controller;
    }

    public ImageController getController() {
        return this.controller;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void resize(int newWidth, int newHeight) throws Exception {
        this.checkImageSize(newWidth, newHeight);
        this.executor.addAndExecuteCommand(new ResizeImageCommand(this, this.width, this.height, newWidth, newHeight));
    }

    private void checkImageSize(int width, int height) {
        if (width < 1 || width > 10000)
        {
            throw new IllegalArgumentException("Invalid image width.");
        }
        if (height < 1 || height > 10000)
        {
            throw new IllegalArgumentException("Invalid image height");
        }
    }
}
