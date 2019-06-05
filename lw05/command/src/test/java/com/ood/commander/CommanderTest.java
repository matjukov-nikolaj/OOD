package com.ood.commander;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CommanderTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream old = System.out;

    @After
    public void tearDown() {
        System.out.flush();
        System.setOut(old);
    }

    private Commander commander;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(output));
        commander = new Commander();
    }

    @Test
    public void commandHistoryDepthIsLimitedByTenCommands() {
        this.output.reset();
        String data = "ip 0 0\n" +
                "ip 1 1\n" +
                "ip 2 2\n" +
                "ip 3 3\n" +
                "ip 4 4\n" +
                "ip 5 5\n" +
                "ip 6 6\n" +
                "ip 7 7\n" +
                "ip 8 8\n" +
                "ip 9 9\n" +
                "ip 10 10\n" +
                "undo\n" +
                "undo\n" +
                "undo\n" +
                "undo\n" +
                "undo\n" +
                "undo\n" +
                "undo\n" +
                "undo\n" +
                "undo\n" +
                "undo\n" +
                "undo\n" +
                "list\n" +
                "exit\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        commander.start();
        String expected = "> > > > > > > > > > > > > > > > > > > > > > Can't undo\r\n" +
                "> ---------------------Elements---------------------\r\n" +
                  "Title: \r\n" +
                  "0. Paragraph: 0\r\n" +
                  "--------------------------------------------------\r\n" +
                "> ";
        Assert.assertEquals(expected, output.toString());
    }

    @Test
    public void whenYouSwitchToANewFutureTheOldCommandHistoryIsDeleted() {
        this.output.reset();
        String data = "ip 0 0\n" +
                "ip 1 1\n" +
                "ip 2 2\n" +
                "ip 3 3\n" +
                "ip 4 4\n" +
                "ip 5 5\n" +
                "ip 6 6\n" +
                "ip 7 7\n" +
                "ip 8 8\n" +
                "undo\n" +
                "undo\n" +
                "undo\n" +
                "undo\n" +
                "list\n" +
                "ip 5 new 5\n" +
                "redo\n" +
                "list\n" +
                "exit\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        commander.start();
        String expected = "> > > > > > > > > > > > > > ---------------------Elements---------------------\r\n" +
                "Title: \r\n" +
                "0. Paragraph: 0\r\n" +
                "1. Paragraph: 1\r\n" +
                "2. Paragraph: 2\r\n" +
                "3. Paragraph: 3\r\n" +
                "4. Paragraph: 4\r\n" +
                "--------------------------------------------------\r\n" +
                "> > Can't redo\r\n" +
                "> ---------------------Elements---------------------\r\n" +
                "Title: \r\n" +
                "0. Paragraph: 0\r\n" +
                "1. Paragraph: 1\r\n" +
                "2. Paragraph: 2\r\n" +
                "3. Paragraph: 3\r\n" +
                "4. Paragraph: 4\r\n" +
                "5. Paragraph: new 5\r\n" +
                "--------------------------------------------------\r\n" +
                "> ";
        Assert.assertEquals(expected, output.toString());
    }

}