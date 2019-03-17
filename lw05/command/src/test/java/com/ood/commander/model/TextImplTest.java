package com.ood.commander.model;

import com.ood.commander.Constants;
import org.junit.Assert;
import org.junit.Test;

public class TextImplTest {

    @Test
    public void canCreateText() {
        Text text = new TextImpl(Constants.TEXT);
        Assert.assertEquals(Constants.TEXT, text.getText());
        text.setText(Constants.NEW_TEXT);
        Assert.assertEquals(Constants.NEW_TEXT, text.getText());
    }

}