package com.ood.commander.command;

import com.ood.commander.Constants;
import com.ood.commander.model.*;
import com.ood.commander.service.History;
import com.ood.commander.service.ImageControllerImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DeleteItemCommandTest {

    private History history;

    private Image image;

    private List<DocumentItem> items;

    private Paragraph paragraph;

    private ImageControllerImpl imageController;

    @Before
    public void setUp() {
        this.history = new History();
        this.image = new ImageImpl(Constants.EXISTING_IMAGE, Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, this.history, new ImageControllerImpl(Constants.IMAGE_DIRECTORY));
        this.items = new ArrayList<>();
        this.paragraph = new ParagraphImpl(Constants.TEXT, this.history);
        this.imageController = new ImageControllerImpl(Constants.IMAGE_DIRECTORY);
        try {
            this.history.addAndExecuteCommand(new InsertParagraphCommand(this.items, this.paragraph, 0));
            this.history.addAndExecuteCommand(new InsertImageCommand(items, image, 1));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void canExecuteDeleteItemCommand() {
        try {
            DeleteItemCommand deleteItemCommand = new DeleteItemCommand(0, this.items);
            this.history.addAndExecuteCommand(deleteItemCommand);
            Assert.assertEquals(1, this.items.size());
            Assert.assertEquals(Constants.EXISTING_IMAGE, this.items.get(0).getImage().getPath());
            Assert.assertNull(this.items.get(0).getParagraph());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void canExecuteDeleteItemCommandAndThenUnExecute() {
        try {
            DeleteItemCommand deleteItemCommand = new DeleteItemCommand(0, this.items);
            this.history.addAndExecuteCommand(deleteItemCommand);
            Assert.assertEquals(1, this.items.size());
            this.history.undo();
            Assert.assertEquals(2, this.items.size());
            Assert.assertEquals(Constants.TEXT, this.items.get(0).getParagraph().getText());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void ifTryingToSetDeleteItemInWrongPositionThrowWrongPositionException() {
        try {
            DeleteItemCommand deleteItemCommand = new DeleteItemCommand(-2, items);
            fail();
        } catch (Exception e) {
            Assert.assertEquals("Wrong position: -2", e.getMessage());
        }
    }

    @Test
    public void ifPerformImageDeletionImageIsMarkedForDeletion() {
        try {
            DeleteItemCommand deleteItemCommand = new DeleteItemCommand(1, this.items);
            this.history.addAndExecuteCommand(deleteItemCommand);
            Assert.assertEquals(1, this.items.size());
            Assert.assertEquals(1, this.image.getController().getImagesToRemove().size());
        } catch (Exception e) {
            fail();
        }
    }


}