package com.ood.commander.service;

import com.ood.commander.Constants;
import com.ood.commander.Utilities;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class ImageControllerTest {

    private ImageController imageController;

    @Before
    public void setUp() {
        this.imageController = new ImageController(Constants.IMAGE_DIRECTORY);
        Utilities.handleImageDirectory();
    }

    @Test
    public void canAddImage() {
        addImage();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ifFileNotExistThrowException() {
        this.imageController.add(Constants.NON_EXISTING_IMAGE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ifFileIsDirectoryThrowException() {
        try {
            this.imageController.delete(Constants.RESOURCES_DIRECTORY);
        } catch (Exception e) {
            Assert.assertEquals(IllegalArgumentException.class, e.getClass());
        }
        this.imageController.add(Constants.RESOURCES_DIRECTORY);
    }

    @Test
    public void canDeleteImage() {
        addImage();
        File folder = new File(Constants.IMAGE_DIRECTORY);
        String[] files = Utilities.getJpgFiles(folder);
        this.imageController.delete(Constants.IMAGE_DIRECTORY + files[0]);
        files = Utilities.getJpgFiles(folder);
        Assert.assertEquals(0, files.length);
    }

    @Test
    public void canMarkForDeletion() {
        markForDelete();
    }

    @Test
    public void canDeleteUnusedImages() {
        markForDelete();
        File folder = new File(Constants.IMAGE_DIRECTORY);
        String[] files = Utilities.getJpgFiles(folder);
        Assert.assertEquals(1, this.imageController.getImagesToRemove().size());
        Assert.assertEquals(1, files.length);
        this.imageController.deleteUnusedImages();
        files = Utilities.getJpgFiles(folder);
        Assert.assertEquals(0, this.imageController.getImagesToRemove().size());
        Assert.assertEquals(0, files.length);
    }

    private void markForDelete() {
        this.imageController.add(Constants.EXISTING_IMAGE);
        File folder = new File(Constants.IMAGE_DIRECTORY);
        String[] files = Utilities.getJpgFiles(folder);
        Assert.assertEquals(1, files.length);
        String image = Constants.IMAGE_DIRECTORY + files[0];
        this.imageController.markForDeletion(image, true);
        Assert.assertEquals(1, this.imageController.getImagesToRemove().size());
    }

    private void addImage() {
        this.imageController.add(Constants.EXISTING_IMAGE);
        File folder = new File(Constants.IMAGE_DIRECTORY);
        String[] files = Utilities.getJpgFiles(folder);
        Assert.assertEquals(1, files.length);
    }


}