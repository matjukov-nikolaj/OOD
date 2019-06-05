package com.ood.commander.command;

import com.ood.commander.Constants;
import com.ood.commander.model.Text;
import com.ood.commander.model.TextImpl;
import com.ood.commander.service.History;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class ChangeStringCommandTest {

    private History history;

    @Before
    public void setUp() {
        this.history = new History();
    }

    @Test
    public void canExecuteChangeStringCommand() {
        try {
            Text text = new TextImpl(Constants.TEXT);
            this.history.addAndExecuteCommand(new ChangeStringCommand(Constants.TEXT, Constants.NEW_TEXT, text));
            Assert.assertEquals(Constants.NEW_TEXT, text.getText());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void canExecuteChangeStringCommandAndThenUnExecute() {
        try {
            Text text = new TextImpl(Constants.TEXT);
            this.history.addAndExecuteCommand(new ChangeStringCommand(Constants.TEXT, Constants.NEW_TEXT, text));
            this.history.undo();
            Assert.assertEquals(Constants.TEXT, text.getText());
        } catch (Exception e) {
            fail();
        }
    }

}