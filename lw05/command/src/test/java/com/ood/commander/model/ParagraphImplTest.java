package com.ood.commander.model;

import com.ood.commander.Constants;
import com.ood.commander.service.History;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParagraphImplTest {

    @Test
    public void canCreateParagraph() {
        History history = new History();
        Paragraph paragraph = new ParagraphImpl(Constants.TEXT, history);
        Assert.assertEquals(Constants.TEXT, paragraph.getText());
        paragraph.setText(Constants.NEW_TEXT);
        Assert.assertEquals(Constants.NEW_TEXT, paragraph.getText());
        try {
            paragraph.setParagraphText(Constants.NEW_PARAGRAPH_TEXT);
            Assert.assertEquals(Constants.NEW_PARAGRAPH_TEXT, paragraph.getParagraphText());
        } catch (Exception e) {
            fail();
        }
    }

}