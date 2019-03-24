package com.ood.commander.command;

import com.ood.commander.Constants;
import com.ood.commander.model.Image;
import com.ood.commander.model.ImageImpl;
import com.ood.commander.service.History;
import com.ood.commander.service.ImageControllerImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class ResizeImageCommandTest {

    private History history;
    private Image image;

    @Before
    public void setUp() {
        this.history = new History();
        this.image = new ImageImpl(Constants.EXISTING_IMAGE, Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, history, new ImageControllerImpl(Constants.IMAGE_DIRECTORY));
    }

    @Test
    public void canCreateResizeImageCommandAndExecute() {
        this.checkImageSize(Constants.IMAGE_SIZE,Constants.IMAGE_SIZE, image);
        ResizeImageCommand resizeImageCommand = new ResizeImageCommand(this.image, Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, Constants.BIG_IMAGE_SIZE, Constants.BIG_IMAGE_SIZE);
        try {
            this.history.addAndExecuteCommand(resizeImageCommand);
            this.checkImageSize(Constants.BIG_IMAGE_SIZE, Constants.BIG_IMAGE_SIZE, image);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void ResizeImageCommandCanExecuteAndThenUnExecute() {
        this.checkImageSize(Constants.IMAGE_SIZE,Constants.IMAGE_SIZE, image);
        ResizeImageCommand resizeImageCommand = new ResizeImageCommand(this.image, Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, Constants.BIG_IMAGE_SIZE, Constants.BIG_IMAGE_SIZE);
        try {
            this.history.addAndExecuteCommand(resizeImageCommand);
            this.checkImageSize(Constants.BIG_IMAGE_SIZE,Constants.BIG_IMAGE_SIZE, image);
            this.history.undo();
            this.checkImageSize(Constants.IMAGE_SIZE,Constants.IMAGE_SIZE, image);
        } catch (Exception e) {
            fail();
        }
    }

    private void checkImageSize(int width, int height, Image image) {
        Assert.assertEquals(width, image.getWidth());
        Assert.assertEquals(height, image.getHeight());
    }

}