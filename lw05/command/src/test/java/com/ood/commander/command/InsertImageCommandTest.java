package com.ood.commander.command;

import com.ood.commander.Constants;
import com.ood.commander.model.DocumentItem;
import com.ood.commander.model.Image;
import com.ood.commander.model.ImageImpl;
import com.ood.commander.model.ParagraphImpl;
import com.ood.commander.service.History;
import com.ood.commander.service.ImageController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InsertImageCommandTest {

    private History history;

    private Image image;

    private List<DocumentItem> items;

    private ImageController imageController;

    @Before
    public void setUp() {
        this.history = new History();
        this.image = new ImageImpl(Constants.EXISTING_IMAGE, Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, this.history);
        this.items = new ArrayList<>();
        this.imageController = new ImageController(Constants.IMAGE_DIRECTORY);
    }

    @Test
    public void canCreateInsertImageCommandAndExecute() {
        try {
            InsertImageCommand insertImageCommand = new InsertImageCommand(items, image, 0, imageController);
            this.history.addAndExecuteCommand(insertImageCommand);
            Assert.assertEquals(1, this.items.size());
            Assert.assertEquals(1, this.items.size());
            Assert.assertEquals(Constants.EXISTING_IMAGE, this.items.get(0).getImage().getPath());
            Assert.assertNull(this.items.get(0).getParagraph());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void canExecuteInsertImageCommand() {
        try {
            InsertImageCommand insertImageCommand = new InsertImageCommand(items, image, 0, imageController);
            this.history.addAndExecuteCommand(insertImageCommand);
            Assert.assertEquals(1, this.items.size());
            this.history.undo();
            Assert.assertEquals(0, this.items.size());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void ifTryingToSetInsertImagesInWrongPositionThrowWrongPositionException() {
        try {
            InsertImageCommand insertImageCommand = new InsertImageCommand(items, image, -2, imageController);
            fail();
        } catch (Exception e) {
            Assert.assertEquals("Wrong position: -2", e.getMessage());
        }
    }

    @Test
    public void canSetInTheDocumentEndByPositionMinusOne() {
        try {
            this.history.addAndExecuteCommand(new InsertParagraphCommand(this.items, new ParagraphImpl(Constants.TEXT, this.history), 0));
            InsertImageCommand insertImageCommand = new InsertImageCommand(items, image, -1, imageController);
            this.history.addAndExecuteCommand(insertImageCommand);
            Assert.assertEquals(2, this.items.size());
            int end = this.items.size() - 1;
            Assert.assertEquals(Constants.EXISTING_IMAGE, this.items.get(end).getImage().getPath());
        } catch (Exception e) {
            fail();
        }
    }

}