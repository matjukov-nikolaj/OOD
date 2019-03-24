package com.ood.commander.model;

import com.ood.commander.Constants;
import com.ood.commander.service.History;
import com.ood.commander.service.ImageControllerImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DocumentItemTest {

    private History history;

    @Before
    public void setUp() {
        this.history = new History();
    }

    @Test
    public void canCreateDocumentItemFromParagraph() {
        Paragraph paragraph = new ParagraphImpl(Constants.TEXT, this.history);
        DocumentItem documentItem = new DocumentItem(paragraph);
        Assert.assertEquals(Constants.TEXT, documentItem.getParagraph().getParagraphText());
        Assert.assertNull(documentItem.getImage());
    }

    @Test
    public void canCreateDocumentItemFromImage() {
        Image image = new ImageImpl(Constants.EXISTING_IMAGE, Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, history, new ImageControllerImpl(Constants.IMAGE_DIRECTORY));
        DocumentItem documentItem = new DocumentItem(image);
        Assert.assertEquals(Constants.EXISTING_IMAGE, documentItem.getImage().getPath());
        Assert.assertNull(documentItem.getParagraph());
    }

}