package com.ood.commander.model;

import com.ood.commander.Constants;
import com.ood.commander.service.History;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImageImplTest {

    private History history;

    @Before
    public void setUp() {
        this.history = new History();
    }

    @Test
    public void canCreateImage() {
        Image image = new ImageImpl(Constants.EXISTING_IMAGE, Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, this.history);
        this.checkImageSize(Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, image);
        Assert.assertEquals(Constants.EXISTING_IMAGE, image.getPath());
    }

    @Test
    public void canResizeImage() {
        Image image = new ImageImpl(Constants.EXISTING_IMAGE, Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, this.history);
        try {
            image.resize(Constants.BIG_IMAGE_SIZE, Constants.BIG_IMAGE_SIZE);
            this.checkImageSize(Constants.BIG_IMAGE_SIZE, Constants.BIG_IMAGE_SIZE, image);
        } catch (Exception e) {
            fail();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentIfImageFileDoesNotExist() {
        Image image = new ImageImpl(Constants.NON_EXISTING_IMAGE, Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, this.history);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentIfFileIsDirectory() {
        Image image = new ImageImpl(Constants.RESOURCES_DIRECTORY, Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, this.history);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionIfTryingToSetIncorrectWidth1() {
        Image image = new ImageImpl(Constants.EXISTING_IMAGE, Constants.INCORRECT_IMAGE_SIZE,  Constants.IMAGE_SIZE, this.history);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionIfTryingToSetIncorrectWidth2() {
        Image image = new ImageImpl(Constants.EXISTING_IMAGE, Constants.INCORRECT_BIG_IMAGE_SIZE,  Constants.IMAGE_SIZE, this.history);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionIfTryingToSetIncorrectHeight1() {
        Image image = new ImageImpl(Constants.EXISTING_IMAGE,  Constants.IMAGE_SIZE, Constants.INCORRECT_IMAGE_SIZE, this.history);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIllegalArgumentExceptionIfTryingToSetIncorrectHeight2() {
        Image image = new ImageImpl(Constants.EXISTING_IMAGE,  Constants.IMAGE_SIZE, Constants.INCORRECT_BIG_IMAGE_SIZE, this.history);
    }

    private void checkImageSize(int expectedWidth, int expectedHeight, Image image) {
        Assert.assertEquals(expectedWidth, image.getWidth());
        Assert.assertEquals(expectedHeight, image.getHeight());
    }


}