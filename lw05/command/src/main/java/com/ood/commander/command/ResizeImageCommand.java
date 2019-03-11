package com.ood.commander.command;

import com.ood.commander.model.Image;

public class ResizeImageCommand extends AbstractCommand {

    private int newWidth;

    private int newHeight;

    private int oldWidth;

    private int oldHeight;

    private Image image;

    public ResizeImageCommand(Image image, int oldWidth, int oldHeight, int newWidth, int newHeight) {
        this.image = image;
        this.newHeight = newHeight;
        this.newWidth = newWidth;
        this.oldHeight = oldHeight;
        this.oldWidth = oldWidth;
    }

    @Override
    protected void doExecute() {
        this.image.setWidth(this.newWidth);
        this.image.setHeight(this.newHeight);
    }

    @Override
    protected void doUnExecute() {
        this.image.setWidth(this.oldWidth);
        this.image.setHeight(this.oldHeight);
    }
}
