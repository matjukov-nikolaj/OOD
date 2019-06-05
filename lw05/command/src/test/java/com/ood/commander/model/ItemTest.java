package com.ood.commander.model;

import com.ood.commander.Constants;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream old = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @After
    public void tearDown() {
        System.out.flush();
        System.setOut(old);
    }

    @Test
    public void canCreateItem() {
        Function func = (args) -> {
            System.out.println(Constants.DO_SOMETHING);
        };
        Item item = new Item(Constants.COMMAND, Constants.DESCRIPTION, func);
        Assert.assertEquals(Constants.COMMAND, item.getShortcut());
        Assert.assertEquals(Constants.DESCRIPTION, item.getDescription());
        item.action(new ArrayList<>());
        Assert.assertEquals(Constants.DO_SOMETHING + "\r\n", output.toString());
    }

}