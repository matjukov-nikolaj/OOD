package com.ood.commander.model;

import com.ood.commander.Constants;
import com.ood.commander.Utilities;
import com.ood.exception.WrongPositionException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.junit.Assert.fail;

public class DocumentImplTest {

    private Document document;

    @Before
    public void setUp() {
        document = new DocumentImpl(Constants.IMAGE_DIRECTORY);
        Utilities.handleImageDirectory();
    }

    @Test
    public void canSetTitle() {
        try {
            document.setTitle(Constants.TEXT);
            Assert.assertEquals(Constants.TEXT, document.getTitle().getText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void canUndo() {
        try {
            document.setTitle(Constants.TEXT);
            Assert.assertEquals(Constants.TEXT, document.getTitle().getText());
            Assert.assertTrue(document.canUndo());
            document.undo();
            Assert.assertEquals("", document.getTitle().getText());
            Assert.assertTrue(!document.canUndo());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void canRedo() {
        try {
            document.setTitle(Constants.TEXT);
            Assert.assertEquals(Constants.TEXT, document.getTitle().getText());
            document.undo();
            Assert.assertEquals("", document.getTitle().getText());
            Assert.assertTrue(document.canRedo());
            document.redo();
            Assert.assertEquals(Constants.TEXT, document.getTitle().getText());
            Assert.assertTrue(!document.canRedo());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void canInsertParagraph() {
        try {
            Paragraph paragraph = document.insertParagraph(Constants.TEXT, 0);
            Assert.assertEquals(Constants.TEXT, document.getDocumentItem(0).getParagraph().getParagraphText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void canInsertImage() {
        try {
            Image image = document.insertImage(Constants.EXISTING_IMAGE, Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, 0);
            File folder = new File(Constants.IMAGE_DIRECTORY);
            String[] files = Utilities.getJpgFiles(folder);
            Assert.assertEquals(1, files.length);
            Assert.assertEquals(Constants.IMAGE_DIRECTORY + files[0], document.getDocumentItem(0).getImage().getPath());
            Assert.assertEquals(Constants.IMAGE_SIZE, document.getDocumentItem(0).getImage().getWidth());
            Assert.assertEquals(Constants.IMAGE_SIZE, document.getDocumentItem(0).getImage().getHeight());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void ifPositionIsLessTheMinusOneThrowWrongPositionException() {
        try {
            document.insertParagraph(Constants.TEXT, -2);
        } catch (Exception e) {
            Assert.assertEquals(WrongPositionException.class, e.getClass());
        }
        try {
            document.insertImage(Constants.EXISTING_IMAGE, Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, -2);
        } catch (Exception e) {
            Assert.assertEquals(WrongPositionException.class, e.getClass());
        }
    }

    @Test
    public void ifPositionIsLargerTheDocumentsItemSizeThenThrowIndexOfBoundException() {
        try {
            document.insertParagraph(Constants.TEXT, 1);
        } catch (Exception e) {
            Assert.assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }
        try {
            document.insertImage(Constants.EXISTING_IMAGE, Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, 1);
        } catch (Exception e) {
            Assert.assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }
    }

    @Test
    public void canDeleteItem() {
        try {
            Paragraph paragraph = document.insertParagraph(Constants.TEXT, 0);
            Assert.assertEquals(Constants.TEXT, document.getDocumentItem(0).getParagraph().getParagraphText());
            document.deleteItem(0);
            Assert.assertEquals(0, document.getItemsCount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void ifTryingToDeleteItemWithIncorrectIndexThrowException() {
        try {
            document.deleteItem(1);
            fail();
        } catch (Exception e) {
            Assert.assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }
        try {
            document.deleteItem(-1);
            fail();
        } catch (Exception e) {
            Assert.assertEquals(ArrayIndexOutOfBoundsException.class, e.getClass());
        }
    }

    @Test
    public void canSaveDocument() {
        try {
            Paragraph paragraph = document.insertParagraph(Constants.TEXT + "&\'\"<>", 0);
            document.save(Constants.RESOURCES_DIRECTORY);
            String expectedResult = "<!DOCTYPE html><html><head><title></title></head><body><p>text&amp;&apos;&quot;&lt;&gt;</p></body></html>";
            String result = "";
            try (BufferedReader br = new BufferedReader(new FileReader(Constants.RESOURCES_DIRECTORY + "index.html"))) {

                String st;
                while ((st = br.readLine()) != null) {
                    result += st;
                }
                Assert.assertEquals(expectedResult, result);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                fail();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

}