package com.ood.commander.command;

import com.ood.commander.Constants;
import com.ood.commander.model.DocumentItem;
import com.ood.commander.model.Paragraph;
import com.ood.commander.model.ParagraphImpl;
import com.ood.commander.service.History;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;


public class InsertParagraphCommandTest {

    private History history;

    private Paragraph paragraph;

    private List<DocumentItem> items;

    @Before
    public void setUp() {
        this.history = new History();
        this.paragraph = new ParagraphImpl(Constants.TEXT, this.history);
        this.items = new ArrayList<>();
    }

    @Test
    public void canExecuteInsertParagraphCommand() {
        try {
            InsertParagraphCommand insertParagraphCommand = new InsertParagraphCommand(this.items, this.paragraph, 0);
            this.history.addAndExecuteCommand(insertParagraphCommand);
            Assert.assertEquals(1, this.items.size());
            Assert.assertEquals(Constants.TEXT, this.items.get(0).getParagraph().getText());
            Assert.assertNull(this.items.get(0).getImage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void canExecuteInsertParagraphCommandAndThenUnExecute() {
        try {
            InsertParagraphCommand insertParagraphCommand = new InsertParagraphCommand(this.items, this.paragraph, 0);
            this.history.addAndExecuteCommand(insertParagraphCommand);
            Assert.assertEquals(1, this.items.size());
            Assert.assertEquals(Constants.TEXT, this.items.get(0).getParagraph().getText());
            this.history.undo();
            Assert.assertEquals(0, this.items.size());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void ifTryingToSetInsertParagraphInWrongPositionThrowWrongPositionException() {
        try {
            InsertParagraphCommand insertParagraphCommand = new InsertParagraphCommand(this.items, this.paragraph, -2);
            fail();
        } catch (Exception e) {
            Assert.assertEquals("Wrong position: -2", e.getMessage());
        }
    }

    @Test
    public void canSetInTheDocumentEndByPositionMinusOne() {
        try {
            InsertParagraphCommand insertParagraphCommand = new InsertParagraphCommand(this.items, this.paragraph, 0);
            this.history.addAndExecuteCommand(insertParagraphCommand);
            Assert.assertEquals(1, this.items.size());
            Assert.assertEquals(Constants.TEXT, this.items.get(0).getParagraph().getText());
            this.history.addAndExecuteCommand(new InsertParagraphCommand(this.items, new ParagraphImpl(Constants.NEW_TEXT, this.history), -1));
            Assert.assertEquals(2, this.items.size());
            int end = this.items.size() - 1;
            Assert.assertEquals(Constants.NEW_TEXT, this.items.get(end).getParagraph().getText());
        } catch (Exception e) {
            fail();
        }
    }

}