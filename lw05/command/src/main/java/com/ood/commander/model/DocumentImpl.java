package com.ood.commander.model;

import com.ood.commander.command.DeleteItemCommand;
import com.ood.commander.service.History;
import com.ood.commander.command.ChangeStringCommand;
import com.ood.commander.command.InsertImageCommand;
import com.ood.commander.command.InsertParagraphCommand;
import com.ood.commander.service.ImageController;
import com.ood.exception.WrongPositionException;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DocumentImpl implements Document {

    private Text title;

    private History history = new History();

    private List<DocumentItem> documentItems = new ArrayList<>();

    private ImageController imageController = new ImageController();

    private static final String INDEX_HTML = "\\index.html";

    @Override
    public void setTitle(String title) throws Exception {
        this.title = new TextImpl(title);
        this.history.addAndExecuteCommand(new ChangeStringCommand("", title, this.title));
    }

    @Override
    public Text getTitle() {
        return title == null ? new TextImpl("") : title;
    }

    @Override
    public boolean canRedo() {
        return this.history.canRedo();
    }

    @Override
    public void redo() {
        this.history.redo();
    }

    @Override
    public void undo() {
        this.history.undo();
    }

    @Override
    public boolean canUndo() {
        return this.history.canUndo();
    }

    @Override
    public Paragraph insertParagraph(String text, int position) throws Exception {
        Paragraph paragraph = new ParagraphImpl(text, this.history);
        if (position > this.documentItems.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.history.addAndExecuteCommand(new InsertParagraphCommand(this.documentItems, paragraph, position));
        return paragraph;
    }

    @Override
    public Image insertImage(String path, int width, int height, int position) throws Exception {
        if (position < 0) {
            throw new WrongPositionException(position);
        }
        String newFilePath = this.imageController.add(path);
        Image image = new ImageImpl(newFilePath, width, height, this.history);
        this.history.addAndExecuteCommand(new InsertImageCommand(this.documentItems, image, position, this.imageController));
        return image;
    }

    @Override
    public void save(String path) {
        String directory = path + INDEX_HTML;
        File file = new File(directory);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Delete the file: " + directory);
            }
        }
        try (PrintWriter writer = new PrintWriter(directory, "UTF-8");) {
            if (file.createNewFile()) {
                System.out.println("Create the file: " + directory);
            }
            writer.println( "<!DOCTYPE html><html><head><title>" + this.escapeHtmlCharacters(this.getTitle().getText()) + "</title></head><body>" );
            for (DocumentItem item : this.documentItems) {
                Paragraph paragraph = item.getParagraph();
                Image image = item.getImage();
                if (paragraph != null) {
                    writer.println("<p>" + this.escapeHtmlCharacters(paragraph.getParagraphText()) + "</p>");
                } else if (image != null) {
                    writer.println("<img src=\"" + this.escapeHtmlCharacters(image.getPath()) + "\" width=\"" + image.getWidth() + "\" height=\"" + image.getHeight() + "\"/>");
                }
            }
            writer.println("</body></html>");
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteItem(int index) throws Exception {
        if (index >= this.documentItems.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.history.addAndExecuteCommand(new DeleteItemCommand(index, this.documentItems, this.imageController));
    }

    @Override
    public DocumentItem getDocumentItem(int index) {
        return this.documentItems.get(index);
    }

    @Override
    public int getItemsCount() {
        return this.documentItems.size();
    }

    private String escapeHtmlCharacters(String text)
    {
        text = text.replaceAll("&", "&amp;");
        text = text.replaceAll("/'", "&apos;");
        text = text.replaceAll("\"", "&quot;");
        text = text.replaceAll(">", "&gt;");
        text = text.replaceAll("<", "&lt;");
        return text;
    }

}
